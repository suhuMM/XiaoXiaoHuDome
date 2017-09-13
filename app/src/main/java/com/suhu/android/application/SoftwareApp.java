package com.suhu.android.application;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.oubowu.slideback.ActivityHelper;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import io.rong.imkit.RongIM;

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

    private ActivityHelper mActivityHelper;
    private static SoftwareApp softwareApp;

    @Override
    public void onCreate() {
        super.onCreate();

        //滑动退出
        mActivityHelper = new ActivityHelper();
        registerActivityLifecycleCallbacks(mActivityHelper);
        softwareApp = this;

        //工具类注册
        Utils.init(this);

        //融云
        RongIM.init(this,"3argexb630q4e");

        //UM分享注册
        UMShareAPI.get(this);
        Config.DEBUG = true;

        //UM推送：注册推送服务，每次调用register方法都会回调该接口
        PushAgent mPushAgent = PushAgent.getInstance(this);
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

    public static ActivityHelper getActivityHelper(){
        return softwareApp.mActivityHelper;
    }

    public static SoftwareApp getInstance(){
        return softwareApp;
    }
}
