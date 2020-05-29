package com.example.appdatastructurelearn;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.appdatastructurelearn.DataResposity.DataRemote;
import com.example.appdatastructurelearn.DataResposity.DataRepsoity;
import com.example.appdatastructurelearn.LifeCycleLearn.TestLifeCycle;
import com.example.appdatastructurelearn.ViewModelAndLiveDataLearn.HelloViewModel;

public class LifeCycleActivity extends FragmentActivity implements LifecycleOwner{
LifecycleRegistry mlifecleRegistry;
TextView textViewState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        mlifecleRegistry=new LifecycleRegistry(this);
        mlifecleRegistry.markState(Lifecycle.State.CREATED);
        mlifecleRegistry.addObserver(new TestLifeCycle());
        textViewState=(TextView)findViewById(R.id.txState);
        HelloViewModel helloViewModel= ViewModelProviders.of(this).get(HelloViewModel.class);
        helloViewModel.getCitys().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.e("onChanged","****-->"+s);
                textViewState.setText(s);
            }
        });
        DataRepsoity.dataRemote.moniRemote(helloViewModel);
    }

    @Override
    public Lifecycle getLifecycle() {
        return mlifecleRegistry;
    }

    public LifeCycleActivity() {
        super();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mlifecleRegistry.markState(Lifecycle.State.STARTED);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mlifecleRegistry.markState(Lifecycle.State.RESUMED);
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        mlifecleRegistry.markState(Lifecycle.State.DESTROYED);

    }
}
