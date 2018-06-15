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

import com.example.moduleview.Modal.Blood;

import java.util.List;

/**
 * Created by wqq on 2018/6/12.
 */

public class WqqBloodView extends View {
    private float lineSmoothness = 0.2f;
    private List<Point> mPointList;
    private List<Blood> bloodList;
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
    private int XaxisLengthMar=100;
    private int lengthFlag=20;
    Paint paintYaxis,paintXaxis;
    Path PathYaxis,PathXaxis;
    private float Maxvalue=200;
    private float Minvalue=0;
    private boolean Zerostart=true;


    public WqqBloodView(Context context) {
        super(context);
        init();
    }

    public WqqBloodView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setPointList(List<Point> pointList) {
        mPointList = pointList;
        getYmaxAndYminValue();


    }

    public List<Blood> getBloodList() {
        return bloodList;
    }

    public void setBloodList(List<Blood> bloodList) {
        this.bloodList = bloodList;
    }

    public void setLineSmoothness(float lineSmoothness) {
        if (lineSmoothness != this.lineSmoothness) {
            this.lineSmoothness = lineSmoothness;

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
       // canvas.drawPath(mAssistPath,paint);
        drawAxis(canvas);
        drawBlood(canvas);


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
        for(int i=0;i<bloodList.size();i++){
            canvas.drawLine(yLeft+(i+1)*((widthReal-XaxisLengthMar)/bloodList.size()),height-xbottom,yLeft+(i+1)*((widthReal-XaxisLengthMar)/bloodList.size()),height-xbottom-lengthFlag,paintYaxis);
        }
    }



    /**
     *
     * @param canvas
     */
    public void drawBlood(Canvas canvas){

        for(int i=0;i<bloodList.size();i++){
            int x=(int)(yLeft+(i+1)*((widthReal-XaxisLengthMar)/bloodList.size()));
            Point pointhigh=new Point(x,0);
            Point pointlow=new Point(x,0);
            Blood blood=bloodList.get(i);
            pointhigh.set(x,(int)getRealY((float)blood.getHighvalue()));
            pointlow.set(x,(int)getRealY((float)blood.getLowvalue()));
            canvas.drawLine(pointhigh.x,pointhigh.y,pointlow.x,pointlow.y,paintXaxis);
            Point pointtemp=new Point();
            pointtemp.x=pointhigh.x;
            pointtemp.y=pointhigh.y;
            Point phighleft=getSwordPos(true,true,pointtemp);
            //Point phgihright=getSwordPos(false,true,pointtemp);
            canvas.drawLine(pointhigh.x,pointhigh.y,phighleft.x,phighleft.y,paintYaxis);
            pointtemp.x=pointhigh.x;
            pointtemp.y=pointhigh.y;
            Point phgihright=getSwordPos(false,true,pointtemp);
            canvas.drawLine(pointhigh.x,pointhigh.y,phgihright.x,phgihright.y,paintYaxis);


            //low
            pointtemp.x=pointlow.x;
            pointtemp.y=pointlow.y;
            Point plowleft=getSwordPos(true,false,pointtemp);
            //Point phgihright=getSwordPos(false,true,pointtemp);
            canvas.drawLine(pointlow.x,pointlow.y,plowleft.x,plowleft.y,paintYaxis);
            pointtemp.x=pointlow.x;
            pointtemp.y=pointlow.y;
            Point plowright=getSwordPos(false,false,pointtemp);
            canvas.drawLine(pointlow.x,pointlow.y,plowright.x,plowright.y,paintYaxis);

        }
    }
    private int moveX=20;
    private int moveY=30;
    public Point getSwordPos(boolean isLeft,boolean isTop,Point point){
        if(isLeft){
            point.x=point.x-moveX;
        }
        else{
            point.x=point.x+moveX;
        }

        if(isTop){
            point.y=point.y-moveY;
        }
        else{
            point.y=point.y+moveY;
        }
        return  point;
    }









    private float getRealY(Float y){
        Log.e("height",(heightReal-YaxisLengthMar)/(Maxvalue-Minvalue)*y+"");
        Log.e("value","y:"+y+"after:"+(height-xbottom-(heightReal-YaxisLengthMar)/(Maxvalue-Minvalue)*y));
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

    }

}
