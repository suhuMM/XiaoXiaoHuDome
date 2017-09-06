package com.suhu.android.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.suhu.android.R;
import com.suhu.android.base.BaseTitleActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.OnClick;

/**
 * @author suhu
 * @data 2017/9/6.
 * @description
 * https://open.weixin.qq.com/cgi-bin/readtemplate?t=resource/app_download_android_tmpl&lang=zh_CN
 */

public class ShareActivity extends BaseTitleActivity implements UMShareListener{
    //private static final int REQUEST_PERMISSION_CODE = 1;
    private static final int REQUEST_PERMISSION = 2;


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


    private void shareText() {

        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{
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
            ActivityCompat.requestPermissions(this,mPermissionList,REQUEST_PERMISSION);
        }

//        MPermissions.requestPermissions(this,REQUEST_PERMISSION_CODE,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.CALL_PHONE,
//                Manifest.permission.READ_LOGS,
//                Manifest.permission.READ_PHONE_STATE,
//                Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.SET_DEBUG_APP,
//                Manifest.permission.SYSTEM_ALERT_WINDOW,
//                Manifest.permission.GET_ACCOUNTS,
//                Manifest.permission.WRITE_APN_SETTINGS
//        );

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
        if (requestCode == REQUEST_PERMISSION){
            new ShareAction(this)
                    .setPlatform(SHARE_MEDIA.SINA)//传入平台
                    .withText("分享微信试试")//分享内容
                    .setCallback(this)//回调监听器
                    .share();
        }

    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
//    @PermissionGrant(REQUEST_PERMISSION_CODE)
//    public void requestPermissionSuccess(){
//        new ShareAction(this)
//                .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
//                .withText("分享微信试试")//分享内容
//                .setCallback(this)//回调监听器
//                .share();
//    }
//
//    @PermissionDenied(REQUEST_PERMISSION_CODE)
//    public void requestPermissionFailed(){
//        Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
//    }
}
