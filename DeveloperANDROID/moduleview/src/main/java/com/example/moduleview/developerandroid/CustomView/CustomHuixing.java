package com.example.moduleview.developerandroid.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/**
 * Created by alone-nine-sword on 18-3-19.
 */

public class CustomHuixing extends View {
    int Endangle=90;
    int nowangle=0;
    Paint paintLast,paintSmall;
    float Bigradus=0,SmallRadus=0;
    RectF rectF,rectFsmall;
    Point pointfirstBig,pointfirstSmall;
    Point pointsecondBig,pointsecondSmall;
    Path pathall;
    private SweepGradient mSweepGradient = null;

    public CustomHuixing(Context context) {
        super(context);
        pathall=new Path();
        rectF=new RectF();
        rectFsmall=new RectF();
        paintLast=new Paint();
        paintLast.setStyle(Paint.Style.STROKE);
        paintLast.setColor(Color.RED);
        paintSmall=new Paint();
        paintSmall.setStyle(Paint.Style.STROKE);
        paintSmall.setColor(Color.GREEN);
        pointfirstBig=new Point();
        pointfirstSmall=new Point();
        pointsecondSmall=new Point();
        pointsecondBig=new Point();
        // 绘图线程
    //    new Thread()
     //   {
   //         public void run()
   //         {
//                while (true)
//                {
//
//                    if(nowangle<90){
//                        nowangle++;
//                        pointfirstBig.set((int)(getMeasuredWidth()/2+Bigradus*Math.cos((nowangle+20)*Math.PI / 180)) ,(int)(getMeasuredHeight()/2 + Math.sin((nowangle+20)*Math.PI / 180) * Bigradus ));
//                        pointfirstSmall.set((int)(getMeasuredWidth()/2+SmallRadus*Math.cos((nowangle+20)*Math.PI / 180)) ,(int)(getMeasuredHeight()/2 + Math.sin((nowangle+20)*Math.PI / 180) * SmallRadus ));
//                        pointsecondBig.set((int)(getMeasuredWidth()/2+Bigradus*Math.cos((nowangle)*Math.PI / 180)) ,(int)(getMeasuredHeight()/2 + Math.sin((nowangle)*Math.PI / 180) * Bigradus ));
//                        pointsecondSmall.set((int)(getMeasuredWidth()/2+SmallRadus*Math.cos((nowangle)*Math.PI / 180)) ,(int)(getMeasuredHeight()/2 + Math.sin((nowangle)*Math.PI / 180) * SmallRadus ));
//                        postInvalidate();
//                        Log.e("周而复始","*******************"+nowangle);
//                    }
//
//                    //postInvalidate();
//                    try
//                    {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//            };
//        }.start();
    }

    public CustomHuixing(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        pathall=new Path();
        rectF=new RectF();
        rectFsmall=new RectF();
        paintLast=new Paint();
        paintLast.setColor(Color.RED);
        paintLast.setStyle(Paint.Style.STROKE);
        paintSmall=new Paint();
        paintSmall.setStyle(Paint.Style.STROKE);
        paintSmall.setColor(Color.GREEN);
        pointfirstBig=new Point();
        pointfirstSmall=new Point();
        pointsecondSmall=new Point();
        pointsecondBig=new Point();
        // 绘图线程
//        new Thread()
//        {
//            public void run()
//            {
//                while (true)
//                {
//
//                    if(nowangle<90){
//                        nowangle++;
//                        pointsecondBig.set((int)(getMeasuredWidth()/2+Bigradus*Math.cos((nowangle)*Math.PI / 180)) ,(int)(getMeasuredHeight()/2 + Math.sin((nowangle)*Math.PI / 180) * Bigradus ));
//                        pointsecondSmall.set((int)(getMeasuredWidth()/2+SmallRadus*Math.cos((nowangle)*Math.PI / 180)) ,(int)(getMeasuredHeight()/2 + Math.sin((nowangle)*Math.PI / 180) * SmallRadus ));
//                        pointfirstBig.set((int)(getMeasuredWidth()/2+Bigradus*Math.cos((nowangle+20)*Math.PI / 180)) ,(int)(getMeasuredHeight()/2 + Math.sin((nowangle+20)*Math.PI / 180) * Bigradus ));
//                        pointfirstSmall.set((int)(getMeasuredWidth()/2+SmallRadus*Math.cos((nowangle+20)*Math.PI / 180)) ,(int)(getMeasuredHeight()/2 + Math.sin((nowangle+20)*Math.PI / 180) * SmallRadus ));
//                        postInvalidate();
//                        Log.e("周而复始","*******************"+nowangle);
//                    }
//
//                    try
//                    {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//            };
//        }.start();
    }

