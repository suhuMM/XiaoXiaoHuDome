package com.suhu.android.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.suhu.android.R;
import com.suhu.android.base.activity.BaseSlidingActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author suhu
 * @data 2017/9/11.
 * @description
 */

public class HtmlActivity extends BaseSlidingActivity{
    @BindView(R.id.webView)
    WebView webView;

    private static final String URL = "file:///android_asset/test.html";

    @Override
    public int showContView() {
        return R.layout.activity_html;
    }

    @Override
    public void setActionBar() {
        title.setText("H5交互页面");
    }

    @Override
    public void setCreateView(Bundle savedInstanceState) {
        initView();

    }

    private void initView() {

        webView.loadUrl(URL);
        WebSettings webSettings = webView.getSettings();

        //设置Android调用H5页方法，例如在onClick方法：
        webSettings.setJavaScriptEnabled(true);

        //设置web调用Android 方法，具体如何调用要在H5页面书写
        webView.addJavascriptInterface(new JsInterface(),"android");

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
               if (url.equals(URL)){
                   Toast.makeText(HtmlActivity.this,"点击跳转",Toast.LENGTH_SHORT).show();
                   HtmlActivity.this.startActivity(new Intent(HtmlActivity.this,LoginActivity.class));
                   return true;
               }else {
                   view.loadUrl(url);
                   return false;
               }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @OnClick(R.id.button)
    public void onClick(View v){
        webView.evaluateJavascript("sum(1,2)", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                Toast.makeText(HtmlActivity.this,"调用h5方法返回值"+s,Toast.LENGTH_SHORT).show();
            }
        });


    }


    public class JsInterface{
        @JavascriptInterface
        public String back(){
            return "android welcome you";
        }
    }



}
