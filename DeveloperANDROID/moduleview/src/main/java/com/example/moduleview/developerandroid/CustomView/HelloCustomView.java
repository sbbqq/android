package com.example.moduleview.developerandroid.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.moduleview.R;

/**
 * Created by alone-nine-sword on 18-3-9.
 */

public class HelloCustomView extends View {
    Paint paint;
    Paint paint2;
    String text="default";
    int color;
    public HelloCustomView(Context context) {
        super(context);
        paint=new Paint();
        paint.setColor(Color.RED);
        paint2=new Paint();
        paint2.setColor(Color.BLACK);
    }

    public HelloCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint2 = new Paint();
        paint2.setColor(Color.BLACK);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.HelloCustomView, 0, 0);
        for (int i = 0; i < typedArray.length(); i++) {
            int indexvalue = typedArray.getIndex(i);
            switch (indexvalue) {
                case R.styleable.HelloCustomView_text:
                    text = typedArray.getString(indexvalue);
                    break;
                case R.styleable.HelloCustomView_textcolor:
                    color = typedArray.getColor(indexvalue, Color.BLACK);
                    paint2.setColor(color);
                    break;
            }
        }
    }

    public HelloCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint=new Paint();
        paint.setColor(Color.RED);
        paint2=new Paint();

        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs, R.styleable.HelloCustomView,defStyleAttr,0);
        for(int i=0;i<typedArray.length();i++) {
            int indexvalue=typedArray.getIndex(i);
            switch (indexvalue){
                case R.styleable.HelloCustomView_text:
                    text=typedArray.getString(indexvalue);
                    break;
                case R.styleable.HelloCustomView_textcolor:
                    color=typedArray.getColor(indexvalue,Color.BLACK);
                    paint2.setColor(color);
                    break;
            }
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,Math.min(getMeasuredHeight()/2,getMeasuredWidth()),paint);
        canvas.drawText(text,getMeasuredWidth()/2,getMeasuredHeight()/2,paint2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
