package com.example.moduleview.developerandroid.CustomView;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Created by wqq on 2018/6/12.
 */

public class WqqBezierView extends View {
    private float lineSmoothness = 0.2f;
    private List<Point> mPointList;
    private Path mPath;
    private Path mAssistPath;
    private float drawScale = 1f;
    private PathMeasure mPathMeasure;
    private float defYAxis = 600f;
    private float defXAxis = 10f;
    int width,height;
    float widthReal,heightReal;
    private float yLeft=100;
    private float xbottom=100;
    private float yright=20;
    private float xtop=20;
    private int YaxisNumber=5;
    private int YaxisLengthMar=40;
    private int XaxisLengthMar=40;
    private int lengthFlag=20;
    Paint paintYaxis,paintXaxis;
    Path PathYaxis,PathXaxis;
    private float Maxvalue=100;
    private float Minvalue=0;
    private boolean Zerostart=true;


    public WqqBezierView(Context context) {
        super(context);
        init();
    }

    public WqqBezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setPointList(List<Point> pointList) {
        mPointList = pointList;
        getYmaxAndYminValue();
        measurePath();

    }

    public void setLineSmoothness(float lineSmoothness) {
        if (lineSmoothness != this.lineSmoothness) {
            this.lineSmoothness = lineSmoothness;
            measurePath();
            postInvalidate();
        }
    }

    public void setDrawScale(float drawScale) {
        this.drawScale = drawScale;
        postInvalidate();
    }

