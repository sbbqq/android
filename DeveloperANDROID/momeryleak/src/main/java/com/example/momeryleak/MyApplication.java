package com.example.momeryleak;

import android.app.Application;
import android.util.Log;

import com.example.momeryleak.DaggerDemo.ApplicationComponent;
import com.example.momeryleak.DaggerDemo.DaggerApplicationComponent;

import dagger.internal.DaggerCollections;


/**
 * Created by alone-nine-sword on 20-1-8.
 */

public class MyApplication extends Application {
    ApplicationComponent applicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("myAppliction-onCreate","***********");
        applicationComponent= DaggerApplicationComponent.create();
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.e("onApp-onLowMem","***********");
    }


}
