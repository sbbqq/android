package com.example.moduleview.developerandroid.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

/**
 * Created by wqq on 18-6-5.
 */

public class CustomClock extends View {
    int nowangle=0;
    float radus;
    Paint paintcircle;
    Point Pcenter;
    Paint movePointer;
    Path  path;
    RectF rectF;
    int strokewidth=5;
    private SweepGradient mSweepGradient = null;
    public CustomClock(Context context) {
        super(context);
        init();
    }

    public CustomClock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomClock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public void init(){
        paintcircle=new Paint();
        paintcircle.setColor(RED);
        paintcircle.setStyle(Paint.Style.STROKE);
       paintcircle.setStrokeWidth(strokewidth);
       paintcircle.setTextSize(20);
       paintcircle.setTextAlign(Paint.Align.CENTER);
        Pcenter=new Point();
        mSweepGradient=new SweepGradient(Pcenter.x,Pcenter.y,Color.parseColor("#63B8FF"),Color.parseColor("#0163B8FF"));
        movePointer=new Paint();
        movePointer.setStyle(Paint.Style.STROKE);
        movePointer.setStrokeWidth(30);
        movePointer.setShader(mSweepGradient);
        path=new Path();
        rectF=new RectF();
        perodamicdrawLine();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        radus=Math.min(getMeasuredWidth()/2,getMeasuredHeight()/2);
        Pcenter.x=getMeasuredWidth()/2;
        Pcenter.y=getMeasuredHeight()/2;
        Log.e("radus",radus+"");
        rectF.set(Pcenter.x-radus+30-strokewidth*2,Pcenter.y-radus+30-strokewidth*2,Pcenter.x+radus-30+strokewidth*2,Pcenter.y+radus-30+strokewidth*2);


    }
    public void drawpoint(Canvas canvas){
         for(int i=0;i<24;i++){
             canvas.drawLine(getMeasuredWidth()-30-strokewidth,Pcenter.y,getMeasuredWidth()-strokewidth,Pcenter.y,paintcircle);
              //canvas.save();
             canvas.drawText((i)+"",Pcenter.x,Pcenter.y-radus+30+strokewidth*4,paintcircle);
              canvas.rotate(360/24,Pcenter.x,Pcenter.y);
         }
         canvas.save();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("onDraw","******************");
        super.onDraw(canvas);
        canvas.drawCircle(Pcenter.x,Pcenter.y,radus-strokewidth,paintcircle);
        canvas.drawCircle(Pcenter.x,Pcenter.y,radus-30-strokewidth,paintcircle);
        drawpoint(canvas);
        drawNumber(canvas);
        drawMovepointer(canvas);


    }
    private static final int RED = 230, GREEN = 0, BLUE = 0; //基础颜色，这里是橙红色
    private static final int MIN_ALPHA = 30; //最小不透明度
    private static final int MAX_ALPHA = 255; //最大不透明度
    private static int[] changeColors = new int[]{
            Color.argb(30, GREEN, GREEN, BLUE),
            Color.argb(30, GREEN, GREEN, BLUE),
            Color.argb(255, GREEN, GREEN, BLUE),
//            Color.TRANSPARENT,
    };
    public void drawMovepointer(Canvas canvas){
        //设置渐变
        mSweepGradient = new SweepGradient(Pcenter.x, Pcenter.y, changeColors, null);
        //按照圆心旋转
        Matrix matrix = new Matrix();
        matrix.setRotate(15, Pcenter.x, Pcenter.y);
        mSweepGradient.setLocalMatrix(matrix);
        movePointer.setShader(mSweepGradient);
        path.reset();
            path.addArc(rectF,nowangle,15);
            canvas.drawPath(path,movePointer);






    }
    private void drawNumber(Canvas canvas){

       // canvas.drawText("12",Pcenter.x,Pcenter.y-radus+30+strokewidth*4,paintcircle);
       // canvas.save();
    }
    public void perodamicdrawLine(){
        new Thread()
        {
            public void run()
            {
                while (true)
                {

                    if(nowangle<360){
                        nowangle++;
                       postInvalidate();
                        Log.e("周而复始","*******************"+nowangle);
                    }


                    try
                    {
                        Thread.sleep(10);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            };
        }.start();
    }

}
