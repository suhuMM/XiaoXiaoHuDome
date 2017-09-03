package com.suhu.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.suhu.android.R;
import com.suhu.android.base.BaseActivity;
import com.suhu.android.utils.AccountValidatorUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.password)
    EditText password;

    private String phoneS,passwordS;
    @Override
    public void setActionBar() {
        left.setVisibility(View.GONE);
        title.setText("登录");

    }

    @Override
    public void setCreateView(Bundle savedInstanceState) {
        showContentView(R.layout.activity_main);

    }



    @OnClick({R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                //login();
                break;
        }
    }

    private void login() {
        phoneS = phone.getText().toString();
        passwordS = password.getText().toString();
        if (phoneS==null){
            Toast.makeText(this,"请输入手机号",Toast.LENGTH_LONG).show();
            return ;
        }
        if (!AccountValidatorUtil.isMobile(phoneS)){
            Toast.makeText(this,"请输正确的入手机号",Toast.LENGTH_LONG).show();
            return;
        }
        if (passwordS==null){
            Toast.makeText(this,"请输密码",Toast.LENGTH_LONG).show();
            return ;
        }

    }
}
