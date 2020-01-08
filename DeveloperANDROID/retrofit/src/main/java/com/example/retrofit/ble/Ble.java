package com.haier.tool;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.app.Activity;
import android.os.Build;
import android.content.pm.PackageManager;
import android.util.Log;
import org.apache.cordova.PluginResult;
import org.apache.cordova.CallbackContext;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.google.gson.Gson;
import static android.bluetooth.BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT;
import static android.bluetooth.BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE;
import static android.bluetooth.BluetoothGattCharacteristic.WRITE_TYPE_SIGNED;
import static android.content.ContentValues.TAG;

/**
 * Created by alone-nine-sword on 18-4-23.
 */

public class Ble {
    private Context context;
    private BluetoothManager mBluetoothManager;
    private BluetoothAdapter mBluetoothAdapter;
    private String mBluetoothDeviceAddress;
    private BluetoothGatt mBluetoothGatt;
    private BluetoothGattCallback mGattCallback;
    private BluetoothAdapter.LeScanCallback mLeScanCallback;
    private int mConnectionState = STATE_DISCONNECTED;
    BluetoothDevice device=null;
    List <BluetoothGattService> bluetoothGattServices;
    List <BluetoothGattCharacteristic> bluetoothGattCharacteristics;
CallbackContext callbackContext;
   Activity activity;

    private static final int STATE_DISCONNECTED = 0;
    private static final int STATE_CONNECTING = 1;
    private static final int STATE_CONNECTED = 2;
    boolean IsSupportBLE=false;//ble support dev
    boolean IsBluOpent=false;//ble open status
    boolean isConecnted=false;//设备和手机连接状态
    boolean isFindservice=false;//是否发现服务
    public final static String ACTION_GATT_CONNECTED =
            "com.example.bluetooth.le.ACTION_GATT_CONNECTED";
    public final static String ACTION_GATT_DISCONNECTED =
            "com.example.bluetooth.le.ACTION_GATT_DISCONNECTED";
    public final static String ACTION_GATT_SERVICES_DISCOVERED =
            "com.example.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED";
    public final static String ACTION_DATA_AVAILABLE =
            "com.example.bluetooth.le.ACTION_DATA_AVAILABLE";
    public final static String EXTRA_DATA =
            "com.example.bluetooth.le.EXTRA_DATA";

