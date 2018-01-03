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
import com.suhu.android.base.activity.BaseTitleActivity;
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
        dialog = ShareDialog.newInstance(2,9.0f,false,false,false);
        dialog.setOnShareClickListener(new ShareDialog.OnShareClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.sina:
                        Toast.makeText(LoginActivity.this,"新浪",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.qq:
                        Toast.makeText(LoginActivity.this,"QQ",Toast.LENGTH_SHORT).show();
                        loginQQ();
                        break;
                    case R.id.wechat:
                        Toast.makeText(LoginActivity.this,"微信好友",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.wxcircle:
                        Toast.makeText(LoginActivity.this,"朋友圈",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                }
                if (dialog !=null) {
                    dialog.dismiss();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }

    @Override
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
                showDialog();
                break;
            default:
        }
    }

    private void loginQQ() {
        UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, this);
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
            startActivity(new Intent(this,MainActivity.class));
            Toast.makeText(this, "用户名或者密码错误", Toast.LENGTH_LONG).show();
        }
    }

    private void showDialog() {
        dialog.show(getSupportFragmentManager(),"blur_sample");
    }


    /**--------------------------------第三方登录接口---------------------------------------------**/

    @Override
    public void onStart(SHARE_MEDIA shareMedia) {

    }

    @Override
    public void onComplete(SHARE_MEDIA shareMedia, int i, Map<String, String> map) {
        switch (shareMedia){
            case QQ:
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                break;
            default:
        }
    }

    @Override
    public void onError(SHARE_MEDIA shareMedia, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(SHARE_MEDIA shareMedia, int i) {

    }


}
