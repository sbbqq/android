package com.example.alone_nine_sword.ktliontest

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textwqq.setText("laji");
        Iniview()


    }
    fun Iniview(){

        buttonwqq.setOnClickListener {
           Log.e("wocao","zzz");
        }
        val sensermager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        var eventlister=as SensorEventListener
        sensermager.registerListener(,sensermager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER), SensorManager.SENSOR_DELAY_FASTEST)
    }
    /**
     * SENSOR_SERVICE
     *
     * public void getStep(){

    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT) {
    sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

    sensorManager.registerListener(new SensorEventListener() {
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
    float steps = sensorEvent.values[0];
    Cantant.setStepCount(sensorEvent.values[0]);
    Calendar CalendarStampTime = Util.getCalendarByLongTime(DataOpreateSharepreference.getStampTime(context));
    Calendar CalendarStampCurrentTime = Util.getCalendarByLongTime(System.currentTimeMillis());
    Log.e("stepservice:", steps + "" + "Thread-Name:" + Thread.currentThread().getName());

    if (DataOpreateSharepreference.isFirstUse(context)) {//如果是app第一次使用哦
    Log.e("第一次打开app", "***********************");
    DataOpreateSharepreference.setFlagFisrtUse(context);//设为false
    DataOpreateSharepreference.setLastStep(context, sensorEvent.values[0]);
    DataOpreateSharepreference.setRealStep(context, 0);
    DataOpreateSharepreference.setStampTime(context, System.currentTimeMillis());
    CalendarStampTime = Util.getCalendarByLongTime(DataOpreateSharepreference.getStampTime(context));


    }

    if (CalendarStampTime.get(Calendar.MONTH) != CalendarStampCurrentTime.get(Calendar.MONTH) || CalendarStampTime.get(Calendar.DAY_OF_MONTH) != CalendarStampCurrentTime.get(Calendar.DAY_OF_MONTH)) {//如果超过一天没有打开过app（后台service超过一天未存活）
    Log.e("长时间没有打开app", "***********************");
    DataOpreateSharepreference.setLastStep(context, sensorEvent.values[0]);
    DataOpreateSharepreference.setRealStep(context, 0);
    DataOpreateSharepreference.setStampTime(context, System.currentTimeMillis());
    }


    DataOpreateSharepreference.setRealStep(context, DataOpreateSharepreference.getRealStep(context) + ((int) sensorEvent.values[0] - (int) DataOpreateSharepreference.getLastStep(context)));
    DataOpreateSharepreference.setLastStep(context, sensorEvent.values[0]);
    Log.e("realstp:", "steps:" + DataOpreateSharepreference.getRealStep(context));
    if (!(String.valueOf(beforevalue).equals(String.valueOf(DataOpreateSharepreference.getRealStep(context))))) {
    Log.e("不等于啊", "*************************" + beforevalue + "Cantanvalue:" + Cantant.getStepCount());
    sendNotification(context);
    beforevalue = DataOpreateSharepreference.getRealStep(context);
    }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    }, sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER), SensorManager.SENSOR_DELAY_FASTEST);
    }
    else{
    ;//
    }
    }
     */
}


