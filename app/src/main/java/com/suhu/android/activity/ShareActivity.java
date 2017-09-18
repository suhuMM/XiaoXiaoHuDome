package com.suhu.android.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.suhu.android.R;
import com.suhu.android.base.activity.BaseTitleActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * @author suhu
 * @data 2017/9/6.
 * @description
 * https://open.weixin.qq.com/cgi-bin/readtemplate?t=resource/app_download_android_tmpl&lang=zh_CN
 */

public class ShareActivity extends BaseTitleActivity implements UMShareListener{
    private static final int REQUEST_PERMISSION = 2;

    private List<String> permissionsL = new ArrayList<>();
    private String[] mPermissionList = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_LOGS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.SET_DEBUG_APP,
            Manifest.permission.SYSTEM_ALERT_WINDOW,
            Manifest.permission.GET_ACCOUNTS,
            Manifest.permission.WRITE_APN_SETTINGS};

    @Override
    public int showContView() {
        return R.layout.activity_share;
    }

    @Override
    public void setActionBar() {
        title.setText("分享页面");
    }

    @Override
    public void setCreateView(Bundle savedInstanceState) {

    }


    @OnClick(R.id.share)
    public void onViewClicked() {
        shareText();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    private void shareText() {

        for (String s : mPermissionList) {
            if (ActivityCompat.checkSelfPermission(this,s)!= PackageManager.PERMISSION_GRANTED){
                permissionsL.add(s);
            }
        }
        if (permissionsL.size() > 0) {
            ActivityCompat.requestPermissions(this,permissionsL.toArray(new String[permissionsL.size()]), REQUEST_PERMISSION);
        }
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {
        Toast.makeText(this,"成功了",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
        Toast.makeText(this,"失败"+throwable.getMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {
        Toast.makeText(this,"取消了",Toast.LENGTH_LONG).show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        permissionsL.clear();
        if (requestCode == REQUEST_PERMISSION){
            UMImage image = new UMImage(ShareActivity.this,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504762622136&di=e238d3b4a8ea8f8f40c36160a00f1cfe&imgtype=0&src=http%3A%2F%2Fwww.bz55.com%2Fuploads%2Fallimg%2F150511%2F139-150511112R7.jpg");
            image.compressStyle = UMImage.CompressStyle.SCALE;
            new ShareAction(this)
                    .setPlatform(SHARE_MEDIA.WEIXIN)
                    .withText("美女")
                    .withMedia(image)
                    .setCallback(this)
                    .share();

        }

    }

}
