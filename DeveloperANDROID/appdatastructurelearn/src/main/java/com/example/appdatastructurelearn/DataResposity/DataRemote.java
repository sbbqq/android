package com.example.appdatastructurelearn.DataResposity;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.util.TimeUtils;

import com.example.appdatastructurelearn.Util.TimeTool;
import com.example.appdatastructurelearn.ViewModelAndLiveDataLearn.HelloViewModel;

/**
 * Created by alone-nine-sword on 20-5-29.
 */

public class DataRemote {
    private static boolean Active=true;

    public static  void moniRemote(final  HelloViewModel helloViewModel){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (Active) {
                    Log.e("子线程更新update","******");
                    helloViewModel.getCitys().postValue(Math.random()+"");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    public static boolean isActive() {
        return Active;
    }

    public static void setActive(boolean active) {
        Active = active;
    }
}
