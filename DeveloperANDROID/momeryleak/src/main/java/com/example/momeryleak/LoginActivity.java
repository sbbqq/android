package com.example.momeryleak;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.momeryleak.DaggerDemo.LoginViewModel;

import javax.inject.Inject;

public class LoginActivity extends Activity  implements LifecycleOwner{
    @Inject
    LoginViewModel loginViewModel,loginViewModel2;

    LifecycleTry lifecycleTry;
    LifecycleRegistry lifecycleRegistry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication)getApplicationContext()).applicationComponent.injectAc(this);

        setContentView(R.layout.activity_login);
        loginViewModel.LogMess();
        loginViewModel.getUserRepository().LogMess();
          lifecycleRegistry=new LifecycleRegistry(this);

          lifecycleTry=new LifecycleTry();
        this.getLifecycle().addObserver(lifecycleTry);
        lifecycleRegistry.markState(Lifecycle.State.CREATED);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifecycleRegistry.markState(Lifecycle.State.STARTED);
    }


    @Override
    protected void onResume() {
        super.onResume();
        lifecycleRegistry.markState(Lifecycle.State.RESUMED);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //lifecycleRegistry.markState(Lif);
    }
}
