package com.haier.healthcenter.tvcesawe;

import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by wqq on 19-7-17.
 */


public class SimpleServer extends NanoHTTPD {
    public AssetManager asset_mgr;

    private static String TAG = SimpleServer.class.toString();

    public SimpleServer() {
// 端口是8088，也就是说要通过http://127.0.0.1:8088来访当问
        super(8888);
    }

    public Response serve(String uri, Method method, Map<String, String> header, Map<String, String> parameters,
                          Map<String, String> files) {
        Log.e(TAG, "SERVE :: URI " + uri);
        final StringBuilder buf = new StringBuilder();
        for (Map.Entry<String, String> kv : header.entrySet())
            buf.append(kv.getKey() + " : " + kv.getValue() + "\n");
        InputStream mbuffer = null;
        String file_name = uri.substring(1);
// 默认的页面名称设定为index.html
        if (file_name.equalsIgnoreCase("")) {
            file_name = "sound/index.html";
        } else {
            file_name = "sound/" + file_name;
        }

//        try {
//            if (uri != null) {
//                if (file_name.contains(".js")) {
//                    mbuffer = asset_mgr.open(file_name);
//                    return new NanoHTTPD.Response(Response.Status.OK, "application/javascript", mbuffer,
//                            (long) mbuffer.available());
//                } else if (file_name.contains(".css")) {
//                    mbuffer = asset_mgr.open(file_name);
//                    return new NanoHTTPD.Response(Response.Status.OK, "text/css", mbuffer, (long) mbuffer.available());
//                } else if (file_name.contains(".png")) {
//                    mbuffer = asset_mgr.open(file_name);
//// HTTP_OK = "200 OK" or HTTP_OK = Status.OK;(check comments)
//                    return new NanoHTTPD.Response(Response.Status.OK, "image/png", mbuffer, (long) mbuffer.available());
//                } else if (file_name.contains(".jpg") || file_name.contains(".jpeg")) {
//                    mbuffer = asset_mgr.open(file_name);
//                    return new NanoHTTPD.Response(Response.Status.OK, "image/jpeg", mbuffer, (long) mbuffer.available());
//                } else if (file_name.contains(".ogv")) {
//                    mbuffer = asset_mgr.open(file_name);
//                    return new NanoHTTPD.Response(Response.Status.OK, "video/ogg", mbuffer, (long) mbuffer.available());
//                } else if (file_name.contains(".ogg")) {
//                    mbuffer = asset_mgr.open(file_name);
//                    return new NanoHTTPD.Response(Response.Status.OK, "application/x-ogg", mbuffer, (long) mbuffer.available());
//                } else if (file_name.contains(".txt")) {
//                    mbuffer = asset_mgr.open(file_name);
//                    return new NanoHTTPD.Response(Response.Status.OK, "text/plain", mbuffer, (long) mbuffer.available());
//                } else if (file_name.contains(".xml")) {
//                    mbuffer = asset_mgr.open(file_name);
//                    return new NanoHTTPD.Response(Response.Status.OK, "text/xml", mbuffer, (long) mbuffer.available());
//                } else if (file_name.contains(".json")) {
//                    mbuffer = asset_mgr.open(file_name);
//                    return new NanoHTTPD.Response(Response.Status.OK, "text/json", mbuffer, (long) mbuffer.available());
//                } else if (file_name.contains(".mp3")) {
//                    mbuffer = asset_mgr.open(file_name);
//                    return new NanoHTTPD.Response(Response.Status.OK, "audio/mpeg", mbuffer, (long) mbuffer.available());
//                } else if (file_name.contains(".mp4")) {
//                    mbuffer = asset_mgr.open(file_name);
//                    return new NanoHTTPD.Response(Response.Status.OK, "video/mp4", mbuffer, (long) mbuffer.available());
//                } else if (uri.contains("/mnt/sdcard")) {
//// Log.d(TAG,"request for media on sdCard "+uri);
//// File request = new File(file_name);
//// mbuffer = new FileInputStream(request);
//// FileNameMap fileNameMap = URLConnection.getFileNameMap();
//// String mimeType = fileNameMap.getContentTypeFor(file_name);
////
//// Response streamResponse = new Response(Status.OK, mimeType, mbuffer,
//// (long)mbuffer.available());
//// Random rnd = new Random();
//// String etag = Integer.toHexString( rnd.nextInt() );
//// streamResponse.addHeader( "ETag", etag);
//// streamResponse.addHeader( "Connection", "Keep-alive");
//// return streamResponse;
//                } else {
//                    mbuffer = asset_mgr.open(file_name);
//                    return new NanoHTTPD.Response(Response.Status.OK, MIME_HTML, mbuffer, (long) mbuffer.available());
//                }
//            }
//        } catch (IOException e) {
//            Log.d(TAG, "Error opening file " + file_name +" "+ e.getMessage());
//        }
        return null;
    }
}
