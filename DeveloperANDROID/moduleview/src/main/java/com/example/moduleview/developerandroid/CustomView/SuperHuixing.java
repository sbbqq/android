package com.example.moduleview.developerandroid.CustomView;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.security.PrivilegedAction;
import java.util.zip.CheckedOutputStream;

/**
 * Created by wqq on 19-1-28.
 */

public class SuperHuixing extends View {
    Paint paint;
    private int width,height;
    SweepGradient sweepGradient;
    LinearGradient linearGradient;
    Path path;
    Path pathSegment;
    int colors[];
    public SuperHuixing(Context context) {
        super(context);
        init();
    }

    public SuperHuixing(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SuperHuixing(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint=new Paint();
        int alpha=255;
        path=new Path();
        pathSegment=new Path();

         colors=new int[255] ;
        for(int i=0;i<colors.length;i++){

            colors[i]=Color.argb(alpha,0,153,255);
            alpha=alpha-1;
            Log.e("hehe","alp:"+alpha+"colori:-->"+colors[i]);
        }
        int[] colorss = {
                0x000000FF, 0xFF0000FF





        };// 0x000000FF,0xAF0000FF, 0xBF0000FF, 0xFF0000FF,// 0xFF0000FF, 0xBF0000FF,0xAF0000FF, 0x000000FF,



        paint.setStrokeWidth(0.3f);
        //paint.setShader(linearGradient);
        paint.setStyle(Paint.Style.STROKE);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawHuixing(canvas);
    }
    private void drawHuixing(Canvas canvas){

        path.addArc(new RectF(30,30,width-30,height-30),0,360);
       // canvas.drawCircle(width/2,height/2,Math.min(width/2,height/2)-15,paint);
      // canvas.drawArc(new RectF(30,30,width-30,height-30),0,360,false,paint);
       //canvas.save();
       //canvas.rotate(180);
        //canvas.drawPath(path,paint);
        PathMeasure pathMeasure=new PathMeasure(path,true);
        Log.e("length",pathMeasure.getLength()+"");
        float length=pathMeasure.getLength();
        pathMeasure.getSegment(0*length,length,pathSegment,true);
       // canvas.drawPath(pathSegment,paint);
        for(int i=0;i<colors.length;i++){
            paint.setColor(colors[i]);

            canvas.drawLine(0+i*paint.getStrokeWidth(),0,0+i*paint.getStrokeWidth(),height,paint);
        }
         Log.e("device", android.os.Build.DEVICE+"other"+android.os.Build.MODEL+";;;"+android.os.Build.MANUFACTURER);
        InetAddress ia = null;
        try {
            ia = ia.getLocalHost();
            String localname = ia.getHostName();
            String localip = ia.getHostAddress();
            System.out.println("本机名称是：" + localname);
            Log.e("cao",localname);
            System.out.println("本机的ip是 ：" + localip);
        } catch (Exception e) {
            //Log.e("except",e.getMessage());
            e.printStackTrace();
        }

    Log.e("DiviceName",getDeviceName());



    }

    public String getPhoneName() {
        BluetoothAdapter myDevice = BluetoothAdapter.getDefaultAdapter();
        String deviceName = myDevice.getName();
        return deviceName;
    }

    static String getDeviceName() {
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method getMethod = systemPropertiesClass.getMethod("get", String.class);
            Object object = new Object();
            Object obj = getMethod.invoke(object, "ro.product.device");
            return (obj == null ? "" : (String) obj);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.width=getMeasuredWidth();
        this.height=getMeasuredHeight();
    }
}
