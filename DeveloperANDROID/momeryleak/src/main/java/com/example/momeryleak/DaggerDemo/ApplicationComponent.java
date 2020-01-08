package com.example.momeryleak.DaggerDemo;

import com.example.momeryleak.LoginActivity;

import dagger.Component;

/**
 * Created by alone-nine-sword on 20-1-8.
 */
@Component
public interface ApplicationComponent {
   void injectAc(LoginActivity loginActivity);
}