    public void startAnimation(long duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "drawScale", 0f, 1f);
        animator.setDuration(duration);
        animator.start();
    }

    public void init(){
        PathXaxis=new Path();
        PathYaxis=new Path();
        paintXaxis=new Paint();
        paintXaxis.setStyle(Paint.Style.FILL_AND_STROKE);
        paintXaxis.setStrokeWidth(10);
        paintXaxis.setStrokeCap(Paint.Cap.ROUND);
        paintXaxis.setAntiAlias(true);
        paintYaxis=new Paint();
        paintYaxis.setStyle(Paint.Style.FILL_AND_STROKE);
        paintYaxis.setStrokeWidth(10);
        paintYaxis.setStrokeCap(Paint.Cap.ROUND);
        paintYaxis.setAntiAlias(true);

    }



    @Override
    protected void onDraw(Canvas canvas) {
        if (mPointList == null)
            return;
        //measurePath();
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        //绘制辅助线
        canvas.drawPath(mAssistPath,paint);
        drawAxis(canvas);

        paint.setColor(Color.GREEN);
        Path dst = new Path();
        dst.rLineTo(0, 0);
        float distance = mPathMeasure.getLength() * drawScale;
        if (mPathMeasure.getSegment(0, distance, dst, true)) {
            //绘制线
            canvas.drawPath(dst, paint);
            float[] pos = new float[2];
            mPathMeasure.getPosTan(distance, pos, null);
            //绘制阴影
            drawShadowArea(canvas, dst, pos);
            //绘制点
            drawPoint(canvas,pos);
        }
        /*greenPaint.setPathEffect(getPathEffect(mPathMeasure.getLength()));
        canvas.drawPath(mPath, greenPaint);*/
        //mPath.reset();adb shell screenrecord --bit-rate 2000000 /sdcard/test.mp4

    }

    /**
     * 绘制阴影
     * @param canvas
     * @param path
     * @param pos
     */
    private void drawShadowArea(Canvas canvas, Path path, float[] pos) {
        path.lineTo(pos[0], height-xbottom);
        path.lineTo(yLeft, height-xbottom);
        path.close();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0x88CCCCCC);
        canvas.drawPath(path, paint);
    }

    /**
     * 绘制点
     * @param canvas
     * @param pos
     */
    private void drawPoint(Canvas canvas, final float[] pos){
        Paint redPaint = new Paint();
        redPaint.setColor(Color.RED);
        redPaint.setStrokeWidth(3);
        redPaint.setStyle(Paint.Style.FILL);
        for (int i=0;i<mPointList.size();i++) {

            canvas.drawCircle(getRealX(i), getRealY((float)(mPointList.get(i).y)), 20, redPaint);
        }
    }

    /**
     * 画坐标系
     * @param canvas
     */
    public void drawAxis(Canvas canvas){
        PathYaxis.moveTo(yLeft,height-xbottom);

        canvas.drawLine(yLeft,height-xbottom,yLeft,xtop,paintYaxis);
        for(int i=0;i<YaxisNumber;i++){
          canvas.drawLine(yLeft,height-xbottom-(i+1)*((heightReal-YaxisLengthMar)/YaxisNumber),yLeft+lengthFlag,height-xbottom-(i+1)*((heightReal-YaxisLengthMar)/YaxisNumber),paintYaxis);
        }
        canvas.drawLine(yLeft,height-xbottom,width-yright,height-xbottom,paintYaxis);
        for(int i=0;i<mPointList.size();i++){
            canvas.drawLine(yLeft+(i+1)*((widthReal-XaxisLengthMar)/mPointList.size()),height-xbottom,yLeft+(i+1)*((widthReal-XaxisLengthMar)/mPointList.size()),height-xbottom-lengthFlag,paintYaxis);
        }
    }

    private PathEffect getPathEffect(float length) {
        return new DashPathEffect(new float[]{length * drawScale, length}, 0);
    }

    private void measurePath() {
       float firstDiffX=0 ;
         float firstDiffY=0;
         float secondDiffX=0 ;
         float secondDiffY=0 ;
         float firstControlPointX=0 ;
         float firstControlPointY=0 ;
         float secondControlPointX=0 ;
         float secondControlPointY=0 ;
        mPath = new Path();
        mAssistPath = new Path();

        float previousPointX = Float.NaN;
        float previousPointY = Float.NaN;
        float currentPointX = Float.NaN;
        float currentPointY = Float.NaN;
        float nextPointX;
        float nextPointY;
        float nextNPointX;
        float nextNPointY;

        final int lineSize = mPointList.size();
        for (int valueIndex = 0; valueIndex <= lineSize-2; ++valueIndex) {

            currentPointX=getRealX(valueIndex);//mPointList.get(valueIndex).x;
            currentPointY=getRealY((float)(mPointList.get(valueIndex).y));
            nextPointX=getRealX(valueIndex+1);//mPointList.get(valueIndex+1).x;
            nextPointY=getRealY((float)(mPointList.get(valueIndex+1).y));

            if( valueIndex==lineSize-2) {
                nextNPointX=0;//any value
                nextNPointY =0;//any value
            }
            else{
                nextNPointX = getRealX(valueIndex + 2);//mPointList.get(valueIndex + 2).x;
                nextNPointY = getRealY((float)(mPointList.get(valueIndex+2).y));
            }



            if (valueIndex == 0) {
                // 将Path移动到开始点
                mPath.moveTo(currentPointX, currentPointY);
                mAssistPath.moveTo(currentPointX, currentPointY);
                firstDiffX = (nextPointX - currentPointX);
                firstDiffY = (nextPointY - currentPointY);
                secondDiffX = (nextNPointX - currentPointX);
                secondDiffY = (nextNPointY - currentPointY);
                firstControlPointX = currentPointX+ (lineSmoothness * firstDiffX);
                firstControlPointY = currentPointY + (lineSmoothness * firstDiffY);
                secondControlPointX = nextPointX- (lineSmoothness * secondDiffX);
                secondControlPointY = nextPointY - (lineSmoothness * secondDiffY);

            } else if(valueIndex==lineSize-2){
                firstDiffX = (nextPointX - previousPointX);
                firstDiffY = (nextPointY - previousPointY);
                secondDiffX = (nextPointX - currentPointX);
                secondDiffY = (nextPointY - currentPointY);
                firstControlPointX = currentPointX+ (lineSmoothness * firstDiffX);
                firstControlPointY = currentPointY + (lineSmoothness * firstDiffY);
                secondControlPointX = nextPointX- (lineSmoothness * secondDiffX);
                secondControlPointY = nextPointY - (lineSmoothness * secondDiffY);

            }
            else {
                // 求出控制点坐标
                firstDiffX = (nextPointX - previousPointX);
                firstDiffY = (nextPointY - previousPointY);
                secondDiffX = (nextNPointX - currentPointX);
                secondDiffY = (nextNPointY - currentPointY);
                firstControlPointX = currentPointX+ (lineSmoothness * firstDiffX);
                firstControlPointY = currentPointY + (lineSmoothness * firstDiffY);
                secondControlPointX = nextPointX- (lineSmoothness * secondDiffX);
                secondControlPointY = nextPointY - (lineSmoothness * secondDiffY);
                //画出曲线

            }
            mPath.cubicTo(firstControlPointX, firstControlPointY, secondControlPointX, secondControlPointY,
                    nextPointX, nextPointY);

            //将控制点保存到辅助路径上
            mAssistPath.lineTo(firstControlPointX, firstControlPointY);
            mAssistPath.lineTo(secondControlPointX, secondControlPointY);
            mAssistPath.lineTo(nextPointX, nextPointY);

            // 更新值,

            previousPointX = currentPointX;
            previousPointY = currentPointY;

        }
        mPathMeasure = new PathMeasure(mPath, false);
    }

    private float getRealY(Float y){
        Log.e("height",(heightReal-YaxisLengthMar)/(Maxvalue-Minvalue)*y+"");
        return  height-xbottom-(heightReal-YaxisLengthMar)/(Maxvalue-Minvalue)*y;

    }

    private float getRealX(int index){
        return yLeft+(index+1)*((widthReal-XaxisLengthMar)/mPointList.size());
    }

    private void getYmaxAndYminValue(){
        float minValueY=mPointList.get(0).y;
        float maxValueY=mPointList.get(0).y;
        for(int i=1;i<mPointList.size();i++){
            if(mPointList.get(i).y>maxValueY){
                maxValueY=mPointList.get(i).y;
            }
            if(mPointList.get(i).y<minValueY){
                minValueY=mPointList.get(i).y;
            }
        }
        Maxvalue=maxValueY;
        if(!Zerostart)
           Minvalue=minValueY;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightmode=MeasureSpec.getMode(heightMeasureSpec);
        int heightsize=MeasureSpec.getSize(heightMeasureSpec);
        int widthmode=MeasureSpec.getMode(widthMeasureSpec);
        int widthsize=MeasureSpec.getSize(widthMeasureSpec);
        if(heightmode==MeasureSpec.EXACTLY){
         width=widthsize;
        }
        else{

        }

         if(widthmode==MeasureSpec.EXACTLY){
         height=heightsize;
         }
         else{

         }
     setMeasuredDimension(widthsize,heightsize);
         widthReal=widthsize-yLeft-yright;
         heightReal=heightsize-xbottom-xtop;
         measurePath();
    }

}
