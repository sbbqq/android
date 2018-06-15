package com.example.canveselement.Canves;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wqq on 18-6-4.
 */

public class Fansaicurve extends View {
    Paint paint;
    Point Pstart,Pend,Pcontro1,Pcontrol2;
    Point ptd,plst;
    Path path;
    int touchwhich=-1;
    public Fansaicurve(Context context) {
        super(context);
        init();
    }

    public Fansaicurve(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Fansaicurve(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public void init(){
        paint=new Paint();
        path=new Path();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(14);
        Pstart=new Point(100,300);
        Pend=new Point(700,300);
        Pcontro1=new Point(200,600);
        Pcontrol2=new Point(500,800);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(14);
        path.moveTo(Pstart.x,Pstart.y);
        path.cubicTo(Pcontro1.x,Pcontro1.y,Pcontrol2.x,Pcontrol2.y,Pend.x,Pend.y);
        canvas.drawPath(path,paint);
        paint.setColor(Color.RED);
        canvas.drawCircle(Pstart.x,Pstart.y,20,paint);

        canvas.drawCircle(Pend.x,Pend.y,20,paint);

        canvas.drawCircle(Pcontro1.x,Pcontro1.y,20,paint);
        canvas.drawCircle(Pcontrol2.x,Pcontrol2.y,20,paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(30);
        canvas.drawText("cont1",Pcontro1.x,Pcontro1.y,paint);
        canvas.drawText("end",Pend.x,Pend.y,paint);
        canvas.drawText("st",Pstart.x,Pstart.y,paint);
        canvas.drawText("cont2",Pcontrol2.x,Pcontrol2.y,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
           case MotionEvent.ACTION_DOWN:
               ptd=new Point((int)event.getX(),(int)event.getY());
               plst=ptd;
               if(inCircle(ptd,Pstart))
               {
                   touchwhich=0;
               }
               else if(inCircle(ptd,Pend))
               {
                   touchwhich=1;
               }
               else if(inCircle(ptd,Pcontro1)){
                   touchwhich=2;
               }
               else if(inCircle(ptd,Pcontrol2)){
                   touchwhich=3;
               }
               else{
                   Log.e("out of ","****************");
                   touchwhich=-1;
                   return  false;
               }

                 break;
            case MotionEvent.ACTION_MOVE:
                float dx=event.getX()-plst.x;
                float dy=event.getY()-plst.y;
                plst.x=(int)event.getX();
                plst.y=(int)event.getY();
                switch (touchwhich){
                    case 0:
                        resetPoint(Pstart,(int)dx,(int)dy);

                        break;
                    case 1:
                        resetPoint(Pend,(int)dx,(int)dy);
                        break;
                    case 2:
                        resetPoint(Pcontro1,(int)dx,(int)dy);
                        break;
                    case 3:
                        resetPoint(Pcontrol2,(int)dx,(int)dy);
                        break;
                    case -1:
                        break;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;


        }
        this.invalidate();
        return true;
    }
    public boolean inCircle(Point touchDown,Point Circlecenter){
        Double d=Math.sqrt((touchDown.x-Circlecenter.x)*(touchDown.x-Circlecenter.x)+(touchDown.y-Circlecenter.y)*(touchDown.y-Circlecenter.y));
        if(d<=34)
            return true;
        else
            return false;
    }
    private void resetPoint(Point point,int dx,int dy){
        point.x=point.x+dx;
        point.y=point.y+dy;
    }
}
