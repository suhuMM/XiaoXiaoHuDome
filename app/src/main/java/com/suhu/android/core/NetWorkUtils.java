package com.suhu.android.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.suhu.android.application.SoftwareApp;


/**
 *@author suhu
 *@time 2017/4/8 9:59
 *
*/
public class NetWorkUtils {

    // 判断网络是否已连接
    public static boolean isConnect(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            return info.isConnected();
        }
        return false;
    }

    public static boolean isConnect() {
        ConnectivityManager manager = (ConnectivityManager) SoftwareApp.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            return info.isConnected();
        }
        return false;
    }

    // 判断有没有连接WiFi网络
    public static boolean isWifi(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return info.isConnected();
    }

    // 判断有没有连接Mobile网络
    public static boolean isMobile(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return info.isConnected();
    }

    // 判断当前连接的网络类型
    public static String getType(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            return info.getTypeName();
        }
        return "网络不可用";
    }
}
