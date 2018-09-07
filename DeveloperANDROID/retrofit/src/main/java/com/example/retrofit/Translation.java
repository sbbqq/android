package com.example.retrofit;

import android.util.Log;

/**
 * Created by wqq on 18-7-25.
 */

public class Translation {
    private int status;

    private content content;
    private static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }

    //定义 输出返回数据 的方法
    public void show() {
        Log.e("status",status+"");
        Log.e("status",content.from+"");
        Log.e("status",content.to+"");
        Log.e("status",content.vendor+"");

        System.out.println(content.out);
        System.out.println(content.errNo);
    }
}