    /**
     * check if the device support ble
     */
  public boolean checkBleSupport(){
      if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
          //Toast.makeText(context, "not suppprted", //Toast.LENGTH_SHORT).show();
          IsSupportBLE=false;
          return  false;

      }
      else{
          //Toast.makeText(context, " suppprted", //Toast.LENGTH_SHORT).show();
          IsSupportBLE=true;
                      return true;
      }
  }

  public boolean checkIsOpen(){
      if(mBluetoothAdapter!=null){
         Log.e("openstatus",mBluetoothAdapter.isEnabled()+"");
         if(mBluetoothAdapter.isEnabled())
           {    IsBluOpent=true;
             return  true;}
         else
         {
             IsBluOpent=false;
            //Toast.makeText(context,"蓝牙未打开",//Toast.LENGTH_SHORT).show();
         }
      }
      return false;
  }

     public Ble(Context context,Activity activity) {
        this.context = context;
        setActivity(activity);
        init();
    }

    public void init() {
      checkBleSupport();

        bluetoothGattCharacteristics=new ArrayList<BluetoothGattCharacteristic>();
        mBluetoothManager=(BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter =mBluetoothManager.getAdapter();
      mGattCallback =
              new BluetoothGattCallback() {
                  @Override
                  public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                      String intentAction;
                      if(status==BluetoothGatt.GATT_SUCCESS) {
                          if (newState == BluetoothProfile.STATE_CONNECTED) {
                              intentAction = ACTION_GATT_CONNECTED;
                              mConnectionState = STATE_CONNECTED;
                              // broadcastUpdate(intentAction);
                              Log.e(TAG, "Connected to GATT server.");

                              CallbackMess callbackMess=new CallbackMess("1","suc","conected");
                              Gson gson=new Gson();
                              String mess=gson.toJson(callbackMess);
                              PluginResult pluginResult=new PluginResult(PluginResult.Status.OK,mess);
                              pluginResult.setKeepCallback(true);
                              callbackContext.sendPluginResult(pluginResult);
                              //Toast.makeText(context, "binggoconnect", //Toast.LENGTH_SHORT).show();
                              // Log.e(TAG, "Attempting to start service discovery:" +
                              //  mBluetoothGatt.discoverServices());
                              isConecnted=true;
                              LogMess(callbackMess);


                          } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                              intentAction = ACTION_GATT_DISCONNECTED;
                              mConnectionState = STATE_DISCONNECTED;

                             // pluginResult = new PluginResult(PluginResult.Status.OK, "-2");// 断开或者未连接
                              CallbackMess callbackMess=new CallbackMess("1","suc","disconnected");
                              Gson gson=new Gson();
                              String mess=gson.toJson(callbackMess);
                              PluginResult pluginResult=new PluginResult(PluginResult.Status.OK,mess);

                              callbackContext.sendPluginResult(pluginResult);
                              realeaseReasourse();
                              Log.e(TAG, "Disconnected from GATT server.");
                              isConecnted=false;
                              LogMess(callbackMess);
                              // broadcastUpdate(intentAction);
                          }
                      }
                      else{
 Log.e("gattfaiid", "enheng");
                          CallbackMess callbackMess=new CallbackMess("-1","fail",status+"");
                          Gson gson=new Gson();
                          String mess=gson.toJson(callbackMess);
                          PluginResult pluginResult=new PluginResult(PluginResult.Status.ERROR,mess);

                          callbackContext.sendPluginResult(pluginResult);
                          LogMess(callbackMess);
                      }
                  }
                  @Override
                  // New services discovered
                  public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                      if (status == BluetoothGatt.GATT_SUCCESS) {
                          //broadcastUpdate(ACTION_GATT_SERVICES_DISCOVERED);
                          Log.e("onserviceDiscorver","GATT_SUCCESS");
                          showShowservice(gatt.getServices());
                          CallbackMess callbackMess=new CallbackMess("1","suc","findservice!");
                          Gson gson=new Gson();
                          String mess=gson.toJson(callbackMess);
                          PluginResult pluginResult=new PluginResult(PluginResult.Status.OK,mess);
                           isFindservice=true;
                          callbackContext.sendPluginResult(pluginResult);
                         // setNotifacion("00002a1c-0000-1000-8000-00805f9b34fb");
                          Log.e("serviceSize","serviceSize"+ gatt.getServices().size());
                          LogMess(callbackMess);
                      } else {
                          CallbackMess callbackMess=new CallbackMess("-1","fail",status+"");
                          Gson gson=new Gson();
                          String mess=gson.toJson(callbackMess);
                          PluginResult pluginResult=new PluginResult(PluginResult.Status.ERROR,mess);
                          LogMess(callbackMess);
                          callbackContext.sendPluginResult(pluginResult);

                      }
                  }

                  @Override
                  // Result of a characteristic read operation
                  public void onCharacteristicRead(BluetoothGatt gatt,
                                                   BluetoothGattCharacteristic characteristic,
                                                   int status) {
                      Log.e("oncharacterreistictRead","UIID:"+characteristic.getUuid()+"value");
                      if (status == BluetoothGatt.GATT_SUCCESS) {
                          Log.e("oncharacterreistictRead","UIID:"+characteristic.getUuid()+"value");
                          //broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);
                      }
                      else{
                          PluginResult pluginResult;
                          pluginResult = new PluginResult(PluginResult.Status.ERROR, status);
                          callbackContext.sendPluginResult(pluginResult);
                      }
                  }

                  @Override
                  public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                      Log.e("onCharacterChanged","UIID:"+characteristic.getUuid()+"value:"+characteristic.getValue().toString());
                      super.onCharacteristicChanged(gatt, characteristic);
                      CallbackMess callbackMess=new CallbackMess("1","suc",characteristic.getValue().toString());
                      Gson gson=new Gson();
                      String mess=gson.toJson(callbackMess);
                      PluginResult pluginResult=new PluginResult(PluginResult.Status.OK,mess);

                      pluginResult.setKeepCallback(true);
                      callbackContext.sendPluginResult(pluginResult);
                      LogMess(callbackMess);
                  }

                  @Override
                  public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                      if(status==BluetoothGatt.GATT_SUCCESS) {
                          Log.e("onCharacterWrite", "UIID:" + characteristic.getUuid() + "value:" + characteristic.getValue() + "status:" + status);
                          // mBluetoothGatt.readCharacteristic(getCharacterByUIID("00002a1c-0000-1000-8000-00805f9b34fb"));
                          CallbackMess callbackMess=new CallbackMess("1","suc","writecharacter-ok");
                          Gson gson=new Gson();
                          String mess=gson.toJson(callbackMess);
                          PluginResult pluginResult=new PluginResult(PluginResult.Status.OK,mess);
                          LogMess(callbackMess);
                          callbackContext.sendPluginResult(pluginResult);
                      }
                      else{
                          CallbackMess callbackMess=new CallbackMess("-1","fail",status+"");
                          Gson gson=new Gson();
                          String mess=gson.toJson(callbackMess);
                          PluginResult pluginResult=new PluginResult(PluginResult.Status.ERROR,mess);
                          LogMess(callbackMess);
                          callbackContext.sendPluginResult(pluginResult);
                      }
                  }

                  @Override
                  public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                      super.onDescriptorWrite(gatt, descriptor, status);
                      Log.e("onDescriptorWrite","*****************");
                      if(status==BluetoothGatt.GATT_SUCCESS){
                          CallbackMess callbackMess=new CallbackMess("1","suc","write-descriptor-ok");
                          Gson gson=new Gson();
                          String mess=gson.toJson(callbackMess);
                          PluginResult pluginResult=new PluginResult(PluginResult.Status.OK,mess);

                          pluginResult.setKeepCallback(true);
                          LogMess(callbackMess);
                          callbackContext.sendPluginResult(pluginResult);
                      }
                      else{
                          CallbackMess callbackMess=new CallbackMess("-1","fail",status+"");
                          Gson gson=new Gson();
                          String mess=gson.toJson(callbackMess);
                          PluginResult pluginResult=new PluginResult(PluginResult.Status.ERROR,mess);
                          LogMess(callbackMess);
                          callbackContext.sendPluginResult(pluginResult);
                      }
                  }
              };




       mLeScanCallback = new BluetoothAdapter.LeScanCallback() {

          @Override
          public void onLeScan(final BluetoothDevice device, int rssi, final byte[] scanRecord) {

              Log.e("bledev","rssi:"+rssi+"device:"+device.getAddress()+"name:"+device.getName());
////Toast.makeText(context,device.getAddress(),//Toast.LENGTH_SHORT).show();
CallbackMess callbackMess=new CallbackMess("1","suc",device.getAddress());
              Gson gson=new Gson();
              String mess=gson.toJson(callbackMess);
 PluginResult pluginResult=new PluginResult(PluginResult.Status.OK,mess);
 pluginResult.setKeepCallback(true);
              LogMess(callbackMess);
              callbackContext.sendPluginResult(pluginResult);
          }
      };
  }

     public void startScan(){

                 checkAllCondition();
                 if(IsSupportBLE)//IsSupportBLE&&IsBluOpent
                        {  
                            if(IsBluOpent) {
                                mBluetoothAdapter.startLeScan(mLeScanCallback);
                                ////Toast.makeText(context, "*****scaninging****", //Toast.LENGTH_SHORT).show();
                            }
                            else{
                                PluginResult pluginResult;
                                CallbackMess callbackMess=new CallbackMess("-2","fail","blutooth not open");
                                pluginResult=new PluginResult(PluginResult.Status.ERROR,new Gson().toJson(callbackMess));
                                LogMess(callbackMess);
                                callbackContext.sendPluginResult(pluginResult);
                            }
                        }
                 else{
                     PluginResult pluginResult;
                     CallbackMess callbackMess=new CallbackMess("-3","fail","dev not support ble");
                     pluginResult=new PluginResult(PluginResult.Status.ERROR,new Gson().toJson(callbackMess));
                     LogMess(callbackMess);
                     callbackContext.sendPluginResult(pluginResult);
                 }

}

