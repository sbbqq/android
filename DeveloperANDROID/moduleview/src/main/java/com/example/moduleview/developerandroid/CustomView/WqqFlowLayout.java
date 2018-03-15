package com.example.moduleview.developerandroid.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by alone-nine-sword on 18-3-15.
 */

public class WqqFlowLayout extends ViewGroup {
    private int margin=20;
    public WqqFlowLayout(Context context) {
        super(context);
    }

    public WqqFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WqqFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count=getChildCount();
        int cl,ct,cr,cb;
        int  CurrentL=0,CurrentT=0;
        int maxHeight=0;
        for(int i=0;i<count;i++){
            View Child=getChildAt(i);
            if(CurrentL+Child.getMeasuredWidth()+margin<getWidth()){
                cl=CurrentL;
                ct=CurrentT;
                CurrentL=CurrentL+Child.getMeasuredWidth()+margin;
                maxHeight=Math.max(maxHeight,Child.getMeasuredHeight());
            }
            else{

                CurrentT=CurrentT+maxHeight;
                maxHeight=Child.getMeasuredHeight();
                cl=CurrentL=0;
                ct=CurrentT;
            }
            cr=cl+Child.getMeasuredWidth();
            cb=ct+Child.getMeasuredHeight();
            Child.layout(cl,ct,cr,cb);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode=MeasureSpec.getMode(widthMeasureSpec),heghtSpecMode=MeasureSpec.getMode(heightMeasureSpec);
        int width=MeasureSpec.getSize(widthMeasureSpec),height=MeasureSpec.getSize(heightMeasureSpec);
        int count=getChildCount();
        int contentwidth=0,contentheight=0, maxHeight=0;
        for(int i=0;i<count;i++){
            View child=getChildAt(i);
            //child.measure(widthMeasureSpec,heightMeasureSpec);
              measureChild(child,widthMeasureSpec,heightMeasureSpec);
            if(contentwidth+child.getMeasuredWidth()+margin<=width){

                  maxHeight=Math.max(maxHeight,child.getMeasuredHeight());
                  contentwidth=contentwidth+child.getMeasuredWidth()+margin;
            }
            else{
                contentheight=contentheight+maxHeight;
                maxHeight=child.getMeasuredHeight();

            }
        }
        setMeasuredDimension(width,MeasureSpec.EXACTLY==heghtSpecMode?height:contentheight);

    }
}
