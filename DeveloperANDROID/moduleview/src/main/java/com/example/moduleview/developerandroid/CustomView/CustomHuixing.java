package com.example.moduleview.developerandroid.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by alone-nine-sword on 18-3-19.
 */

public class CustomHuixing extends View {
    int Endangle=90;
    int nowangle=0;
    Paint paintLast;

    public CustomHuixing(Context context) {
        super(context);
    }

    public CustomHuixing(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomHuixing(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 绘图线程
        new Thread()
        {
            public void run()
            {
                while (true)
                {

                    if(nowangle<Endangle){
                        nowangle++;
                    }
                    postInvalidate();
                    try
                    {
                        Thread.sleep(100);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            };
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.dra
    }
}
