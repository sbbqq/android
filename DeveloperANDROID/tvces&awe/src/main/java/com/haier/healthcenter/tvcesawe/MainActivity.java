package com.haier.healthcenter.tvcesawe;

import android.app.Activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
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
        //适配屏幕
        webSettings.setLoadWithOverviewMode(true);
        //不使用缓存：
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebView.setBackgroundColor(Color.BLACK);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        //mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

//        mWebView.setWebChromeClient(new WebChromeClient()
//        { public void onConsoleMessage(String message, int lineNumber, String sourceID) {
//            Log.e("MyApplication", message + " -- From line " + lineNumber + " of " + sourceID);
//
//        }
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
        });
        mWebView.loadUrl("file:///android_asset/tv-ces-demo/index.html");
        mWebView.getSettings().setBlockNetworkImage(false);
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.e("onkey","**************");
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK ) { //表示按返回键 时的操作
                        //mWebView.goBack();
                        mWebView.loadUrl("file:///android_asset/tv-ces-demo/index.html");
                        // 后退 //webview.goForward();//前进 return true; //已处理 }
                      return true;
                    }
                }
                        return false;



            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK){
            mWebView.loadUrl("file:///android_asset/tv-ces-demo/index.html");
        }
        Log.e("onkeyDonw","***********");
        mWebView.loadUrl("file:///android_asset/tv-ces-demo/index.html");
        return false;
    }
}
