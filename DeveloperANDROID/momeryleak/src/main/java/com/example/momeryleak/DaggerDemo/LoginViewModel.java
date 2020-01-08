package com.example.momeryleak.DaggerDemo;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by alone-nine-sword on 20-1-8.
 */

public class LoginViewModel {

    private final UserRepository userRepository;


    // @Inject tells Dagger how to create instances of LoginViewModel
    @Inject
    public LoginViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
        Log.e("LoginVeiwModel","---onStruct---");
    }
     public void LogMess(){
        Log.e("logMess","oh,yes,im LoginModal");
     }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}