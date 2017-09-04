package com.suhu.android.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/9/4 0004.
 */

public class SharedPreferencesUtils {

    /**
     *@method 储存用户名密码
     *@author suhu
     *@time 2017/9/4 0004 20:51
     *@param context
     *@param tableName
     *@param phone
     *@param password
     *
    */
    public static void createSharePreferences(Context context, String tableName, String phone ,String password){
        SharedPreferences sharedPreferences = context.getSharedPreferences(tableName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Config.PHONE,phone);
        editor.putString(Config.PASSWORD,password);
        editor.commit();
    }

    /**
     *@method 验证登陆信息
     *@author suhu
     *@time 2017/9/4 0004 20:58
     *@param context
     *@param tableName
     *@param phone
     *@param password
     *
    */
    public static boolean getLoginMessage(Context context, String tableName, String phone ,String password){
        SharedPreferences sharedPreferences = context.getSharedPreferences(tableName, Context.MODE_PRIVATE);
        ;
        if (sharedPreferences.getString(Config.PHONE,"null").equals(phone)
                &&
                sharedPreferences.getString(Config.PASSWORD,"null").equals(password)
                ){
            return true;
        }else {
            return false;
        }
    }







}
