package com.example.appdatastructurelearn.LifeCycleLearn;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import static android.arch.lifecycle.Lifecycle.Event.ON_CREATE;


/**
 * Created by alone-nine-sword on 20-5-27.
 */

public class TestLifeCycle implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(){
        Log.e("onCreate","*************");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestory(){
        Log.e("onDestroy","*************");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(){
        Log.e("onStart","*************");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(){
        Log.e("onStop","*************");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(){
        Log.e("onResume","*************");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny(){
        Log.e("onAny","*************");
    }

    public void show(TextView textView,String state)
    {
       textView.setText(state);
    }




}
