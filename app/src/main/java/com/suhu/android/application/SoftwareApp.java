package com.suhu.android.application;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

public class SoftwareApp extends Application{
    {
        //ok
        PlatformConfig.setWeixin("wx79b3c663c76916d8", "c12e4143f1715f9d243fd0940a11edd8");
        PlatformConfig.setAlipay("alipay");
        PlatformConfig.setQQZone("100424468", "7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3561365327", "2bca4073acc3a5f602e6e699079679cd","http://sns.whalecloud.com/");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);
        UMShareAPI.get(this);
        Config.DEBUG = true;
        PushAgent mPushAgent = PushAgent.getInstance(this);//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
            }

            @Override
            public void onFailure(String s, String s1) {
            }
        });

    }
}
