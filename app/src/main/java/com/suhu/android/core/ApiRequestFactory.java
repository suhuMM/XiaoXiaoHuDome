package com.suhu.android.core;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dmax.dialog.SpotsDialog;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by suhu on 2017/4/8.
 */

public class ApiRequestFactory {
    private static SpotsDialog loadingDialog;
    private static ProgressDialog progressDialog;


    public interface HttpCallBackListener {
        /**
         *@param response
         *@param url
         *@param id
         *
        */
        void onSuccess(String response, String url, int id);
        /**
         *@param call
         *@param e
         *@param id
         *
         */
        void failure(Call call, Exception e, int id);
    }


    /**
     *@method 判断网络
     *@author suhu
     *@time 2017/4/8 11:32
     *@param context
     *
    */
    private static void init(Context context) {
        if (!NetWorkUtils.isConnect(context)) {
            ToastUtils.disPlayShort(context, "网络连接已断开，请检查网络连接");
            return;
        }
    }


    /**
     *@method get异步请求
     *@author suhu
     *@time 2017/4/8 9:53
     * @param context
     * @param url
     * @param params
     * @param httpCallBackListener
     * @param isShowDialog
    */
    public static void get(final Context context, final String url, Map<String,String> params , final HttpCallBackListener httpCallBackListener, boolean isShowDialog){
        init(context);
        if (isShowDialog) {
            if (loadingDialog == null) {
                loadingDialog = new SpotsDialog(context,"正在加载数据");
            }
            loadingDialog.dismiss();
            loadingDialog.show();
        }
        OkHttpUtils
                .get()
                .url(ApiUrl.URL+url)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                            loadingDialog = null;
                        }
                        ToastUtils.disPlayShort(context, "网络加载出现异常");
                        if (httpCallBackListener !=null) {
                            httpCallBackListener.failure(call, e, id);
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                            loadingDialog = null;
                        }
                        if (response.contains("用户认证系统")){
                            ToastUtils.disPlayShort(context, "网络连接已断开，请检查网络连接");
                            return;
                        }
                        if (httpCallBackListener !=null) {
                            httpCallBackListener.onSuccess(response,url, id);
                        }
                    }
                });
    }

    /**
     *@method 同步Get方法
     *@author suhu
     *@time 2017/4/8 12:10
     *@param context
     *@param url
     *
    */
    public static Response getSynchronous(final Context context, String url){
        init(context);
        Response response = null;
        try {
            response = OkHttpUtils
                    .get()
                    .url(ApiUrl.URL+url)
                    .tag(context)
                    .build()
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }



    /**
     *@method post请求
     *@author suhu
     *@time 2017/4/8 9:53
     * @param context
     * @param url
     * @param params
     * @param httpCallBackListener
     * @param isShowDialog
     */
    public static void post(final Context context, final String url, Map<String,String> params , final HttpCallBackListener httpCallBackListener, boolean isShowDialog){
        init(context);
        if (isShowDialog) {
            if (loadingDialog == null) {
                loadingDialog = new SpotsDialog(context,"正在加载数据");
            }
            loadingDialog.dismiss();
            loadingDialog.show();
        }
        OkHttpUtils
                .post()
                .url(ApiUrl.FACE+url)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                            loadingDialog = null;
                        }
                        if (httpCallBackListener !=null) {
                            httpCallBackListener.failure(call, e, id);
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                            loadingDialog = null;
                        }
                        if (response.contains("用户认证系统")){
                            ToastUtils.disPlayShort(context, "网络连接已断开，请检查网络连接");
                            return;
                        }
                        if (httpCallBackListener !=null) {
                            httpCallBackListener.onSuccess(response,url, id);
                        }
                    }
                });
    }

    /**
     *@method postJson
     *@author suhu
     *@time 2017/4/8 10:32
     * @param context
     * @param url
     * @param params
     * @param httpCallBackListener
     * @param isShowDialog
    */
    public static void postJson(final Context context, final String url, Map<String,String> params , final HttpCallBackListener httpCallBackListener, boolean isShowDialog){
        init(context);
        if (isShowDialog) {
            if (loadingDialog == null) {
                loadingDialog = new SpotsDialog(context,"正在加载数据");
            }
            loadingDialog.dismiss();
            loadingDialog.show();
        }

        JSONObject localJSONObject = new JSONObject();
        Set set = params.entrySet();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            try {
                localJSONObject.put((String) entry.getKey(),entry.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        OkHttpUtils
                .postString()
                .url(ApiUrl.URL+url)
                .content("content=" + localJSONObject.toString())
                .mediaType(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                            loadingDialog = null;
                        }
                        if (httpCallBackListener !=null) {
                            httpCallBackListener.failure(call,e,id);
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                            loadingDialog = null;
                        }
                        if (response.contains("用户认证系统")){
                            ToastUtils.disPlayShort(context, "网络连接已断开，请检查网络连接");
                            return;
                        }
                        if (httpCallBackListener !=null){
                            httpCallBackListener.onSuccess(response,url,id);
                        }
                    }
                });
    }


    /**
     *
     * 表单上传文件
     *
     * @param context
     * @param url
     * @param submitFiles
     * @param params
     * @param httpCallBackListener
     * @param isShowDialog
     */

    public static void postMixture(final Context context, final String url, List<SubmitFile> submitFiles, Map<String,String> params, final HttpCallBackListener httpCallBackListener, boolean isShowDialog){

        init(context);
        if (isShowDialog) {
            if (loadingDialog == null) {
                loadingDialog = new SpotsDialog(context,"正在加载数据");
            }
            loadingDialog.dismiss();
            loadingDialog.show();
        }
        PostFormBuilder builder = OkHttpUtils.post();
        if (submitFiles!=null){
            for (SubmitFile submitFile : submitFiles) {
                builder.addFile(submitFile.getName(),submitFile.getFileName(),submitFile.getFile());
            }
        }
        builder.url(ApiUrl.FACE+url)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                            loadingDialog = null;
                        }
                        if (httpCallBackListener !=null) {
                            httpCallBackListener.failure(call,e,id);
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                            loadingDialog = null;
                        }
                        if (response.contains("用户认证系统")){
                            ToastUtils.disPlayShort(context, "网络连接已断开，请检查网络连接");
                            return;
                        }
                        if (httpCallBackListener !=null){
                            httpCallBackListener.onSuccess(response,url,id);
                        }
                    }
                });

    }



    /**
     *@method uploadFile 上传文件
     *@author suhu
     *@time 2017/4/8 10:37
     * @param context
     * @param url
     * @param file
     * @param httpCallBackListener
     *
    */
    public static void uploadFile(final Context context, final String url, File file, final HttpCallBackListener httpCallBackListener) {
        init(context);
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
        }
        progressDialog.setProgress(0);
        progressDialog.show();
        OkHttpUtils
                .postFile()
                .url(ApiUrl.URL+url)
                .file(file)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (progressDialog != null){
                            progressDialog.dismiss();
                            progressDialog=null;
                        }

                        ToastUtils.disPlayShort(context, "网络加载出现异常");
                        if (httpCallBackListener != null) {
                            httpCallBackListener.failure(call,e,id);
                        }
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        progressDialog.setProgress((int) progress);
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        if (progressDialog != null){
                            progressDialog.dismiss();
                            progressDialog=null;
                        }

                        if (httpCallBackListener != null) {
                            httpCallBackListener.onSuccess(response.toString(),url,id);
                        }
                    }
                });
    }





    /**
     *@method 文件下载
     *@author suhu
     *@time 2017/4/8 14:08
     * @param context
     * @param url
     * @param destFileDir
     * @param destFileName
     * @param isApp
     *
    */
    public static void download(final Context context, String url, String destFileDir, String destFileName, final boolean isApp) {
        init(context);
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
        }
        progressDialog.setProgress(0);
        progressDialog.show();
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new FileCallBack(destFileDir, destFileName) {

                    @Override
                    public void onBefore(Request request, int id) {
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        progressDialog.setProgress((int) progress);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (progressDialog != null){
                            progressDialog.dismiss();
                            progressDialog=null;
                        }

                        ToastUtils.disPlayShort(context, "网络加载出现异常");
                    }

                    @Override
                    public void onResponse(File file, int id) {
                        if (progressDialog != null){
                            progressDialog.dismiss();
                            progressDialog=null;
                        }

                        if (isApp) {
                            //AppUtils.installApp(context, file);
                        } else {
                            openFile(context, file);
                        }
                    }
                });
    }


    /**
     *@method 通过tag取消请求
     *@author suhu
     *@time 2017/4/8 14:05
     *@param tag
     *
    */
    public static void canncelHttp(Object tag) {
        OkHttpUtils.getInstance().cancelTag(tag);
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }


    /**
     *@method 通过url取消请求
     *@author suhu
     *@time 2017/4/8 14:07
     *@param url
     *
    */
    public static void canncelHttp(String url){
        OkHttpUtils
                .get()
                .url(ApiUrl.URL+url)
                .build()
                .cancel();
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }



    /**
     *@method 打开文件
     *@author suhu
     *@time 2017/4/8 11:05
     *@param context
     *@param file
     *
    */

    private static void openFile(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //设置intent的Action属性
        intent.setAction(Intent.ACTION_VIEW);
        //获取文件file的MIME类型
        String type = getMIMEType(file);
        //设置intent的data和Type属性。
        intent.setDataAndType(/*uri*/Uri.fromFile(file), type);
        //跳转
        context.startActivity(intent);
    }



    /**
     *@method 根据文件后缀名获得对应的MIME类型
     *@author suhu
     *@time 2017/4/8 11:05
     *@param file
     *
    */
    private static String getMIMEType(File file) {
        String type = "*/*";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        /* 获取文件的后缀名*/
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if (end == "") {
            return type;
        }
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (int i = 0; i < MIME_MAP_TABLE.length; i++) {
            //MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
            if (end.equals(MIME_MAP_TABLE[i][0])) {
                type = MIME_MAP_TABLE[i][1];
            }
        }
        return type;
    }

    private static final String[][] MIME_MAP_TABLE = {
            //{后缀名，MIME类型}
            {".3gp", "video/3gpp"},
            {".apk", "application/vnd.android.package-archive"},
            {".asf", "video/x-ms-asf"},
            {".avi", "video/x-msvideo"},
            {".bin", "application/octet-stream"},
            {".bmp", "image/bmp"},
            {".c", "text/plain"},
            {".class", "application/octet-stream"},
            {".conf", "text/plain"},
            {".cpp", "text/plain"},
            {".doc", "application/msword"},
            {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls", "application/vnd.ms-excel"},
            {".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".exe", "application/octet-stream"},
            {".gif", "image/gif"},
            {".gtar", "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h", "text/plain"},
            {".htm", "text/html"},
            {".html", "text/html"},
            {".jar", "application/java-archive"},
            {".java", "text/plain"},
            {".jpeg", "image/jpeg"},
            {".jpg", "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log", "text/plain"},
            {".m3u", "audio/x-mpegurl"},
            {".m4a", "audio/mp4a-latm"},
            {".m4b", "audio/mp4a-latm"},
            {".m4p", "audio/mp4a-latm"},
            {".m4u", "video/vnd.mpegurl"},
            {".m4v", "video/x-m4v"},
            {".mov", "video/quicktime"},
            {".mp2", "audio/x-mpeg"},
            {".mp3", "audio/x-mpeg"},
            {".mp4", "video/mp4"},
            {".mpc", "application/vnd.mpohun.certificate"},
            {".mpe", "video/mpeg"},
            {".mpeg", "video/mpeg"},
            {".mpg", "video/mpeg"},
            {".mpg4", "video/mp4"},
            {".mpga", "audio/mpeg"},
            {".msg", "application/vnd.ms-outlook"},
            {".ogg", "audio/ogg"},
            {".pdf", "application/pdf"},
            {".png", "image/png"},
            {".pps", "application/vnd.ms-powerpoint"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".prop", "text/plain"},
            {".rc", "text/plain"},
            {".rmvb", "audio/x-pn-realaudio"},
            {".rtf", "application/rtf"},
            {".sh", "text/plain"},
            {".tar", "application/x-tar"},
            {".tgz", "application/x-compressed"},
            {".txt", "text/plain"},
            {".wav", "audio/x-wav"},
            {".wma", "audio/x-ms-wma"},
            {".wmv", "audio/x-ms-wmv"},
            {".wps", "application/vnd.ms-works"},
            {".xml", "text/plain"},
            {".z", "application/x-compress"},
            {".zip", "application/x-zip-compressed"},
            {"", "*/*"}
    };
}
