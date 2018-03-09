package com.example.alone_nine_sword.developerandroid.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by alone-nine-sword on 18-3-9.
 */

public class HelloCustomView extends View {
    Paint paint;
    Paint paint2;
    public HelloCustomView(Context context) {
        super(context);
        paint=new Paint();
        paint.setColor(Color.RED);
        paint2=new Paint();
        paint2.setColor(Color.BLACK);
    }

    public HelloCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        paint.setColor(Color.RED);
        paint2=new Paint();
        paint2.setColor(Color.BLACK);
    }

    public HelloCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint=new Paint();
        paint.setColor(Color.RED);
        paint2=new Paint();
        paint2.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,Math.min(getMeasuredHeight()/2,getMeasuredWidth()),paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
