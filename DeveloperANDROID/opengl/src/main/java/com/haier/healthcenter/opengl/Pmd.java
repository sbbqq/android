package com.haier.healthcenter.opengl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;



/**
 * Created by wqq on 19-4-18.
 */

public class Pmd extends LinearLayout {
    ImageView i1,i2,i3;
    boolean show=true;
    int where=1;
    Handler handler=new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.e("handler","what"+msg.what);
            super.handleMessage(msg);
           showAnimation((where));
           where++;
            this.sendEmptyMessageDelayed(where,1000);
        }
    };



    public Pmd(Context context) {
        super(context);
        inflate();
    }

    public Pmd(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate();
    }

    public Pmd(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate();
    }

    private void inflate(){
           i1=(ImageView)findViewById(R.id.i1);
        i2=(ImageView)findViewById(R.id.i2);
        i3=(ImageView)findViewById(R.id.i3);



    }

    private void showAnimation(int  i){
        Log.e("showAni",i+"");
        AlphaAnimation alphaAnimation=new AlphaAnimation(0,1);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setFillAfter(true);
        AlphaAnimation alphaAnimation2=new AlphaAnimation(1,0);
        alphaAnimation2.setDuration(1000);
        alphaAnimation2.setFillAfter(true);
        i=i%3;
        switch (i){

            case 0:

                i1.setVisibility(VISIBLE);
                i1.setAnimation(alphaAnimation);
                i1.startAnimation(alphaAnimation);

                i2.clearAnimation();
                i2.setVisibility(INVISIBLE);
                i3.setAnimation(alphaAnimation2);
                i3.startAnimation(alphaAnimation2);
                //i3.clearAnimation();
                //i3.setVisibility(INVISIBLE);


                break;
            case 1:
                i2.setVisibility(VISIBLE);
                i2.setAnimation(alphaAnimation);
                i2.startAnimation(alphaAnimation);
                i1.setAnimation(alphaAnimation2);
                i1.startAnimation(alphaAnimation2);
                i3.clearAnimation();
                i3.setVisibility(INVISIBLE);

                break;
            case 2:
                i3.setVisibility(VISIBLE);
                i3.setAnimation(alphaAnimation);
                i3.startAnimation(alphaAnimation);
                i2.setAnimation(alphaAnimation2);
                i2.startAnimation(alphaAnimation2);
                i1.clearAnimation();
                i1.setVisibility(INVISIBLE);

                break;

        }



    }



    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e("child",getChildCount()+"");
        inflate();
        handler.sendEmptyMessage(where);





    }
}
