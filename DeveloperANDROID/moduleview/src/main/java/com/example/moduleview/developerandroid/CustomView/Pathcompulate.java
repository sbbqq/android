package com.example.moduleview.developerandroid.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.moduleview.R;

/**
 * Created by wqq on 18-6-19.
 */

public class Pathcompulate extends View {
    Path pathBig,pathSmall;
    Paint paint,paintcu;
    public Pathcompulate(Context context) {
        super(context);
        ini();
    }

    public Pathcompulate(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        ini();
    }

    public Pathcompulate(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ini();
    }
    public void ini(){
        paint=new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(20);
        paint.setColor(Color.parseColor("#50fffa0d"));

        paintcu=new Paint();
        paintcu.setStyle(Paint.Style.FILL);
        paintcu.setStrokeWidth(40);
        paintcu.setColor(getResources().getColor(R.color.colorRedhalf));
        
        pathBig=new Path();
        pathSmall=new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawyouwant(canvas);
        drawLine(canvas);
    }

    public void drawyouwant(Canvas canvas){
       // canvas.translate(getMeasuredWidth()/2,getMeasuredHeight()/2);
        //pathSmall.addRect(0,0,200,200, Path.Direction.CCW);
        pathSmall.addRect(0,0,600,600, Path.Direction.CCW);
        pathBig.lineTo(600,0);
        pathBig.lineTo(300,200);
        pathBig.close();
        pathSmall.addPath(pathBig);

        Path temp=new Path();
        pathSmall.addRect(700,0,1300,600, Path.Direction.CCW);
        temp.moveTo(700,0);
        temp.lineTo(1300,0);
        temp.lineTo(1000,300);
        temp.close();
        pathSmall.addPath(temp);

        Path temp2=new Path();
       // pathSmall.addRect(700,0,1300,600, Path.Direction.CCW);
        temp2.moveTo(700,600);
        temp2.lineTo(1000,400);
        temp2.lineTo(1300,600);
        temp2.close();

        //pathSmall.addRect(1400,0,1900,600, Path.Direction.CCW);
        pathSmall.addRoundRect(new RectF(1400,0,1900,600),30,30, Path.Direction.CW);
        Path pathtangle=new Path();
        pathtangle.moveTo(1600,600);
        pathtangle.lineTo(1650,700);
        pathtangle.lineTo(1700,600);
        pathtangle.close();
        pathSmall.addPath(pathtangle);
       pathSmall.addPath(temp2);
        //pathSmall=new  Path();
        pathSmall.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(pathSmall,paint);

    }

    public void drawLine(Canvas canvas){
        canvas.drawLine(0,0,800,900,paint);
        canvas.drawLine(0,0,800,900,paintcu);
    }
}
