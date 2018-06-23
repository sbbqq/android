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
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.moduleview.Modal.Blood;
import com.example.moduleview.R;

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
    private float xtop=50;
    private int YaxisNumber=4;
    private int YaxisLengthMar=40;
    private int XaxisLengthMar=100;
    private int lengthFlag=20;
    Paint paintYaxis,paintXaxis;
    Path PathYaxis,PathXaxis;
    private float Maxvalue=240;
    private float Minvalue=0;
    private boolean Zerostart=true;
    private int strokewidth=10;

    Path pathAll;//高压低压之间的阴影
    Paint paintshadow;

    //tips 属性
    Path pathTips;
    Paint paintTips;
    Paint paintTipValue;
    private int paintTextSize=20;
    private int tipwidth=100;
    private int tipheight=50;
    private int tanglebotmar=10;
    private int tanglexmove=10;
    private int tangleymove=15;
    private int tangleruondxy=5;
    private int Ymove=40;

    //ｙ轴标尺数据,以及网格－－－横线
    Paint paintYvalue,paintYline;
    private int YvalueLeft=40;

    int controlN=0;
    private int Number=6;





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
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "Number", 0,6);
        animator.setDuration(duration);
        animator.start();
    }

    public void init(){
        pathAll=new Path();
        PathXaxis=new Path();
        PathYaxis=new Path();

        paintXaxis=new Paint();
        paintXaxis.setStyle(Paint.Style.FILL);
        paintXaxis.setStrokeWidth(strokewidth);
        paintXaxis.setStrokeCap(Paint.Cap.ROUND);
        paintXaxis.setAntiAlias(true);



        paintYaxis=new Paint();
        paintYaxis.setStyle(Paint.Style.FILL);
        paintYaxis.setStrokeWidth(strokewidth);
        paintYaxis.setStrokeCap(Paint.Cap.ROUND);
        paintYaxis.setAntiAlias(true);
       // paintYaxis.setTextSize(200);

        paintshadow=new Paint();
        paintshadow.setStyle(Paint.Style.FILL);
        paintshadow.setColor(getResources().getColor(R.color.colorRedhalf));
        paintshadow.setStrokeCap(Paint.Cap.ROUND);
        paintshadow.setAntiAlias(true);
       // paintshadow.setStrokeWidth(10);
        Log.e("textsizs","paintshow:size:"+paintshadow.getTextSize()+"heeh");



      //tips
        pathTips=new Path();
        paintTips=new Paint();
        paintTips=new Paint();
        paintTips.setStyle(Paint.Style.FILL);
        paintTips.setColor(getResources().getColor(R.color.colorRedhalf));
        paintTips.setStrokeCap(Paint.Cap.ROUND);
        paintTips.setAntiAlias(true);

        paintTipValue=new Paint();
        paintTipValue.setTextAlign(Paint.Align.CENTER);
        paintTipValue.setStyle(Paint.Style.FILL);
//        paintTipValue.setStrokeWidth(strokewidth);
        paintTipValue.setTextSize(paintTextSize);
        paintTipValue.setColor(Color.WHITE);
        paintTipValue.setStrokeCap(Paint.Cap.ROUND);
        paintTipValue.setAntiAlias(true);



        //ｙ轴标尺以及网格线－Ｙ线
        paintYvalue=new Paint();
        paintYvalue.setTextAlign(Paint.Align.CENTER);
        paintYvalue.setStyle(Paint.Style.FILL);
//        paintYvalue.setStrokeWidth(strokewidth);
        paintYvalue.setTextSize(paintTextSize);
        paintYvalue.setColor(Color.BLUE);

        paintYvalue.setStrokeCap(Paint.Cap.ROUND);
        paintYvalue.setAntiAlias(true);

        paintYline=new Paint(Paint.ANTI_ALIAS_FLAG);
        paintYline.setTextAlign(Paint.Align.CENTER);
      paintYline.setStrokeWidth(2);
        //paintYline.setTextSize(paintTextSize);
        paintYline.setColor(Color.BLUE);
        paintYline.setPathEffect(new DashPathEffect(new float[] {5, 5}, 0));
        paintYline.setStrokeCap(Paint.Cap.ROUND);
        paintYline.setAntiAlias(true);
//        Path path = new Path();
//        path.moveTo(50, 50);
//        path.lineTo(50, 200);
//        canvas.drawPath(path, paint);
       // controlN=0;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (controlN<=7){
//                    postInvalidate();
//                    try{
//                        Thread.sleep(300);
//                        controlN++;
//                    }
//                    catch (Exception e){
//
//                    }
//                }
//            }
//        }).start();

    }



    @Override
    protected void onDraw(Canvas canvas) {
       // super.onDraw(canvas);
        //setLayerType(LAYER_TYPE_SOFTWARE, null);//关闭硬件加速
        Log.e("oandraw","ondraw");
        if (mPointList == null)
            return;
        //measurePath();

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
          String text=(60*(i+1))+"";
          Rect rectboud=new Rect();
            paintYvalue.getTextBounds(text,0,text.length(),rectboud);
          canvas.drawText(text,YvalueLeft,height-xbottom-(i+1)*((heightReal-YaxisLengthMar)/YaxisNumber)+rectboud.height()/2,paintYvalue);
          //画横着的虚线

            canvas.drawLine(yLeft,height-xbottom-(i+1)*((heightReal-YaxisLengthMar)/YaxisNumber),width-yright,height-xbottom-(i+1)*((heightReal-YaxisLengthMar)/YaxisNumber),paintYline);

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
        Log.e("drawblood","***************88");

        for(int i=0;i<=Number;i++) {
            if (bloodList.size() > i) {
                pathAll.reset();
                pathTips.reset();
                Log.e("drawblood", i + "");
                int x = (int) (yLeft + (i + 1) * ((widthReal - XaxisLengthMar) / bloodList.size()));
                Point pointhigh = new Point(x, 0);
                Point pointlow = new Point(x, 0);

                Blood blood = bloodList.get(i);
                pointhigh.set(x, (int) getRealY((float) blood.getHighvalue()));
                pointlow.set(x, (int) getRealY((float) blood.getLowvalue()));
                // canvas.drawLine(pointhigh.x,pointhigh.y,pointlow.x,pointlow.y,paintXaxis);
                Point pointtemp = new Point();
                Point pointtemp1 = new Point();
                Point pointtemp2 = new Point();
                Point pointtemp3 = new Point();
                pointtemp.x = pointhigh.x;
                pointtemp.y = pointhigh.y;
                Point phighleft = getSwordPos(true, true, pointtemp);
                //Point phgihright=getSwordPos(false,true,pointtemp);
                canvas.drawLine(pointhigh.x, pointhigh.y, phighleft.x, phighleft.y, paintYaxis);
                pointtemp1.x = pointhigh.x;
                pointtemp1.y = pointhigh.y;
                Point phgihright = getSwordPos(false, true, pointtemp1);
                canvas.drawLine(pointhigh.x, pointhigh.y, phgihright.x, phgihright.y, paintYaxis);


                //low
                pointtemp2.x = pointlow.x;
                pointtemp2.y = pointlow.y;
                Point plowleft = getSwordPos(true, false, pointtemp2);
                //Point phgihright=getSwordPos(false,true,pointtemp);
                canvas.drawLine(pointlow.x, pointlow.y, plowleft.x, plowleft.y, paintYaxis);
                pointtemp3.x = pointlow.x;
                pointtemp3.y = pointlow.y;
                Point plowright = getSwordPos(false, false, pointtemp3);
                canvas.drawLine(pointlow.x, pointlow.y, plowright.x, plowright.y, paintYaxis);

                Log.e("i:", "hx:" + phighleft.x + "hy:" + phighleft.y + "plowrx:" + plowright.x + "plowry:" + plowright.y);
                pathAll.addRect(phighleft.x, phighleft.y, plowright.x, plowright.y, Path.Direction.CW);
                Path low = new Path();
                low.moveTo(pointlow.x, pointlow.y);
                low.lineTo(plowleft.x, plowleft.y);
                low.lineTo(plowright.x, plowright.y);
                low.close();
                pathAll.addPath(low);

                Path hign = new Path();
                hign.moveTo(pointhigh.x, pointhigh.y);
                hign.lineTo(phighleft.x, phighleft.y);
                hign.lineTo(phgihright.x, phgihright.y);
                hign.close();
                pathAll.addPath(hign);
                pathAll.setFillType(Path.FillType.EVEN_ODD);
                paintshadow.setColor(getResources().getColor(R.color.colorRedhalf));

                //画ｔｉｐｓ
                Point point = new Point();
                point.set(pointhigh.x, pointhigh.y - moveY - tangleymove);
                RectF rectFtip = new RectF(point.x - tipwidth / 2, point.y - tipheight, point.x + tipwidth / 2, point.y);

                pathTips.addRoundRect(rectFtip, tangleruondxy, tangleruondxy, Path.Direction.CW);
                Path tangle = new Path();
                tangle.moveTo(point.x - tanglebotmar, point.y);
                tangle.lineTo(point.x, point.y + tangleymove);
                tangle.lineTo(point.x + tanglebotmar, point.y);
                tangle.close();
                pathTips.addPath(tangle);
                canvas.drawPath(pathAll, paintshadow);
                canvas.drawPath(pathTips, paintTips);
                Rect rectbound = new Rect();
                String text = bloodList.get(i).getLowvalue() + "/" + bloodList.get(i).getHighvalue();
                paintTipValue.getTextBounds(text, 0, text.length(), rectbound);

                canvas.drawText(text, point.x, point.y - tipheight / 2 + rectbound.height() / 2, paintTipValue);
            }


            canvas.drawText("Testdfsfsf", 200, 200, paintshadow);
        }
    }


    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
        invalidate();
    }

    private int moveX=20;
    private int moveY=25;
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
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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
