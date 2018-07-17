package com.example.canveselement;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BActivity extends AppCompatActivity {
  WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     String para=   getIntent().getStringExtra("para");
        Log.e("para","***************"+para);
        setContentView(R.layout.activity_b);


        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setBackgroundColor(ContextCompat.getColor(this,android.R.color.transparent));
        webView.setBackgroundResource(R.color.colorAccent);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
       // webView.addJavascriptInterface(this, "nativeMethod");
        webView.loadUrl(para);//http://10.130.95.112:8088/test //"http://10.190.0.99:8088/test"  //http://healthrecord.haier.net

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
}
