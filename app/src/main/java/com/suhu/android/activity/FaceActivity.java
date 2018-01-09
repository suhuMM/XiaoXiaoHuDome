package com.suhu.android.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.suhu.android.R;
import com.suhu.android.base.activity.BaseSlidingActivity;
import com.suhu.android.core.ApiRequestFactory;
import com.suhu.android.core.ApiRequestMethods;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * @author suhu
 * @date 2018-01-08
 */
public class FaceActivity extends BaseSlidingActivity {


    @BindView(R.id.select)
    Button select;
    @BindView(R.id.identify)
    Button identify;
    @BindView(R.id.image)
    ImageView image;


    private static final int REQUEST_CODE= 111;
    private String imageUrl;
    private Bitmap bitmap;

    @Override
    public int showContView() {
        return R.layout.activity_face;
    }

    @Override
    public void setActionBar() {
        title.setText("人脸识别");

    }

    @Override
    public void setCreateView(Bundle savedInstanceState) {

    }



    @Override
    @OnClick({R.id.select, R.id.identify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.select:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE);
                break;
            case R.id.identify:
                request();
                break;
            default:
                break;
        }
    }

    private void request() {
        ApiRequestMethods.detect(this, imageUrl, new ApiRequestFactory.HttpCallBackListener() {
            @Override
            public void onSuccess(String response, String url, int id) {

            }

            @Override
            public void failure(Call call, Exception e, int id) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode==REQUEST_CODE){
            if (data!=null){
                Uri uri = data.getData();
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                cursor.moveToFirst();
                int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                imageUrl =  cursor.getString(index);
                cursor.close();
                resizePhoto(imageUrl);
                image.setImageBitmap(bitmap);

            }
        }



        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * 图片裁剪
     * @param url
     */
    private void resizePhoto(String url) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(url,options);
        double ratio = Math.max(options.outWidth*1.0f/1024,options.outHeight*1.0f/1024);
        options.inSampleSize = (int) Math.ceil(ratio);
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(url,options);




    }



















}
