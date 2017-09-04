package com.suhu.android.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.suhu.android.R;
import com.suhu.android.base.BaseActivity;
import com.suhu.android.utils.AccountValidatorUtil;
import com.suhu.android.utils.Config;
import com.suhu.android.utils.MD5Tools;
import com.suhu.android.utils.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login)
    Button login;

    private String phoneS, passwordS;

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
        setListener();
    }

    private void setListener() {
        login.setClickable(false);
        login.setPressed(true);
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable.toString())&&!TextUtils.isEmpty(password.getText().toString())) {
                    login.setClickable(true);
                    login.setPressed(false);

                } else {
                    login.setClickable(false);
                    login.setPressed(true);
                }
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable.toString())&&!TextUtils.isEmpty(phone.getText().toString())) {
                    login.setClickable(true);
                    login.setPressed(false);

                } else {
                    login.setClickable(false);
                    login.setPressed(true);
                }
            }
        });
    }


    @OnClick({R.id.login,R.id.right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                login();
                break;
            case R.id.right:
                startActivity(new Intent(this,RegistrationActivity.class));
                break;
        }
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

}
