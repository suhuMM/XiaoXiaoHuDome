package com.suhu.android.activity;

import android.content.Intent;
import android.os.Bundle;
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
 */

public class ShareActivity extends BaseTitleActivity implements UMShareListener{
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
        new ShareAction(this)
                .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                .withText("hello")//分享内容
                .setCallback(this)//回调监听器
                .share();
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
}
