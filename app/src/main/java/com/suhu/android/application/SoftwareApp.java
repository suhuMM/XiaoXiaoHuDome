package com.suhu.android.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.oubowu.slideback.ActivityHelper;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import io.rong.imkit.RongIM;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/9/2 0002.
 */

public class SoftwareApp extends Application implements IUmengRegisterCallback{
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
        //RongIM.setServerInfo("nav.cn.ronghub.com", "up.qbox.me");
        RongIM.init(this,"3argexb630q4e");

        //UM分享注册
        UMShareAPI.get(this);
        Config.DEBUG = true;

        //UM推送：注册推送服务，每次调用register方法都会回调该接口
        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.register(this);

        //配置Cookie(包含Session)
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        //设置可访问所有的https网站
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .cookieJar(cookieJar)
                //配置Log
                .addInterceptor(new LoggerInterceptor("TAG"))
                //https
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();

        OkHttpUtils.initClient(okHttpClient);

        // 测试 注意上线时要一定要去掉
//        OtCrashHandler otCrashHandler = OtCrashHandler.getInstance();
//        otCrashHandler.init(this);

    }

    public static ActivityHelper getActivityHelper(){
        return softwareApp.mActivityHelper;
    }

    public static SoftwareApp getInstance(){
        return softwareApp;
    }

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    @Override
    public void onSuccess(String s) {

    }

    @Override
    public void onFailure(String s, String s1) {

    }
}
