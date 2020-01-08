package com.haier.healthcenter.tvcesawe;

import android.content.Context;
import android.util.Log;
import android.webkit.WebView;


import com.yanzhenjie.andserver.AndServer;
import com.yanzhenjie.andserver.Server;
import com.yanzhenjie.andserver.website.WebSite;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * Created by wqq on 19-7-17.
 */

public class ServerManager {

    private Server mServer;
    Context context;
    AndServer andServer;
    WebSite webSite;
    WebOprate webOprate;



    public ServerManager(Context context,WebSite webSite,WebOprate webOprate) {
        this.context = context;
        this.webSite=webSite;
        this.webOprate=webOprate;
        ServerManager();

    }

    /**
     * Create server.
     */
    public void ServerManager() {


             andServer = new AndServer.Build()
                    .port(8888)
                     .website(webSite)
                    .listener(new Server.Listener() {
                        @Override
                        public void onStarted() {
                            Log.e("***********","onstated");
                            webOprate.load();
                        }

                        @Override
                        public void onStopped() {
                            Log.e("***********","onstoped");
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.e("***********","onError");
                        }
                    })
                    .build();

                 mServer=andServer.createServer();

    }

    /**
     * Start server.
     */
    public void startServer() {
        if (mServer.isRunning()) {
            // TODO The server is already up.

        } else {
            Log.e("start","*********gooo*****");
            mServer.start();
        }
    }

    /**
     * Stop server.
     */
    public void stopServer() {
        if (mServer.isRunning()) {
            mServer.stop();
        } else {
            Log.e("AndServer", "The server has not started yet.");
        }
    }

}
