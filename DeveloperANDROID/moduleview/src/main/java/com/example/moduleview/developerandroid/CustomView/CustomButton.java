package com.example.moduleview.developerandroid.CustomView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.moduleview.R;

/**
 * Created by wqq on 18-6-15.
 */

public class CustomButton extends View {
    Paint paint;
    private String text="null";
    int state=1;//1 left kong 2 left 实心　３　 left kong 4 right shi
    public CustomButton(Context context) {
        super(context);
        init();
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
       init();
    }

    public int getState() {
        return state;

    }

    public void setState(int state) {
        this.state = state;
        //invalidate();
    }
    public void init(){
     paint=new Paint();
//        paint.setTextAlign(Paint.Align.CENTER);
//        paint.setStyle(Paint.Style.FILL);
        this.setFocusable(true);
        this.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Log.e("luelue-get","************");
                    setState(2);
                  // setBackground(getResources().getDrawable(R.drawable.chose));
                }
                else{
                    Log.e("luelue-leave","************");
                    setState(1);
                    //setBackground(getResources().getDrawable(R.drawable.unchose));
                }
                invalidate();
            }
        });

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("custom-onDraw",state+"");



        canvas.drawText(text,getMeasuredWidth()/2,getMeasuredHeight()/2,paint);
        if(state==1){
            Bitmap    bmp = BitmapFactory.decodeResource(getResources(),R.drawable.unchose );
            canvas.drawBitmap(bmp,0,0,paint);
        }
        else if(state==2){
            Bitmap    bmp = BitmapFactory.decodeResource(getResources(),R.drawable.chose );
            canvas.drawBitmap(bmp,0,0,paint);

        }
        else if(state==3){
            this.setBackground(getResources().getDrawable(R.drawable.chose));
        }
        else if(state==4){
            this.setBackground(getResources().getDrawable(R.drawable.chose));
        }





    }

}