    public CustomHuixing(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rectF=new RectF();
        pathall=new Path();
        rectFsmall=new RectF();
        paintLast=new Paint();
        paintLast.setStyle(Paint.Style.STROKE);
        paintLast.setColor(Color.RED);
        paintSmall=new Paint();
        paintSmall.setStyle(Paint.Style.STROKE);
        paintSmall.setColor(Color.GREEN);
        pointfirstBig=new Point();
        pointfirstSmall=new Point();
        pointsecondSmall=new Point();
        pointsecondBig=new Point();
        // 绘图线程
//        new Thread()
//        {
//            public void run()
//            {
//                while (true)
//                {
//
//                    if(nowangle<90){
//                        nowangle++;
//                        pointsecondBig.set((int)(getMeasuredWidth()/2+Bigradus*Math.cos((0)*Math.PI / 180)) ,(int)(getMeasuredHeight()/2 + Math.sin((0)*Math.PI / 180) * Bigradus ));
//                        pointsecondSmall.set((int)(getMeasuredWidth()/2+SmallRadus*Math.cos((0)*Math.PI / 180)) ,(int)(getMeasuredHeight()/2 + Math.sin((0)*Math.PI / 180) * SmallRadus ));
//                        pointfirstBig.set((int)(getMeasuredWidth()/2+Bigradus*Math.cos((0+20)*Math.PI / 180)) ,(int)(getMeasuredHeight()/2 + Math.sin((0+20)*Math.PI / 180) * Bigradus ));
//                        pointfirstSmall.set((int)(getMeasuredWidth()/2+SmallRadus*Math.cos((0+20)*Math.PI / 180)) ,(int)(getMeasuredHeight()/2 + Math.sin((0+20)*Math.PI / 180) * SmallRadus ));
//                        postInvalidate();
//                        Log.e("周而复始","*******************"+nowangle);
//                    }
//
//
//                    try
//                    {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//            };
//        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path small,big;
        small=new Path();
        big=new Path();
        canvas.drawLine(getMeasuredWidth()/2,getMeasuredHeight()/2,getMeasuredWidth(),getMeasuredHeight()/2,paintLast);

        mSweepGradient = new SweepGradient(getMeasuredWidth()/2,getMeasuredHeight()/2,Color.BLUE,Color.RED );
        paintSmall.setStyle(Paint.Style.STROKE);
        paintSmall.setShader(mSweepGradient);
        paintSmall.setStrokeWidth(70);

       big.addArc(rectF,0,20);
     small.addArc(rectFsmall,0,20);
     //pathall.setFillType(Path.FillType.WINDING);
   //  paintSmall.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(small,paintSmall);
        RotateAnimation rotate  = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        LinearInterpolator lin = new LinearInterpolator();
        rotate.setInterpolator(lin);
        rotate.setDuration(2000);//设置动画持续周期
        rotate.setRepeatCount(1);//设置重复次数
        rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        rotate.setStartOffset(10);//执行前的等待时间



                this.setAnimation(rotate);
                this.startAnimation(rotate);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Bigradus=Math.min(getMeasuredHeight()/2,getMeasuredWidth()/2);
        SmallRadus=Bigradus/2;
        rectF.set(getMeasuredWidth()/2-Bigradus,getMeasuredHeight()/2-Bigradus,getMeasuredWidth()/2+Bigradus,getMeasuredHeight()/2+Bigradus);
        rectFsmall.set(getMeasuredWidth()/2-SmallRadus,getMeasuredHeight()/2-SmallRadus,getMeasuredWidth()/2+SmallRadus,getMeasuredHeight()/2+SmallRadus);
        pointfirstBig.set((int)(getMeasuredWidth()/2+Bigradus*Math.cos((nowangle+20)*Math.PI / 180)) ,(int)(getMeasuredHeight()/2 + Math.sin((nowangle+20)*Math.PI / 180) * Bigradus ));
        pointfirstSmall.set((int)(getMeasuredWidth()/2+SmallRadus*Math.cos((nowangle+20)*Math.PI / 180)) ,(int)(getMeasuredHeight()/2 + Math.sin((nowangle+20)*Math.PI / 180) * SmallRadus ));


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }
}
