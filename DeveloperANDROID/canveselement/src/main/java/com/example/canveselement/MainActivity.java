package com.example.canveselement;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webview);
        imgBack=(ImageView)findViewById(R.id.imgback);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.goBack();
            }
        });
      // webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
     webView.getSettings().setDomStorageEnabled(true);

        WebSettings webSettings = webView.getSettings();
//设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
//设置可以访问文件
        webSettings.setAllowFileAccess(true);
//设置支持缩放
        webSettings.setBuiltInZoomControls(true);

        webSettings.setAppCacheEnabled(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.supportMultipleWindows();
        webSettings.setAllowContentAccess(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
       webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);

        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
      webView.addJavascriptInterface(this, "user");//http://123.103.113.201:8085   http://thealth.haier.net/login
       webView.loadUrl("https://www.haier.com/cn/smarthome/information/advisory/W020180918805702974131.jpg");//http://10.130.95.112:8088/test //"http://10.190.0.99:8088/test"  //http://healthrecord.haier.net  //http://47.93.57.7/Myfirst/HelloWorld.html
        //webView.loadUrl("http://my.fridge.com/build/index.html");
        webView.setWebViewClient(new WebViewClient() {

                                     @Override
                                     public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                         Log.e("shouldouver",url);
                                         WebView.HitTestResult hit = webView.getHitTestResult();
                                         int hitType = hit.getType();

                                         if (hitType != WebView.HitTestResult.UNKNOWN_TYPE) {
                                              // view.loadUrl(url);
                                             //这里执行自定义的操作
                                             return true;

                                         } else {

                                             //重定向时hitType为0 ,执行默认的操作
                                             Log.e("rego","********************");
                                             //view.loadUrl(url);
                                             return false;
                                         }
                                         // view.loadUrl(url);
                                           //   return  true;
                                     }


                                     @Override
                                     public void onPageFinished(WebView view, String url) {
                                         super.onPageFinished(view, url);
                                         Log.e("onPagefinish",url+"");
                                         //view.loadUrl(url);
                                     }

                                     @Override
                                     public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                         super.onPageStarted(view, url, favicon);
                                         Log.e("onPagestarted","*****************");
                                     }
                                 }


                                 );






    }




    @JavascriptInterface
    public void getFamilyDetail() {
        Log.e("getfrom html","**********************************8");
        //此处应该定义常量对应，同时提供给web页面编写者
//        if (TextUtils.equals(activityName, "a")) {
//
//            startActivity(new Intent(this, AActivity.class));
//        } else {
//            Intent intent=new Intent(MainActivity.this,BActivity.class);
//
//            intent.putExtra("para",activityName);
//            startActivity(intent);
//        }
        //finish();

     webView.post(new Runnable() {
         @Override
         public void run() {
             webView.loadUrl("javascript:getFamilyDetailCallBack('hello from android')");
         }
     });
    }

    @JavascriptInterface
    public void secondWay(String urlpara){

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK){
           Log.e("back","******************");
        }

        return false;
    }
}