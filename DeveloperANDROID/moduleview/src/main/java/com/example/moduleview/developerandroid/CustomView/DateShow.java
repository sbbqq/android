package com.example.moduleview.developerandroid.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by wqq on 18-6-14.
 */

public class DateShow extends View {
    private CustomCalendar.MyDay myDay;
    private Paint paintText;
    private Paint paintCircle;
    
    int radusBig,radusSmall;
    int width,height;
    Point pointBig,poingSmall;
    private String content="null";
    private int stateBig=0;//0 代表默认　１代表选中（实心圈）　
    private int stateSmall=0;//0 默认　１代表有数据（实心圈）
    private int stateToday=0;//０代表不是今天，１　代表今天
    public DateShow(Context context) {
        super(context);
        ini();
    }

    public DateShow(Context context, AttributeSet attrs) {
        super(context, attrs);
        ini();
        Log.e("lue","***************");
    }

    public DateShow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ini();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private void ini(){
        poingSmall=new Point();
        pointBig=new Point();
        
        paintText=new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setStrokeCap(Paint.Cap.ROUND);
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTextSize(20);
        paintText.setAntiAlias(true);
        paintText.setTextAlign(Paint.Align.CENTER);

        paintCircle=new Paint();
        paintCircle.setColor(Color.GREEN);
        paintCircle.setStrokeCap(Paint.Cap.ROUND);
        paintCircle.setStyle(Paint.Style.FILL);
        paintCircle.setTextSize(20);
        paintCircle.setAntiAlias(true);
        paintCircle.setTextAlign(Paint.Align.CENTER);
        this.setFocusable(true);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width=getMeasuredWidth();
        height=getMeasuredHeight();
        if(width<=height)
        {
            radusBig=Math.min(width/2,height*3/8);
            radusSmall=radusBig/3;
           
        }
        else{
            radusBig=height/2;
            radusSmall=height/3;
        }
        pointBig.set(((int)(float)width/2),radusBig);
        poingSmall.set((int)(float)width/2,radusBig*2+radusSmall);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawAll(canvas);
        
    }
    public void drawAll(Canvas canvas){
        if(stateBig==1){
            paintCircle.setStyle(Paint.Style.FILL);
            canvas.drawCircle(pointBig.x,pointBig.y,radusBig,paintCircle);
            paintText.setColor(Color.parseColor("#30000000"));

        }else if(stateBig==2){
            paintCircle.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(pointBig.x,pointBig.y,radusBig,paintCircle);
            paintText.setColor((Color.BLACK));
        }
        else{
            paintText.setColor((Color.BLACK));
        }

        if(stateToday==1){
            paintCircle.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(pointBig.x,pointBig.y,radusBig,paintCircle);
            paintText.setColor((Color.BLACK));
        }

        if(stateSmall==1){
            paintCircle.setStyle(Paint.Style.FILL);
            canvas.drawCircle(poingSmall.x,poingSmall.y,radusSmall,paintCircle);
        }

        canvas.drawText(content,pointBig.x,pointBig.y,paintText);

    }
    public void setStyle(int statusBig,int statusSmall){
        this.stateSmall=statusSmall;
        this.stateBig=statusBig;
        this.invalidate();
    }

    public CustomCalendar.MyDay getMyDay() {
        return myDay;
    }

    public void setMyDay(CustomCalendar.MyDay myDay) {
        this.myDay = myDay;
    }

    public int getStateBig() {
        return stateBig;
    }

    public void setStateBig(int stateBig) {
        this.stateBig = stateBig;
        this.invalidate();
    }

    public int getStateSmall() {
        return stateSmall;
    }

    public void setStateSmall(int stateSmall) {
        this.stateSmall = stateSmall;
    }

    public int getStateToday() {
        return stateToday;
    }

    public void setStateToday(int stateToday) {
        this.stateToday = stateToday;
    }
}
