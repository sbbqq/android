package com.example.momeryleak.DaggerDemo;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by alone-nine-sword on 20-1-8.
 */

public class UserRepository {
    private String Name="UserRespository";
    private final UserLocalDataSource userLocalDataSource;
    private final UserRemoteDataSource userRemoteDataSource;

    @Inject
    public UserRepository(UserLocalDataSource userLocalDataSource, UserRemoteDataSource userRemoteDataSource) {
        this.userLocalDataSource = userLocalDataSource;
        this.userRemoteDataSource = userRemoteDataSource;
        Log.e("UserRepository()","---onStruct---");
    }

    public void LogMess(){
        Log.e("LogMese","hey ,i am UserRepository"+this.Name);
    }

}
