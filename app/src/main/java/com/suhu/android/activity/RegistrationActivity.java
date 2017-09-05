package com.suhu.android.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mob.MobSDK;
import com.suhu.android.R;
import com.suhu.android.base.BaseTitleActivity;
import com.suhu.android.utils.AccountValidatorUtil;
import com.suhu.android.utils.Config;
import com.suhu.android.utils.MD5Tools;
import com.suhu.android.utils.SharedPreferencesUtils;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;


public class RegistrationActivity extends BaseTitleActivity {
    private static final int REQUEST_PERMISSION_CODE = 1;

    @BindView(R.id.actionbar)
    RelativeLayout actionbar;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.request)
    Button request;
    @BindView(R.id.password_one)
    EditText passwordOne;
    @BindView(R.id.password_again)
    EditText passwordAgain;
    @BindView(R.id.registration)
    Button registration;

    private String phoneS,codeS,Password1,password2;

    private EventHandler handler = new EventHandler(){
        @Override
        public void afterEvent(int event, int result, Object data) {
            if (result ==SMSSDK.RESULT_COMPLETE){
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegistrationActivity.this,"验证成功",Toast.LENGTH_SHORT).show();
                            SharedPreferencesUtils.createSharePreferences(RegistrationActivity.this, Config.LOGIN_MESSAGE,phoneS, MD5Tools.MD5(Password1));
                            startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                            finish();
                        }
                    });
                }else if(event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    //获取验证码成功
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegistrationActivity.this,"验证码已发送",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }else{
                ((Throwable)data).printStackTrace();
                Throwable throwable = (Throwable) data;
                try {
                    JSONObject obj = new JSONObject(throwable.getMessage());
                    final String des = obj.optString("detail");
                    if (!TextUtils.isEmpty(des)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (des.equals("invalid validation code")){
                                    Toast.makeText(RegistrationActivity.this,"验证码无效",Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(RegistrationActivity.this,"手机号已注册",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    };


    @Override
    public int showContView() {
        return R.layout.activity_registration;
    }

    @Override
    public void setActionBar() {
        title.setText("注册");
    }

    @Override
    public void setCreateView(Bundle savedInstanceState) {
        MobSDK.init(this, "20a8ee4f731de", "ab236fd23e610fe078dcc1fb136c0adf");
        SMSSDK.registerEventHandler(handler);
        setListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(handler);
    }

    @OnClick({R.id.request, R.id.registration})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.request:
                changeState();
                sendSMS();
                break;
            case R.id.registration:
                registration();
                break;
        }
    }

    private void registration() {
        phoneS = phone.getText().toString();
        codeS = code.getText().toString();
        Password1 = passwordOne.getText().toString();
        password2 = passwordAgain.getText().toString();

        if (TextUtils.isEmpty(phoneS)){
            Toast.makeText(this,"手机号为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!AccountValidatorUtil.isMobile(phoneS)){
            Toast.makeText(this,"手机号有误",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(codeS)){
            Toast.makeText(this,"验证码为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Password1)){
            Toast.makeText(this,"密码为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password2)){
            Toast.makeText(this,"再次输入密码",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Password1.equals(password2)){
            Toast.makeText(this,"两次密码不一致",Toast.LENGTH_SHORT).show();
            return;
        }
        SMSSDK.submitVerificationCode("86",phoneS,codeS);

    }


    private void setListener() {
        request.setClickable(false);
        request.setPressed(true);
        request.setText("输入手机号");
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)){
                    request.setText("输入手机号");
                    request.setClickable(false);
                    request.setPressed(true);
                    return;
                }
                if (s.length()!=11 || !AccountValidatorUtil.isMobile(s.toString())){
                    request.setText("输入正确手机号");
                    request.setClickable(false);
                    request.setPressed(true);
                    return;
                }
                request.setText("获取验证码");
                request.setClickable(true);
                request.setPressed(false);
            }
        });
    }

    private void changeState() {
        new CountDownTimer(6000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                request.setText(millisUntilFinished/1000+"秒");
                request.setClickable(false);
                request.setPressed(true);
            }
            @Override
            public void onFinish() {
                request.setText("再次获取");
                request.setClickable(true);
                request.setPressed(false);
            }
        }.start();
    }

    private void sendSMS() {
        MPermissions.requestPermissions(this,REQUEST_PERMISSION_CODE,
                Manifest.permission.READ_SMS,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_PHONE_STATE
                );
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionGrant(REQUEST_PERMISSION_CODE)
    public void requestPermissionSuccess(){
        phoneS = phone.getText().toString();
        SMSSDK.getVerificationCode("86",phoneS);
    }

    @PermissionDenied(REQUEST_PERMISSION_CODE)
    public void requestPermissionFailed(){
        Toast.makeText(this, "DENY ACCESS SDCARD!", Toast.LENGTH_SHORT).show();
    }


}
