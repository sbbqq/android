package com.example.moduleview.developerandroid.CustomView;

import android.content.Context;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by alone-nine-sword on 18-3-13.
 */

public class HelloCustomViewGroup extends ViewGroup {
    public HelloCustomViewGroup(Context context) {
        super(context);
    }

    public HelloCustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HelloCustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
               int count=getChildCount();

               MarginLayoutParams layoutParams;
        //layoutParams=(MarginLayoutParams)getLayoutParams();
               for(int i=0;i<count;i++){
                   int cl=0,ct=0,cr,cb;
                   View Childview=getChildAt(i);
                   layoutParams=(MarginLayoutParams)Childview.getLayoutParams();
                   switch (i){
                       case 0:
                          cl=0+layoutParams.leftMargin;
                          ct=0;
                           break;
                       case 1:
                           cl=getMeasuredWidth()-getChildAt(i).getMeasuredWidth()-0;
                           ct=0;
                           break;
                       case 2:
                           cl=0;
                           ct=getMeasuredHeight()-getChildAt(i).getMeasuredHeight()-0;
                           break;
                       case 3:
                           cl=getMeasuredWidth()-getChildAt(i).getMeasuredWidth()-0;
                           ct=getMeasuredHeight()-getChildAt(i).getMeasuredHeight()-0;
                           break;
                   }
                   cr=cl+getChildAt(i).getMeasuredWidth();
                   cb=ct+getChildAt(i).getMeasuredHeight();
                   Log.e("pos","i:"+i+"left:"+cl+"ct:"+ct+"cr:"+cr+"cb:"+cb);
                   getChildAt(i).layout(cl,ct,cr,cb);

               }
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthmode=MeasureSpec.getMode(widthMeasureSpec);
        int heightmode= MeasureSpec.getMode(heightMeasureSpec);
        int width=0,height=0;
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        int count=getChildCount();
        int topwidth=0,botwidth=0;
        int  lheight=0,rheight=0;
        MarginLayoutParams layoutParams;
        for(int i=0;i<count;i++){
            View childview=getChildAt(i);
            if(i==0||i==1){
               layoutParams=(MarginLayoutParams)childview.getLayoutParams();
                topwidth=topwidth+getChildAt(i).getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
            }

            if(i==2||i==3){
               layoutParams=(MarginLayoutParams)getLayoutParams();
                botwidth=botwidth+getChildAt(i).getMeasuredWidth()+0+0;
            }


            if(i==0||i==2){
               // layoutParams=(MarginLayoutParams)getLayoutParams();
                lheight=lheight+getChildAt(i).getMeasuredWidth()+0+0;
            }


            if(i==1||i==3){
                //layoutParams=(MarginLayoutParams)getLayoutParams();
                rheight=rheight+getChildAt(i).getMeasuredWidth()+0+0;
            }
        }
        width=Math.max(topwidth,botwidth);
        height=Math.max(lheight,rheight);
        setMeasuredDimension(widthmode==MeasureSpec.EXACTLY?MeasureSpec.getSize(widthMeasureSpec):width,heightmode==MeasureSpec.EXACTLY?MeasureSpec.getSize(heightMeasureSpec):height);

    }

    // 继承自margin，支持子视图android:layout_margin属性
//    public static class LayoutParamss extends MarginLayoutParams {
//
//
//        public LayoutParamss(Context c, AttributeSet attrs) {
//            super(c, attrs);
//        }
//
//
//        public LayoutParamss(int width, int height) {
//            super(width, height);
//        }
//
//
//        public LayoutParamss(ViewGroup.LayoutParams source) {
//            super(source);
//        }
//
//
//        public LayoutParamss(ViewGroup.MarginLayoutParams source) {
//            super(source);
//        }
//    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }
}
