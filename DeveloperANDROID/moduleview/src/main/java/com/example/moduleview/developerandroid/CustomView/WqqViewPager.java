package com.example.moduleview.developerandroid.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

/**
 * Created by alone-nine-sword on 18-5-30.
 */

public class WqqViewPager extends ViewGroup {
    int move=0;//偏移量
    int XTouchDown,YTouchDown;
    int XLast,YLast;
    public WqqViewPager(Context context) {
        super(context);
    }

    public WqqViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WqqViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e("onLayout","****************");

                Log.e("onLayout+true","****************");
                View viewchild;
                for(int i=0;i<getChildCount();i++){
                    viewchild=getChildAt(i);
                    viewchild.layout(move+i*viewchild.getMeasuredWidth(),0,move+(i+1)*viewchild.getMeasuredWidth(),viewchild.getMeasuredHeight());
                }

          Log.e("chilonescrollX","scroX:"+getChildAt(0).getScrollX()+"rawx:"+getChildAt(0));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthsuggest=MeasureSpec.getSize(widthMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightsuggest=MeasureSpec.getSize(heightMeasureSpec);
        int heigthMode=MeasureSpec.getMode(heightMeasureSpec);
        int realsetwidth,realsetheight;
        View viewchild;
        for(int i=0;i<getChildCount();i++){
            viewchild=getChildAt(i);
            viewchild.measure(widthMeasureSpec,heightMeasureSpec);
        }
        if(widthMode== MeasureSpec.EXACTLY){
           realsetwidth=widthsuggest;
        }
        else{


            int wantwidsize=getChildAt(0).getMeasuredWidth();
            realsetwidth=wantwidsize;
        }
        if(heigthMode==MeasureSpec.EXACTLY){
            realsetheight=heightsuggest;
        }
        else{
           realsetheight=getChildAt(0).getMeasuredHeight();
        }
        Log.e("onMeasure","reasetwidth:"+realsetwidth+"");
        setMeasuredDimension(realsetwidth,realsetheight);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       Log.e("onTouchevent","********************"+event.getAction());
        switch (event.getAction()){
            case  MotionEvent.ACTION_DOWN:
                XTouchDown=(int)event.getX();
                YTouchDown=(int)event.getY();
                XLast=XTouchDown;
                YLast=YTouchDown;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("onTouchevent","********************");
                int dx=(int)event.getX()-XLast;
                 XLast=(int)event.getX();
                 if(move+dx<=0&&move+dx>=-(getChildAt(0).getMeasuredWidth()*2)) {
                     move = move + dx;
                     Log.e("move", move + "");
                     requestLayout();
                 }
                 else{
                     Log.e("out of bandline","***out********************");
                 }

                break;
            case MotionEvent.ACTION_UP:
                if(move>-(getChildAt(0).getMeasuredWidth()/2)){
                    move=0;
                }
                else if(move>-(3*getChildAt(0).getMeasuredWidth()/2)){
                    move=-getChildAt(0).getMeasuredWidth();
                }
                else{
                    move=-2*getChildAt(0).getMeasuredWidth();
                }
                requestLayout();
                break;

        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
       switch (ev.getAction()){
           case MotionEvent.ACTION_DOWN:
               XTouchDown=(int)ev.getX();
               YTouchDown=(int)ev.getY();
               XLast=XTouchDown;
               YLast=YTouchDown;
               break;
           case MotionEvent.ACTION_MOVE:
               Log.e("onTouchevent","********************");
               int dx=(int)ev.getX()-XLast;
               XLast=(int)ev.getX();

               if(Math.abs(dx)>2) {
                   //move = move + dx;
                   //requestLayout();
                   return  true;
               }
               break;
           case MotionEvent.ACTION_UP:
               break;
       }
        return false;
    }
}
