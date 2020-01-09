package com.example.momeryleak;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;


/**
 * Created by alone-nine-sword on 20-1-9.
 */

public class LifecycleTry implements LifecycleObserver{

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start(){
       Log.e("life-onStart","*****");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop(){
        Log.e("lifce-onStop","*****");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(){
        Log.e("lifce-onDestroy","*****");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(){
        Log.e("lifce-onResume","*****");
    }

    public LifecycleTry() {
        Log.e("stuct","LifecycleTry()");
    }
}
