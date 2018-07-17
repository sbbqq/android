package com.example.canveselement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webview);
      //  webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
       // webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.addJavascriptInterface(this, "nativeMethod");
        webView.loadUrl("http://47.93.57.7:8088");//http://10.130.95.112:8088/test //"http://10.190.0.99:8088/test"  //http://healthrecord.haier.net  //http://47.93.57.7/Myfirst/HelloWorld.html

        webView.setWebViewClient(new WebViewClient() {

                                     @Override
                                     public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                         WebView.HitTestResult hit = webView.getHitTestResult();
                                         int hitType = hit.getType();
//
//                                         if (hitType != WebView.HitTestResult.UNKNOWN_TYPE) {
//                                               view.loadUrl(url);
//                                             //这里执行自定义的操作
//                                             return true;
//
//                                         } else {
//
//                                             //重定向时hitType为0 ,执行默认的操作
//                                             view.loadUrl(url);
//                                             return false;
//                                         }
                                          view.loadUrl(url);
                                              return  true;
                                     }

                                 });






    }




    @JavascriptInterface
    public void toActivity(String activityName) {
        Log.e("getfrom html",activityName);
        //此处应该定义常量对应，同时提供给web页面编写者
        if (TextUtils.equals(activityName, "a")) {

            startActivity(new Intent(this, AActivity.class));
        } else {
            Intent intent=new Intent(MainActivity.this,BActivity.class);

            intent.putExtra("para",activityName);
            startActivity(intent);
        }
        //finish();

    }

    @JavascriptInterface
    public void secondWay(String urlpara){

    }
}