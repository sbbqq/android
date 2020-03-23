package com.example.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.aidlserver.HelloaidlInterface;

public class MainActivity extends AppCompatActivity {
   private  HelloaidlInterface helloaidlInterface;
   TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.txGet);
        Intent intent=new Intent();
        intent.setAction("com.example.aidlserver");
        intent.setPackage("com.example.aidlserver");
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
               helloaidlInterface=HelloaidlInterface.Stub.asInterface(service);
               Log.e("onserviceConnected","**********");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e("onserviceDisConnected","**********");
            }
        },BIND_AUTO_CREATE);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(helloaidlInterface!=null)
                   Log.e("binggo",helloaidlInterface.getName()) ;
                    else{
                        Log.e("no-biggo","service is null");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
