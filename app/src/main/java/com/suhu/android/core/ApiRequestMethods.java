package com.suhu.android.core;

import android.content.Context;
import android.graphics.Bitmap;

import com.suhu.android.application.SoftwareApp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    /**
     * face++面部识别
     *
     * @param context
     * @param fileUrl
     * @param callBackListener
     */
    public static void detect(Context context, String fileUrl, final ApiRequestFactory.HttpCallBackListener callBackListener){
        Map<String,String> map = new HashMap<>();
        map.put("api_key", SoftwareApp.API_KEY);
        map.put("api_secret",SoftwareApp.API_SECRET);

        SubmitFile submitFile = new SubmitFile();
        submitFile.setFileName("face");
        submitFile.setName("image_file");
        submitFile.setFile(new File(fileUrl));
        List<SubmitFile> list = new ArrayList<>();
        list.add(submitFile);
        ApiRequestFactory.postMixture(context,ApiUrl.DETECT,list,map,callBackListener,true);
    }



}
