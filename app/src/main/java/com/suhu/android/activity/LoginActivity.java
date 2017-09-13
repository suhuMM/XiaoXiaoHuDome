package com.suhu.android.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.suhu.android.R;
import com.suhu.android.application.SoftwareApp;
import com.suhu.android.base.BaseTitleActivity;
import com.suhu.android.core.ApiRequestFactory;
import com.suhu.android.core.ApiRequestMethods;
import com.suhu.android.core.ApiUrl;
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
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import okhttp3.Call;

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
    private String userId,name,portraitUri="http://d.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=cf8442791bd8bc3ec65d0eceb7bb8a28/b3119313b07eca80c63dcea4932397dda14483bd.jpg";

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
                }
                if (dialog !=null) dialog.dismiss();
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
            startActivity(new Intent(this,MainActivity.class));
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
                userId = map.get("uid");
                name = map.get("name");
              //  portraitUri = map.get("iconurl");
                handler.sendEmptyMessage(0);
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

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    ApiRequestMethods.getToken(LoginActivity.this,
                            ApiUrl.IM_TOKEN, userId, name, portraitUri, new ApiRequestFactory.HttpCallBackListener() {
                                @Override
                                public void onSuccess(String response, String url, int id) {
                                   String ss =  response;
                                    Toast.makeText(LoginActivity.this, "成功", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void failure(Call call, Exception e, int id) {
                                    Toast.makeText(LoginActivity.this, "失败", Toast.LENGTH_LONG).show();
                                }
                            },true);
                    break;
            }
        }
    };

    private void connect(String token) {

        if (getApplicationInfo().packageName.equals(SoftwareApp.getCurProcessName(getApplicationContext()))) {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    Toast.makeText(LoginActivity.this, "错误", Toast.LENGTH_LONG).show();
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Toast.makeText(LoginActivity.this, "连接融云失败", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
