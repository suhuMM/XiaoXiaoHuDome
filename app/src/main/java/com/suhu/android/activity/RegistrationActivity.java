package com.suhu.android.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.suhu.android.R;
import com.suhu.android.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class RegistrationActivity extends BaseActivity {

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

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.request, R.id.registration})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.request:
                changeState();
                //sendSMS();
                break;
            case R.id.registration:
                break;
        }
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
    }
}