public void checkAllCondition(){
   checkBleSupport();
   

        checkIsOpen();
        
}

public void stopScan(){
    CallbackMess callbackMess;
    PluginResult pluginResult;
    try {
        mBluetoothAdapter.stopLeScan(mLeScanCallback);
         callbackMess=new CallbackMess("1","suc","excute stopScan");
         pluginResult=new PluginResult(PluginResult.Status.OK,new Gson().toJson(callbackMess));
         callbackContext.sendPluginResult(pluginResult);
         
    }
    catch (Exception e){
        callbackMess=new CallbackMess("-2","excep","excute stopScan fail");
        pluginResult=new PluginResult(PluginResult.Status.OK,new Gson().toJson(callbackMess));
        LogMess(callbackMess);
        callbackContext.sendPluginResult(pluginResult);
    }
}

public void  conectDev(String mac){
   String mess="null-null";
//Toast.makeText(context,"try connecting",//Toast.LENGTH_SHORT).show();
PluginResult pluginResult;
 checkAllCondition();
if(mBluetoothGatt!=null){
     mBluetoothGatt.close();
     Log.e("closegatt","closing************");
 }
  if(IsSupportBLE)
 {         
     if(mBluetoothAdapter.isEnabled())
     try {
         device = mBluetoothAdapter.getRemoteDevice(mac);

         // //Toast.makeText(context,"getdeviceafter2",//Toast.LENGTH_SHORT).show();


         //Toast.makeText(context, "获取到设备mac", //Toast.LENGTH_SHORT).show();
         Log.e("connect,", "-----------connect-----------------" + mac);

         mBluetoothGatt = device.connectGatt(context, false, mGattCallback);
         //Toast.makeText(context, "mbluegatt:" + (mBluetoothGatt == null), //Toast.LENGTH_SHORT).show();
         // //Toast.makeText(context,"connectingbefore4",//Toast.LENGTH_SHORT).show();
     }
     catch (Exception e){
Log.e("excep",e.toString());
         CallbackMess callbackMess=new CallbackMess("-1","fail",e.toString());
         pluginResult=new PluginResult(PluginResult.Status.ERROR,mess);
         LogMess(callbackMess);
         callbackContext.sendPluginResult(pluginResult);
     }
     else{
         // -1 means blutooth not open
         CallbackMess callbackMess=new CallbackMess("-2","fail","blutooth not open");
         pluginResult=new PluginResult(PluginResult.Status.ERROR,new Gson().toJson(callbackMess));
         LogMess(callbackMess);
         callbackContext.sendPluginResult(pluginResult);
     }
 }
 else{
      //-3 means dev not support ble
      CallbackMess callbackMess=new CallbackMess("-3","fail","dev not support ble");
      pluginResult=new PluginResult(PluginResult.Status.ERROR,new Gson().toJson(callbackMess));
      LogMess(callbackMess);
      callbackContext.sendPluginResult(pluginResult);
  }

}

