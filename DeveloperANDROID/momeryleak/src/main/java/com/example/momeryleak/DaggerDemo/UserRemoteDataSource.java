package com.example.momeryleak.DaggerDemo;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by alone-nine-sword on 20-1-8.
 */

public class UserRemoteDataSource {
    @Inject
    public UserRemoteDataSource() {
        Log.e("UserRemoteDataSource()","---onStruct---");
    }
}
