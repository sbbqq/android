package com.example.moduleview.developerandroid.CustomView;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by alone-nine-sword on 18-3-16.
 */

public class ViewDragHelperLearn extends LinearLayout {
    ViewDragHelper viewDragHelper;
    View viewdragnormal,viewdragupback,viewedetouch;
    Point PointOfDragback;
    public ViewDragHelperLearn(Context context) {
        super(context);
    }

    public ViewDragHelperLearn(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    viewDragHelper=ViewDragHelper.create(this,1,new ViewDragHelper.Callback(){

         @Override
         public boolean tryCaptureView(View child, int pointerId) {
             return (child==viewdragupback||child==viewedetouch);
         }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
//             if(left>getWidth()-getPaddingLeft()-child.getWidth())
//                 return  getWidth()-getPaddingLeft()-child.getWidth();
//             else if(left<0)
//                 return 0;
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return top;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
           // super.onViewReleased(releasedChild, xvel, yvel);
            if(releasedChild==viewdragupback){
                viewDragHelper.settleCapturedViewAt(PointOfDragback.x,PointOfDragback.y);
                invalidate();
            }
        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            viewDragHelper.captureChildView(viewedetouch,pointerId);
            invalidate();
        }
    });
    viewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
         return viewDragHelper.shouldInterceptTouchEvent(ev);

    }
    @Override
    public void computeScroll()
    {
        Log.e("comptueScroll","**********************88");
        if(viewDragHelper.continueSettling(true))
        {
            invalidate();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        viewdragnormal=getChildAt(0);
        viewdragupback=getChildAt(1);
        viewedetouch=getChildAt(2);
        Log.e("onFinishInflate","***********************************");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.e("onlayout","*********************************");
        PointOfDragback=new Point(viewdragupback.getLeft(),viewdragupback.getTop());

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("onMeasure","*********************************");

    }
}
