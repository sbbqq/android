package com.example.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {
    private List<City> list;
    public MyService() {
        Log.e("onstruct-Myser","*****************"+"PId:"+android.os.Process.myPid());
        list=new ArrayList<>();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

       return  new Mybinder();
    }
    class Mybinder extends HelloaidlInterface.Stub{
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String getName() throws RemoteException {
            Log.e("server-getName","**************");
            return "this content genarate by server";
        }

        @Override
        public int Add(int one, int two) throws RemoteException {
            Log.e("serveradd","one:"+one+"two:"+two);
            return one+two;
        }

        @Override
        public int addCity(City c) throws RemoteException {
            Log.e("server-addCity","***"+c.getName()+"code:"+c.getCode());

            if(c!=null){
                list.add(c);
                return 1;

            }

            return 0;
        }

        @Override
        public int getNumberOfCity() throws RemoteException {
            return list.size();
        }
    }
}
