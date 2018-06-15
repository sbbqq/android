package com.example.canveselement.Canves;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by alone-nine-sword on 18-4-13.
 */

public class DrawTextWay extends View {
    Paint paint;
    public DrawTextWay(Context context) {
        super(context);
        iniData();
    }

    public DrawTextWay(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        iniData();
    }
    protected void iniData(){
        paint=new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(20);
        //paint.setStrokeWidth(20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(200,200);
        canvas.drawLine(0,0,getMeasuredWidth()-200,0,paint);
        canvas.drawLine(0,0,0,getMeasuredHeight()-200,paint);
        canvas.drawText("hello,Text",0,0,paint);
        canvas.drawPosText("wqqc",new float[]{10f,10f,30f,30f,50f,50f,80f,80f},paint);
    }

    @Override
    public ViewGroup.LayoutParams getLayoutParams() {
        return super.getLayoutParams();
    }
}
