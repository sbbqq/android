package com.example.momeryleak;

import android.app.Activity;
import android.os.Bundle;

import com.example.momeryleak.DaggerDemo.LoginViewModel;

import javax.inject.Inject;

public class LoginActivity extends Activity {
    @Inject
    LoginViewModel loginViewModel,loginViewModel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication)getApplicationContext()).applicationComponent.injectAc(this);

        setContentView(R.layout.activity_login);
        loginViewModel.LogMess();
        loginViewModel.getUserRepository().LogMess();
    }
}
