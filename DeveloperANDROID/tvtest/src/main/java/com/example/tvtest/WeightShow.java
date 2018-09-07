package com.example.tvtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.tvtest.adpter.AttrValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wqq on 18-8-14.
 */

public class WeightShow extends View {
    Paint Ptext;
    Paint Pitem;
    Paint PTipbg;
    Paint PTiptext;
    float width, height,widthreal;
   private AttrValue attrValue;
    private float h1 = 0.25f, h2 = 0.5f, h3 = 0.75f;//四行起始所佔百分比
    private float hper0 = 0.3f, hper1 = 0.28f, hper2 = 0.15f, hper3 = 0.27f;
    private float widpercent = 0.005f;// 間隔
    private String textTip="1250";
    private int textTipwidth;
    private int margin=80;
    private float progress=0.50f;
    Path pathTip;

    public AttrValue getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(AttrValue attrValue) {
        this.attrValue = attrValue;
        this.invalidate();
    }

    public WeightShow(Context context) {
        super(context);
        ini();
    }

    public WeightShow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        ini();
    }

    public WeightShow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ini();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthreal = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        width=widthreal-margin;
        Log.e("width", width + "height:" + height);
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    private void ini() {
        Ptext = new Paint();
        Ptext.setColor(Color.parseColor("#888888"));
        Ptext.setAntiAlias(true);
        Ptext.setStyle(Paint.Style.FILL);
        Ptext.setTextSize(21);

        Pitem = new Paint();
        Pitem.setColor(Color.parseColor("#FFF000"));
        Pitem.setAntiAlias(true);
        Pitem.setStyle(Paint.Style.FILL);

        PTipbg = new Paint();
        PTipbg.setColor(Color.parseColor("#5AAEFF"));
        PTipbg.setAntiAlias(true);
        PTipbg.setStyle(Paint.Style.FILL);

        PTiptext = new Paint();
        PTiptext.setColor(Color.parseColor("#FFFFFF"));
        PTiptext.setAntiAlias(true);
        PTiptext.setTextSize(24);
        PTiptext.setTextAlign(Paint.Align.CENTER);
        PTiptext.setStyle(Paint.Style.FILL);
        pathTip=new Path();

        List<String> clors = new ArrayList<>();
        clors.add("#10D3DE");
        clors.add("#26A6FF");
        clors.add("#FF6E00");
        clors.add("#FF475D");
        List<String> standvalues = new ArrayList<>();
        standvalues.add("偏瘦");
        standvalues.add("标准");
        standvalues.add("偏胖");
        standvalues.add("肥胖");
        List<String> standvaluess = new ArrayList<>();
        standvaluess.add("18");
        standvaluess.add("22");
        standvaluess.add("27");
        attrValue = new AttrValue(standvalues, standvaluess, clors);

    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        computeStandWidth(canvas);

        drawAllstand(canvas);
        drawStandName(canvas);
        drawStandValue(canvas);
       drawTip(canvas,progress);
    }

    /**
     * 根据tip内容动态计算宽度
     */
    private void computeStandWidth(Canvas canvas){
        canvas.translate(margin/2,0);
    }

    private void drawAllstand(Canvas canvas) {

        int size = attrValue.getClors().size();
        float widunit =  ((width - (size - 1) * widpercent * width) / size);
        Log.e("widunit", widunit + "");
        for (int i = 0; i < attrValue.getClors().size(); i++) {
            Pitem.setColor(Color.parseColor(attrValue.getClors().get(i)));
            if (i == 0) {
                RectF rectF = new RectF();
                RectF rectFcircle = new RectF(0, hper0 * height + hper1 * height, hper2 * height, hper0 * height + hper1 * height + hper2 * height);
                rectF.set(hper2 * height / 2, hper0 * height + hper1 * height, widunit, hper0 * height + hper1 * height + hper2 * height);

                canvas.drawArc(rectFcircle, 90, 180, true, Pitem);
                canvas.drawRect(rectF, Pitem);
            } else if (i == size - 1) {
                RectF rectF = new RectF();
                RectF rectFcircle = new RectF(width - hper2 * height, hper0 * height + hper1 * height, width, hper0 * height + hper1 * height + hper2 * height);
                rectF.set(width - widunit, hper0 * height + hper1 * height, width - hper2 * height / 2, hper0 * height + hper1 * height + hper2 * height);
                canvas.drawArc(rectFcircle, 270, 180, true, Pitem);
                canvas.drawRect(rectF, Pitem);
            } else {
                RectF rectF = new RectF();
                rectF.set((widunit + widpercent * width) * i, hper0 * height + hper1 * height, (widunit + widpercent * width) * i + widunit, hper0 * height + hper1 * height + hper2 * height);
                canvas.drawRect(rectF, Pitem);
            }
        }
    }


    /**
     * draw valuename
     * @param canvas
     */

    private void drawStandName(Canvas canvas) {
        int size = attrValue.getStandNames().size();
        int widunit = (int) ((width) / size);
        RectF rectF=new RectF();
        for (int i = 0; i < size; i++) {
             rectF.set(i*widunit,(hper0 +hper1+hper2)*height,(i+1)*widunit,height);
             String text=attrValue.getStandNames().get(i);
             Ptext.setTextAlign(Paint.Align.CENTER);
            Rect rectbound = new Rect();

            Ptext.getTextBounds(text, 0, text.length(), rectbound);
             canvas.drawText(text,rectF.centerX(),rectF.centerY()+rectbound.height()/2,Ptext);
        }

    }



    /**
     * draw value
     * @param canvas
     */
    private void drawStandValue(Canvas canvas){
        int size = attrValue.getStandValues().size();
        int widunit = (int) ((width - (size) * widpercent * width) / (size+1));
        for(int i=0;i<size;i++){
            String value=attrValue.getStandValues().get(i);
            float x=widunit*(i+1)+widpercent * width*i+widpercent*width/2;
            float y=hper0*height+2*hper1*height/3;
            Log.e("x",x+"");

            Ptext.setTextAlign(Paint.Align.CENTER);
          canvas.drawText(value,x,y,Ptext);
        }

    }




    private void drawTip(Canvas canvas,float percernt){

        int size = attrValue.getClors().size();
        float widunit =  ((width - (size - 1) * widpercent * width) / size);
        float number;
        number=percernt/(1f/size);
        int NumInt=(int)number;
        float added=number-NumInt;
        float Xpos;
        if(number>1.0&&number<size+0.0f)
         Xpos=(widunit+widpercent * width)*NumInt+widunit*added;
        else if(number<1.0){
            Xpos=(widunit)*NumInt+widunit*added;
        }
        else{
            Xpos=(widunit+widpercent * width)*NumInt-widpercent*width;
        }


        float xCirLeft,xCirRight;
        xCirLeft=Xpos-hper0*height/2;
        xCirRight=Xpos+hper0*height/2;
        pathTip.reset();
        pathTip.addArc(new RectF(xCirLeft-hper0*height/2,0,Xpos,hper0*height),90,180);

        pathTip.addRect(new RectF(xCirLeft,0,xCirRight,hper0*height), Path.Direction.CW);
        pathTip.addArc(new RectF(Xpos,0,xCirRight+hper0*height/2,hper0*height),270,180);
        canvas.drawPath(pathTip,PTipbg);
      // canvas.drawArc(new RectF(xCirLeft-hper0*height/2,0,Xpos,hper0*height),90,180,true,PTipbg);
      // PTipbg.setColor(Color.YELLOW);
       // canvas.drawRect(xCirLeft,0,xCirRight,hper0*height,PTipbg);
      // canvas.drawArc(new RectF(Xpos,0,xCirRight+hper0*height/2,hper0*height),270,180,true,PTipbg);
        //PTipbg.setColor(Color.BLACK);

        canvas.drawLine(Xpos,hper0*height,Xpos,(hper0+hper1+hper2)*height+10,PTipbg);

        Rect rectbound = new Rect();
        String text="1250";
        RectF rectF=new RectF(xCirLeft-hper0*height/2,0,xCirRight+hper0*height/2,hper0*height);
        PTiptext.getTextBounds(text, 0, text.length(), rectbound);
        canvas.drawText(text,rectF.centerX(),rectF.centerY()+rectbound.height()/2,PTiptext);

    }



}