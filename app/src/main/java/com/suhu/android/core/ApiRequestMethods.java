package com.suhu.android.core;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by suhu on 2017/4/8.
 */

public class ApiRequestMethods {

    /**
     *@method 请求方法
     *@author suhu
     *@time 2017/4/8 15:09
     *
    */
    public static void getToken(Context context, String url, String userId , String name, String portraitUri , final ApiRequestFactory.HttpCallBackListener httpCallBackListener, boolean isShowDialog){
        Map<String ,String> map = new HashMap<>();
        map.put("userId",userId);
        map.put("name",name);
        map.put("portraitUri",portraitUri);
        ApiRequestFactory.postJson(context,url,map,httpCallBackListener,isShowDialog);
    }



}
