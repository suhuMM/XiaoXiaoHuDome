package com.suhu.android.fragment;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blankj.utilcode.util.ImageUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.suhu.android.R;
import com.suhu.android.activity.ImageSelectActivity;
import com.suhu.android.activity.SportActivity;
import com.suhu.android.activity.UpdateActivity;
import com.suhu.android.utils.Config;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/9/5 0005.
 */

public class FragmentPerson extends Fragment {
    @BindView(R.id.photo)
    CircleImageView photo;

    private Unbinder unbinder;
    private RequestOptions options;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, null);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        setHeadPhoto();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);


    }

    private void setHeadPhoto() {
        options = new RequestOptions();
        options.error(getActivity().getDrawable(R.drawable.qq))
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(getActivity())
                .load(Config.PHOTO_URL)
                .apply(options)
                .into(photo);

    }


    @OnClick({R.id.image_select,R.id.sport, R.id.update, R.id.photo, R.id.logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_select:
                startActivity(new Intent(getActivity(), ImageSelectActivity.class));
                break;
            case R.id.sport:
                startActivity(new Intent(getActivity(), SportActivity.class));
                break;
            case R.id.update:
                startActivity(new Intent(getActivity(), UpdateActivity.class));
                break;
            case R.id.photo:
                photograph();
                break;
            case R.id.logout:
                logout();
                break;
        }
    }

    private void logout() {
        //取消授权并且退出
        UMShareAPI.get(getActivity()).deleteOauth(getActivity(), SHARE_MEDIA.QQ, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                getActivity().finish();
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });
    }

    private void photograph() {
        RxPermissions permissions = new RxPermissions(getActivity());
        permissions.request(Manifest.permission.CAMERA).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureSelector.create(getActivity())
                            .openGallery(PictureMimeType.ofImage())
                            .maxSelectNum(1)
                            .enableCrop(true)
                            .compress(true)
                            .withAspectRatio(1,1)
                            .compressMode(PictureConfig.LUBAN_COMPRESS_MODE)
                            .circleDimmedLayer(true)
                            .showCropFrame(false)
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                } else {
                    Toast.makeText(getActivity(),
                            getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void activityResult(Intent data){
        List<LocalMedia> list = PictureSelector.obtainMultipleResult(data);
        if (list != null && list.size() > 0) {
            LocalMedia media = list.get(0);
            if (media.isCompressed()) {
                String url = media.getCompressPath();
                Glide.with(getActivity()).load(url).apply(options).into(photo);
                ImageUtils.save(ImageUtils.getBitmap(new File(url)),Config.PHOTO_URL, Bitmap.CompressFormat.PNG,true);
            }
        }
    }

}
