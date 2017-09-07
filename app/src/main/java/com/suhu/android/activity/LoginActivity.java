package com.suhu.android.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.suhu.android.R;
import com.suhu.android.base.BaseTitleActivity;
import com.suhu.android.dialog.ShareDialog;
import com.suhu.android.utils.AccountValidatorUtil;
import com.suhu.android.utils.Config;
import com.suhu.android.utils.MD5Tools;
import com.suhu.android.utils.SharedPreferencesUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.umeng.socialize.bean.SHARE_MEDIA.QQ;

public class LoginActivity extends BaseTitleActivity implements UMAuthListener{

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login)
    Button login;

    private String phoneS, passwordS;
    private ShareDialog dialog;

    @Override
    public int showContView() {
        return R.layout.activity_login;
    }

    @Override
    public void setActionBar() {
        left.setVisibility(View.GONE);
        title.setText("登录");
        TextView textView = new TextView(this);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(15);
        textView.setText("注册");
        right.addView(textView);
    }


    @Override
    public void setCreateView(Bundle savedInstanceState) {
        dialog = ShareDialog.newInstance(8,9.0f,false,false,false);
        dialog.setOnShareClickListener(new ShareDialog.OnShareClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.sina:
                        Toast.makeText(LoginActivity.this,"新浪",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.qq:
                        Toast.makeText(LoginActivity.this,"QQ",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.wechat:
                        Toast.makeText(LoginActivity.this,"微信好友",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.wxcircle:
                        Toast.makeText(LoginActivity.this,"朋友圈",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }

    @OnClick({R.id.login,R.id.right,R.id.qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                login();
                break;
            case R.id.right:
                startActivity(new Intent(this,RegistrationActivity.class));
                break;
            case R.id.qq:
                //loginQQ();
                showDialog();
                break;
        }
    }

    private void loginQQ() {
        UMShareAPI.get(this).getPlatformInfo(this, QQ, this);
    }


    private void login() {
        phoneS = phone.getText().toString().trim();
        passwordS = password.getText().toString().trim();
        if (!AccountValidatorUtil.isMobile(phoneS)) {
            Toast.makeText(this, "请输正确的入手机号", Toast.LENGTH_LONG).show();
            return;
        }
        if ( SharedPreferencesUtils.getLoginMessage(this, Config.LOGIN_MESSAGE,phoneS, MD5Tools.MD5(passwordS))){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }else {
            Toast.makeText(this, "用户名或者密码错误", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    @Override
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
        switch (share_media){
            case QQ:
                startActivity(new Intent(this,MainActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {

    }

    private void showDialog() {

        dialog.show(getSupportFragmentManager(),"blur_sample");

    }


}
