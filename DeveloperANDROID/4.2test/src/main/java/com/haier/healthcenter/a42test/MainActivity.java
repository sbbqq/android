package com.haier.healthcenter.a42test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.http.SslError;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity{

    WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniview();
    }
    private void iniview(){
        mWebView=(WebView)findViewById(R.id.webview);
       WebSettings webSettings = mWebView.getSettings();
       webSettings.setJavaScriptEnabled(true);
      // webSettings.setBlockNetworkImage(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
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
        //webSettings.setLoadsImagesAutomatically(false);
        //适配屏幕
        webSettings.setLoadWithOverviewMode(true);
        //不使用缓存：
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
       // mWebView.setBackgroundColor(Color.BLACK);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(false);
       // mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        mWebView.setWebChromeClient(new WebChromeClient()
        { public void onConsoleMessage(String message, int lineNumber, String sourceID) {
            Log.e("MyApplication", message + " -- From line " + lineNumber + " of " + sourceID);

        }
        });
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onReceivedSslError(WebView view,
//                                           SslErrorHandler handler, SslError error) {
//                // TODO Auto-generated method stub
//                // handler.cancel();// Android默认的处理方式
//                handler.proceed();// 接受所有网站的证书
//                // handleMessage(Message msg);// 进行其他处理
//            }
//        });

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("url",url);
                return false;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });//http://healthrecord.haier.net/build/index.html" //http://47.93.57.7/Myfirst/hello.html
        mWebView.loadUrl("http://47.93.57.7/Myfirst/hello.html");//file:///android_asset/tv-ces-demo/index2.html
        //mWebView.getSettings().setBlockNetworkImage(false);
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.e("onkey","**************");
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK ) { //表示按返回键 时//的操作
                        //mWebView.goBack();
                        mWebView.loadUrl("http://47.93.57.7/Myfirst/hello.html");
                        // 后退 //webview.goForward();//前进 return true; //已处理 }
                        return true;
                    }
                }
                return false;



            }
        });
        mWebView.reload();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK){
            mWebView.loadUrl("file:///android_asset/tv-ces-demo/index2.html");
        }
        Log.e("onkeyDonw","***********");
        mWebView.loadUrl("file:///android_asset/tv-ces-demo/index2.html");
        return false;
    }
}