public void disconnectDev(){
    Log.e("disconnect","*****************");
    realeaseReasourse();


}
public void realeaseReasourse(){
    if(mBluetoothGatt!=null){
        Log.e("realeaseresouse","*****************");
        if(!isConecnted)
        {
            CallbackMess callbackMess=new CallbackMess("1","suc","already disconnected");
            PluginResult pluginResult=new PluginResult(PluginResult.Status.OK,new Gson().toJson(callbackMess));
            LogMess(callbackMess);
            callbackContext.sendPluginResult(pluginResult);
        }
        else {
            mBluetoothGatt.disconnect();
        }
        bluetoothGattCharacteristics.clear();

    }
    else{
        CallbackMess callbackMess=new CallbackMess("1","suc","already disconnected");
        PluginResult pluginResult=new PluginResult(PluginResult.Status.OK,new Gson().toJson(callbackMess));
        LogMess(callbackMess);
        callbackContext.sendPluginResult(pluginResult);
    }
    isFindservice=false;
}


public void findService(){
    if(mBluetoothGatt!=null){
        Log.e("realeaseresouse","*****************");
        if(!isConecnted)
        {
            CallbackMess callbackMess=new CallbackMess("-1","fail","not connected");
            PluginResult pluginResult=new PluginResult(PluginResult.Status.OK,new Gson().toJson(callbackMess));
            LogMess(callbackMess);
            callbackContext.sendPluginResult(pluginResult);
        }
        else {
            mBluetoothGatt.discoverServices();
        }
        bluetoothGattCharacteristics.clear();

    }
    else{
        CallbackMess callbackMess=new CallbackMess("-1","fail","not connected");
        PluginResult pluginResult=new PluginResult(PluginResult.Status.OK,new Gson().toJson(callbackMess));
        LogMess(callbackMess);
        callbackContext.sendPluginResult(pluginResult);
    }
    
}

public void showcharacter(BluetoothGattService bluetoothGattService){
   List<BluetoothGattCharacteristic> bluetoothGattCharacteristicstemp;
   bluetoothGattCharacteristicstemp=bluetoothGattService.getCharacteristics();

   for(int i=0;i< bluetoothGattCharacteristicstemp.size();i++){
       Log.e("character","UIID:"+bluetoothGattCharacteristicstemp.get(i).getUuid());
       bluetoothGattCharacteristics.add(bluetoothGattCharacteristicstemp.get(i));
   }

}

