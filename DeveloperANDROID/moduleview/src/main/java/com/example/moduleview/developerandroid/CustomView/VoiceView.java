package com.example.moduleview.developerandroid.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.moduleview.R;

/**
 * Created by alone-nine-sword on 18-3-12.
 */

public class VoiceView  extends View {
    private int countdop=10;// 个数
    private int width_angle=10;//间距
    private int progress=30;//default show 30 percent
    private int swipe_unit;
    private float paintWidth=10;
    private float paintwidthpro=10;
    Paint paintbg;
    Paint paintshow;
    Paint paintpro;
    RectF rectF;
    private float startX,startY;
    public VoiceView(Context context) {
        super(context);
    }

    public VoiceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paintbg=new Paint();
        paintshow=new Paint();
        paintshow.setStrokeCap(Paint.Cap.ROUND);
        paintbg.setStrokeCap(Paint.Cap.ROUND);
        paintbg.setColor(Color.RED);
        paintbg.setStrokeWidth(paintWidth);
        paintbg.setStyle(Paint.Style.FILL_AND_STROKE);
        paintshow.setStyle(Paint.Style.FILL_AND_STROKE);
        paintshow.setColor(Color.BLACK);
        paintshow.setStrokeWidth(paintWidth);
        rectF=new RectF();
        paintpro=new Paint();
        paintpro.setTextSize(14);
        paintpro.setStrokeCap(Paint.Cap.ROUND);
        paintpro.setStyle(Paint.Style.FILL);
        paintpro.setColor(Color.YELLOW);


        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs, R.styleable.VoiceView,0,0);
        int attrindex;
        for(int i=0;i<typedArray.length();i++){
            attrindex=typedArray.getIndex(i);
            switch (attrindex){
                case  R.styleable.VoiceView_count:
                    countdop=typedArray.getInteger(attrindex,18);
                    Log.e("passcount:","passCount:"+typedArray.getInteger(attrindex,18));
                    break;
                case R.styleable.VoiceView_width_angle:
                    width_angle=typedArray.getInteger(attrindex,10);
                    Log.e("passwidth:","passWidth:"+typedArray.getInteger(typedArray.getInteger(attrindex,10),18));
                    break;
                case R.styleable.VoiceView_progress:
                    progress=typedArray.getInteger(attrindex,50);
                    break;
                case R.styleable.VoiceView_cicle_width:
                    paintWidth=typedArray.getInteger(attrindex,10);
                    Log.e("width:","typedArray.getInteger(attrindex,10)"+typedArray.getInteger(attrindex,10));
                    break;


            }
            Log.e("counddopandwidth",countdop+"width:"+width_angle);
        }
        //swipe_unit=(360-width_angle*countdop)/countdop;
        swipe_unit=360/countdop-width_angle;
        paintbg.setStrokeWidth(paintWidth);
        paintshow.setStrokeWidth(paintWidth);
        typedArray.recycle();//recyle resousrce
    }

    public VoiceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paintbg=new Paint();
        paintshow=new Paint();
        rectF=new RectF();
        paintbg.setAntiAlias(true);
        paintshow.setStrokeCap(Paint.Cap.ROUND);
        paintbg.setStrokeCap(Paint.Cap.ROUND);
        paintbg.setColor(Color.RED);
        paintbg.setStrokeWidth(paintWidth);
        paintbg.setStyle(Paint.Style.FILL_AND_STROKE);
        paintshow.setStyle(Paint.Style.FILL);
        paintshow.setColor(Color.BLACK);
        paintshow.setStrokeWidth(paintWidth);
        paintpro=new Paint();
        paintpro.setStrokeCap(Paint.Cap.ROUND);

        paintpro.setStyle(Paint.Style.FILL_AND_STROKE);
        paintpro.setTextSize(14);
        paintpro.setColor(Color.YELLOW);
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs, R.styleable.VoiceView,defStyleAttr,0);
        int attrindex;
        for(int i=0;i<typedArray.length();i++){
            attrindex=typedArray.getIndex(i);
            switch (attrindex){
                case  R.styleable.VoiceView_count:
                    countdop=typedArray.getInteger(attrindex,18);
                    Log.e("passcount:","passCount:"+typedArray.getInteger(attrindex,18));
                    break;
                case R.styleable.VoiceView_width_angle:
                    width_angle=typedArray.getInteger(attrindex,10);
                    Log.e("passwidth:","passWidth:"+typedArray.getInteger(typedArray.getInteger(attrindex,10),18));
                    break;
                case R.styleable.VoiceView_progress:
                    progress=typedArray.getInteger(attrindex,50);
                    break;
                case R.styleable.VoiceView_cicle_width:
                    paintWidth=typedArray.getInteger(attrindex,10);
                    Log.e("width:","typedArray.getInteger(attrindex,10)"+typedArray.getInteger(attrindex,10));
                    break;

            }
            Log.e("counddopandwidth",countdop+"width:"+width_angle);
        }
        //swipe_unit=(360-width_angle*countdop)/countdop;
        swipe_unit=360/countdop-width_angle;
        paintbg.setStrokeWidth(paintWidth);
        paintshow.setStrokeWidth(paintWidth);
        typedArray.recycle();//recyle resousrce
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawAc(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
    public void drawAc(Canvas canvas){
        Log.e("drawAc","******************");
        float x,y,radus;
        x=getMeasuredWidth()/2;
        y=getMeasuredHeight()/2;



        radus=Math.min(getMeasuredHeight()/2,getMeasuredWidth()/2);
        radus=radus-paintWidth;
        Log.e("xy","x:"+x+"y:"+y+"radus:"+radus+"swipe_unit:"+swipe_unit+"count:"+countdop);
       rectF=new RectF(x-radus,y-radus,x+radus,y+radus);

        for(int i=0;i<countdop;i++){
            canvas.drawArc(rectF,i*(360/(float)countdop),swipe_unit,false,paintbg);
        }
        int showcount=countdop*progress/100;
        for(int i=0;i<showcount;i++){
            canvas.drawArc(rectF,i*(360/(float)countdop),swipe_unit,false,paintshow);
        }
        paintpro.setTextSize(50);
        paintpro.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(""+progress,rectF.centerX(),rectF.centerY(),paintpro);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(rectF.contains(event.getX(),event.getY())) {
                    startX = event.getX();

                }
                break;

            case MotionEvent.ACTION_MOVE:

                break;

            case MotionEvent.ACTION_UP:
                if(rectF.contains(event.getX(),event.getY())) {
                    float dx=event.getX()-startX;
                    Log.e("x","sx:"+startX+"nowx:"+event.getX());
                    changeVoice(dx);
                    startX=event.getX();
                }
                break;
        }
        return true;
    }

    public void changeVoice(float dx){
        Log.e("dx",dx+"");
        int change=(int)(dx*100/rectF.width());
        Log.e("change",change+"");
        if(change+progress>=100){
            progress=100;
        }
        else if(change+progress<=0){
            progress=0;
        }
        else{
            progress=progress+change;
        }
        invalidate();
    }

}
