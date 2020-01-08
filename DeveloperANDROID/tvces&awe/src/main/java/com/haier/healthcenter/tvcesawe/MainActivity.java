package com.haier.healthcenter.tvcesawe;

import android.app.Activity;

import android.content.Context;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;



import com.yanzhenjie.andserver.website.AssetsWebsite;
import com.yanzhenjie.andserver.website.WebSite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Map;

import javax.net.ssl.SSLServerSocketFactory;

import fi.iki.elonen.NanoHTTPD;

public class MainActivity extends Activity implements WebOprate {
   WebView mWebView;
   SimpleServer simpleServer;
  WebServer webServer;
   Context context;
    WebSite webSite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context = getApplicationContext();
        //this.webServer = new WebServer(8888);
//        try {
//            webServer.start();
//            //simpleServer=new SimpleServer();
//           // simpleServer.start();
//            Log.e("start","*******************");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
        AssetManager mAssetManager = getAssets();
 webSite=new AssetsWebsite(mAssetManager, "sound");

        ServerManager serverManager=new ServerManager(this,webSite,this);
        serverManager.startServer();

//



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
//
       webSettings.setAppCacheEnabled(false);
       webSettings.setDomStorageEnabled(true);
       webSettings.supportMultipleWindows();
       webSettings.setAllowContentAccess(true);

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
       webSettings.setLoadWithOverviewMode(true);
       webSettings.setSavePassword(true);

       webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
       webSettings.setLoadsImagesAutomatically(true);
//        //适配屏幕
       webSettings.setLoadWithOverviewMode(true);
//        //不使用缓存：
//        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
//        mWebView.setBackgroundColor(Color.BLACK);
//
       mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
     mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient() {
                                        public boolean onConsoleMessage(ConsoleMessage cm) {
                                            Log.d("MyApplication", cm.message() + " -- From line "
                                                    + cm.lineNumber() + " of "
                                                    + cm.sourceId());
                                            return true;
                                        }
                                    });

     //  mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

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
                Log.e("onStarted","******************"+url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e("onFinished","******************"+url);


            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("shoudlOverurl",url);


                return false;
            }
        });//tvtest/index.html
       //file:///android_asset/tvtest/index.html//healthrecord.haier.net  http://healthrecord.haier.net/tv/index.html
        //mWebView.getSettings().setBlockNetworkImage(false);
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.e("onkey","**************");
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK ) { //表示按返回键 时的操作
                        mWebView.goBack();
                       // mWebView.loadUrl("file:///android_asset/tv-ces-demo/index.html");
                        // 后退 //webview.goForward();//前进 return true; //已处理 }
                      return true;
                    }
                }
                        return false;



            }
        });
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        if(keyCode==KeyEvent.KEYCODE_BACK){
//           // mWebView.loadUrl("file:///android_asset/tv-ces-demo/index.html");
//        }
//        Log.e("onkeyDonw","***********");
//        //mWebView.loadUrl("file:///android_asset/tv-ces-demo/index.html");
//        return false;
//    }

    @Override
    public void load() {
        //http://healthrecord.haier.net/tv/index.html http://127.0.0.1:8888/tv/index.html
        mWebView.loadUrl("http://127.0.0.1:8888/sound/index.html");
    }


//    private class WebServer extends NanoHTTPD {
//
//        public WebServer()
//        {
//            super(8080);
//        }
//
//        @Override
//        public Response serve(String uri, Method method,
//                              Map<String, String> header,
//                              Map<String, String> parameters,
//                              Map<String, String> files) {
//            String answer = "";
//            try {
//                // Open file from SD Card
//                File root = Environment.getExternalStorageDirectory();
//                FileReader index = new FileReader(root.getAbsolutePath() +
//                        "/www/index.html");
//                BufferedReader reader = new BufferedReader(index);
//                String line = "";
//                while ((line = reader.readLine()) != null) {
//                    answer += line;
//                }
//                reader.close();
//
//            } catch(IOException ioe) {
//                Log.e("Httpd", ioe.toString());
//            }
//
//
//            return new NanoHTTPD.Response(answer);
//        }
//    }

    public  class WebServer extends NanoHTTPD {

        public WebServer(int port) {
            super(port);
        }

        public Response serve(IHTTPSession session) {
            String  uri = session.getUri();
            if ("/".equals(uri)) {
                uri = "index.html";
            }

            String filename = uri;
            Log.e("uri",uri);
            if (uri.substring(0, 1).equals("/")) {
                filename = filename.substring(1);
            }
            Log.e("AppInfo", filename);

            AssetManager asssets = context.getResources().getAssets();
            InputStream fis = null;
            try {
                fis = asssets.open(filename);
            } catch ( Exception e ) {
                Log.d("AppErr", "File openning failed");
            }

            if (uri.endsWith(".ico")) {
                return NanoHTTPD.newChunkedResponse(Response.Status.OK, "image/x-icon", fis);
            } else if (uri.endsWith(".png") || uri.endsWith(".PNG")) {
                return NanoHTTPD.newChunkedResponse(Response.Status.OK, "image/png", fis);
            } else if (uri.endsWith(".jpg") || uri.endsWith(".JPG") || uri.endsWith(".jpeg") || uri.endsWith(".JPEG")) {
                return NanoHTTPD.newChunkedResponse(Response.Status.OK, "image/jpeg", fis);
            } else if (uri.endsWith(".js")) {
                return NanoHTTPD.newChunkedResponse(Response.Status.OK, "application/javascript", fis);
            } else if (uri.endsWith(".css")) {
                return NanoHTTPD.newChunkedResponse(Response.Status.OK, "text/css", fis);
            } else if (uri.endsWith(".html") || uri.endsWith(".htm")) {
                return NanoHTTPD.newChunkedResponse(Response.Status.OK, "text/html", fis);
            } else if (uri.endsWith(".map")) {
                return NanoHTTPD.newChunkedResponse(Response.Status.OK, "application/json", fis);
            } else {
                return NanoHTTPD.newFixedLengthResponse(Response.Status.NOT_FOUND, "text/plain", uri);
            }
        }
    }



}