public void showShowservice(List<BluetoothGattService>gattServices){
     for(int i=0;i<gattServices.size();i++){
         Log.e("service","UIID-->"+gattServices.get(i).getUuid());
         showcharacter(gattServices.get(i));
     }
}


public void writeValue(String SerUIID,String CharUIID,String value){
    if(mBluetoothGatt!=null){
        Log.e("realeaseresouse","*****************");
        if(!isConecnted)
        {
            CallbackMess callbackMess=new CallbackMess("-1","fail","not connected");
            PluginResult pluginResult=new PluginResult(PluginResult.Status.OK,new Gson().toJson(callbackMess));
            LogMess(callbackMess);
            callbackContext.sendPluginResult(pluginResult);
        }
        else {
            BluetoothGattCharacteristic bluetoothGattCharacteristic=null;
            bluetoothGattCharacteristic=getCharacter(SerUIID,CharUIID);
            if(bluetoothGattCharacteristic!=null)
              writeCharacter(bluetoothGattCharacteristic,value);
            else{
                CallbackMess callbackMess=new CallbackMess("-1","fail","not got the character");
                PluginResult pluginResult=new PluginResult(PluginResult.Status.OK,new Gson().toJson(callbackMess));
                LogMess(callbackMess);
                callbackContext.sendPluginResult(pluginResult);
            }
        }
        bluetoothGattCharacteristics.clear();

    }
    else{
        CallbackMess callbackMess=new CallbackMess("-1","fail","not connected");
        PluginResult pluginResult=new PluginResult(PluginResult.Status.OK,new Gson().toJson(callbackMess));
        LogMess(callbackMess);
        callbackContext.sendPluginResult(pluginResult);
    }
    
     
}
 private void writeCharacter(BluetoothGattCharacteristic bluetoothGattCharacteristic,String value){
//    Log.e("writeCharacter","UIID:"+bluetoothGattCharacteristic.getUuid()+"value:"+value+"beforevalue:"+bluetoothGattCharacteristic.getValue().toString());
     bluetoothGattCharacteristic.setWriteType(WRITE_TYPE_DEFAULT);
   bluetoothGattCharacteristic.setValue(value);
  // UUID.fromString("")

   //setNotifacion("00002a1c-0000-1000-8000-00805f9b34fb");
   //
     //descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
     Log.e("writeCharacter","UIID:"+bluetoothGattCharacteristic.getUuid()+"value:"+value+"Aftervalue:"+bluetoothGattCharacteristic.getValue().toString());
     byte temp[]=bluetoothGattCharacteristic.getValue();
     for(int i=0;i<temp.length;i++){
         Log.e("bytevalue:","i:"+i+"value:"+temp[i]);
     }
   mBluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);

 }
 private void writeDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor,byte [] value){
  bluetoothGattDescriptor.setValue(value);
  mBluetoothGatt.writeDescriptor(bluetoothGattDescriptor);
 }

 private BluetoothGattCharacteristic getCharacterByUIID(String UIID){
     BluetoothGattCharacteristic bluetoothGattCharacteristictemp=null;
        for(int i=0;i<bluetoothGattCharacteristics.size();i++){
            if(UIID.equals(bluetoothGattCharacteristics.get(i).getUuid().toString())) {
                bluetoothGattCharacteristictemp=bluetoothGattCharacteristics.get(i);
                Log.e("matching-binggo","******************");
                return bluetoothGattCharacteristictemp;

            }
        }
        return  bluetoothGattCharacteristictemp;
 }
 private BluetoothGattService  getServiceByUIID(String UIID){
           return  null;
 }
 private BluetoothGattDescriptor getDescriptor(String UIID){
         return  null;
 }

