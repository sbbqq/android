package com.haier.healthcenter.tvcesawe

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.webkit.WebView
import fi.iki.elonen.NanoHTTPD
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.io.InputStream



class Main3Activity : AppCompatActivity() {


    private var context: Context? = null
    private var webServer: WebServer? = null
    private val PORT = 8888

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.context = applicationContext
        this.webServer = WebServer(applicationContext)
        try {
            this.webServer?.start()
            Log.e("start","*****************");
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        this.webview.loadUrl("http://127.0.0.1:8888/sound/index.html")
        Log.e("id:", this.webview.getId().toString());


    }

    override fun onDestroy() {
        super.onDestroy()
        if (this.webServer != null) {
            this.webServer?.stop()
        }
    }

    public inner class WebServer(private val context: Context) : NanoHTTPD(PORT) {

        override fun serve(session: IHTTPSession): NanoHTTPD.Response {
            try {
                var uri = session.uri
                if ("/".equals(uri)) {
                    uri = "index.html"
                }

                var filename = uri
                Log.e("uri",uri);
                if (uri.substring(0, 1).equals("/")) {
                    filename = filename.substring(1)
                }
                Log.e("AppInfo", filename)

                val asssets = context.getResources().getAssets()
                var fis: InputStream? = null
                try {
                    fis = asssets.open(filename)
                } catch (e: Exception) {
                    Log.d("AppErr", "File openning failed")
                }

                if (uri.endsWith(".ico")) {
                    return NanoHTTPD.newChunkedResponse(Response.Status.OK, "image/x-icon", fis)
                } else if (uri.endsWith(".png") || uri.endsWith(".PNG")) {
                    return NanoHTTPD.newChunkedResponse(Response.Status.OK, "image/png", fis)
                } else if (uri.endsWith(".jpg") || uri.endsWith(".JPG") || uri.endsWith(".jpeg") || uri.endsWith(".JPEG")) {
                    return NanoHTTPD.newChunkedResponse(Response.Status.OK, "image/jpeg", fis)
                } else if (uri.endsWith(".js")) {
                    return NanoHTTPD.newChunkedResponse(Response.Status.OK, "application/javascript", fis)
                } else if (uri.endsWith(".css")) {
                    return NanoHTTPD.newChunkedResponse(Response.Status.OK, "text/css", fis)
                } else if (uri.endsWith(".html") || uri.endsWith(".htm")) {
                    return NanoHTTPD.newChunkedResponse(Response.Status.OK, "text/html", fis)
                } else if (uri.endsWith(".map")) {
                    return NanoHTTPD.newChunkedResponse(Response.Status.OK, "application/json", fis)
                } else {
                    return NanoHTTPD.newFixedLengthResponse(Response.Status.NOT_FOUND, "text/plain", uri)
                }
            } catch (ioe: IOException) {
                ioe.printStackTrace()
                return NanoHTTPD.newFixedLengthResponse(Response.Status.INTERNAL_ERROR, "text/plain", ioe.localizedMessage)
            }
        }
    }
}

