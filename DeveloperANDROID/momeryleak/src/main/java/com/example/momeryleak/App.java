package com.example.momeryleak;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by alone-nine-sword on 20-1-3.
 */

public class App extends Application {
;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("application-onCreate","***********8");
    }
}