public void setNotifacion(String serUIId,String charUiid){
    if(mBluetoothGatt!=null){
        Log.e("realeaseresouse","*****************");
        if(!isConecnted)
        {
            CallbackMess callbackMess=new CallbackMess("-1","fail","not connected");
            PluginResult pluginResult=new PluginResult(PluginResult.Status.OK,new Gson().toJson(callbackMess));
            LogMess(callbackMess);
            callbackContext.sendPluginResult(pluginResult);
        }
        else {
            BluetoothGattCharacteristic bluetoothGattCharacteristic=null;
            bluetoothGattCharacteristic=getCharacter(serUIId,charUiid);
            if(bluetoothGattCharacteristic!=null) {
                Log.e("setNotifaction", bluetoothGattCharacteristic.getUuid() + "");
                boolean status = mBluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
                Log.e("setnotification", status + "<---<status");
                BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(
                        UUID.fromString(SampleGattAttributes.CLIENT_CHARACTERISTIC_CONFIG));
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                boolean statuss = mBluetoothGatt.writeDescriptor(descriptor);
                Log.e("writeDescriptor", statuss + "<-----status");
            }
            else{
                CallbackMess callbackMess=new CallbackMess("-1","fail","not got the character");
                LogMess(callbackMess);
                PluginResult pluginResult=new PluginResult(PluginResult.Status.OK,new Gson().toJson(callbackMess));
                callbackContext.sendPluginResult(pluginResult);
            }
        }
        //bluetoothGattCharacteristics.clear();

    }
    else{
        CallbackMess callbackMess=new CallbackMess("-1","fail","not connected");
        LogMess(callbackMess);
        PluginResult pluginResult=new PluginResult(PluginResult.Status.OK,new Gson().toJson(callbackMess));
        callbackContext.sendPluginResult(pluginResult);
    }
     

}
public static final int REQUEST_CODE_ACCESS_COARSE_LOCATION = 1;
        public static final String ACCESS_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION";

public void checkpermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//如果 API level 是大于等于 23(Android 6.0) 时
            //判断是否具有权限
            if (ContextCompat.checkSelfPermission(context,
                 ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //判断是否需要向用户解释为什么需要申请该权限
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                      ACCESS_COARSE_LOCATION)) {
                    //Toast.makeText(context, "自Android 6.0开始需要打开位置权限才可以搜索到Ble设备", //Toast.LENGTH_SHORT).show();
                }
                //请求权限
                ActivityCompat.requestPermissions(activity,
                        new String[]{ACCESS_COARSE_LOCATION},
                        REQUEST_CODE_ACCESS_COARSE_LOCATION);
            }
           else{
                CallbackMess callbackMess=new CallbackMess("1","suc","got the permission");
                LogMess(callbackMess);
                PluginResult pluginResult=new PluginResult(PluginResult.Status.OK,new Gson().toJson(callbackMess));
                callbackContext.sendPluginResult(pluginResult);
            }
        }

    }

    public BluetoothGattCharacteristic getCharacter(String serUID,String charUID){
    BluetoothGattCharacteristic bluetoothGattCharacteristic=null;
    BluetoothGattService bluetoothGattService=null;
     if(mBluetoothGatt!=null)
     {

         bluetoothGattService=mBluetoothGatt.getService(UUID.fromString(serUID));
         if(bluetoothGattService!=null){
             bluetoothGattCharacteristic=bluetoothGattService.getCharacteristic(UUID.fromString(charUID));
             if(bluetoothGattCharacteristic==null){
                 CallbackMess callbackMess=new CallbackMess("-1","fail","character not exist");
                 PluginResult pluginResult=new PluginResult(PluginResult.Status.ERROR,new Gson().toJson(callbackMess));
                 LogMess(callbackMess);
                 callbackContext.sendPluginResult(pluginResult);
             }
             else{
                 bluetoothGattCharacteristic=bluetoothGattService.getCharacteristic(UUID.fromString(charUID));
                 return  bluetoothGattCharacteristic;
             }

         }
         else{
             CallbackMess callbackMess=new CallbackMess("-1","fail","service not exist");
             LogMess(callbackMess);
             PluginResult pluginResult=new PluginResult(PluginResult.Status.ERROR,new Gson().toJson(callbackMess));
             callbackContext.sendPluginResult(pluginResult);
         }
     }
     else{

     }
          return  null;
    }

//
private static String hexString = "0123456789abcdef";
    /*
    * 将字符串编码成16进制数字,适用于所有字符（包括中文）
    */
    public static String encode(String str) {
//根据默认编码获取字节数组
        byte[] bytes = str.getBytes();
        //41595530304441
        StringBuilder sb = new StringBuilder(bytes.length * 2);
//将字节数组中每个字节拆解成2位16进制整数
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f)));
        }
        Log.e("convert",sb.toString());
        return sb.toString();
    }
public void setActivity(Activity activity) {
        this.activity = activity;
    }

public void setCallbackContext(CallbackContext callbackContext) {
        this.callbackContext = callbackContext;
    }
public CallbackContext getCallbackContext() {
        return callbackContext;
    }
    private void LogMess(CallbackMess callbackMess){
        Log.e("LogMess",new Gson().toJson(callbackMess));
    }
}












