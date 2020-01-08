package com.example.retrofit;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.retrofit.ble.Ble;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.os.Build.*;
import static android.os.Build.VERSION.*;

public class MainActivity extends AppCompatActivity {
    Button btntest;
    Button btnscan,btnconnect,btndisconnect,btnfindservice,btnwrite,btnnotify,btnreadvalue;
    Ble ble;
 private static final int ACCESS_LOCATION=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.e("wifiip",getlocalip());
//        Toast.makeText(this,getlocalip(),Toast.LENGTH_LONG).show();
//        btntest=(Button)findViewById(R.id.btn_go);
//        btntest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                request();
//                getlocalip();
//            }
//        });
        getPermission();
        init();

    }

    private void init(){

        ble=new Ble(this,this);
        ble.init();
        btnscan=(Button)findViewById(R.id.button);
        btnconnect=(Button)findViewById(R.id.button2);
        btndisconnect=(Button)findViewById(R.id.button3);
        btnfindservice=(Button)findViewById(R.id.button4);
        btnwrite=(Button)findViewById(R.id.button5);
        btnnotify=(Button)findViewById(R.id.button6);
        btnreadvalue=(Button)findViewById(R.id.button7);
        btnscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ble.startScan();
            }
        });

        btnconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ble.conectDev("C8:B2:1E:7C:AB:42");
            }
        });

        btnfindservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ble.findService();
            }
        });

        btnwrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value=ble.hexStringToString("55c0500102ecaa");//55c19206a6aa 读命令//55c0500102ecaa
               Log.e("value",value);
                ble.writeValue("0000ff10-0000-1000-8000-00805f9b34fb","0000ff11-0000-1000-8000-00805f9b34fb","55c0500102ecaa");
            }
        });

        btnnotify.setOnClickListener(new View.OnClickListener() {
            @Override//0000ff10-0000-1000-8000-00805f9b34fb   0000ff12-0000-1000-8000-00805f9b34fb 血压计
            public void onClick(View v) {
                ble.setNotifacion("0000fff0-0000-1000-8000-00805f9b34fb","0000fff1-0000-1000-8000-00805f9b34fb");
            }
        });
        btndisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ble.disconnectDev();
            }
        });
        btnreadvalue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  ble.readValue("00002a9c-0000-1000-8000-00805f9b34fb");
            }
        });

    }




    @SuppressLint("WrongConstant")
    private void getPermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            int permissionCheck = 0;
            permissionCheck = this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
            permissionCheck += this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions( // 请求授权
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        ACCESS_LOCATION);// 自定义常量,任意整型
            } else {
                // 已经获得权限
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case ACCESS_LOCATION:
                if (hasAllPermissionGranted(grantResults)) {
                    //Log.d(TAG, "onRequestPermissionsResult: OK");
                } else {
                   // Log.d(TAG, "onRequestPermissionsResult: NOT OK");
                }
                break;
        }
    }

    private boolean hasAllPermissionGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }





    private String getlocalip(){
        WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        if(ipAddress==0)return "未连接wifi";
        return ((ipAddress & 0xff)+"."+(ipAddress>>8 & 0xff)+"."
                +(ipAddress>>16 & 0xff)+"."+(ipAddress>>24 & 0xff));
    }


    public void request() {

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call<Translation> call = request.getCall();

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                // 步骤7：处理返回的数据结果
                response.body().show();

            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Translation> call, Throwable throwable) {
                System.out.println("连接失败");
                Log.e("onFailed","******************");
            }
        });
    }
}

