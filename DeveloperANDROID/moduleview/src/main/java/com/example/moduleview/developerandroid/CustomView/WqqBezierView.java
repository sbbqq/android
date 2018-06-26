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
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.moduleview.Modal.BaseResult;
import com.example.moduleview.R;

import java.util.List;

/**
 * Created by wqq on 2018/6/12.
 */

public class WqqBezierView extends View {
    private float lineSmoothness = 0.2f;
    private List<Point> mPointList;
    private List<BaseResult>baseResults;
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
    private float Maxvalue=400;
    private float Minvalue=0;
    private boolean Zerostart=true;


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
    
    
    //draw background 
    Paint paintBg;
    private int PaintWidthbg=1;
    private int gridYNumber=8;
    private int gridXNumber=14;

    //ｙ轴标尺数据,以及网格－－－横线
    Paint paintYvalue,paintYline;
    private int YvalueLeft=40;


    //draw bottom
    Paint paintBotBg,paintBotXaxis;


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
       //getYmaxAndYminValue();
        //measurePath();

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
        paintXaxis.setStrokeWidth(3);
        paintXaxis.setStrokeCap(Paint.Cap.ROUND);
        paintXaxis.setAntiAlias(true);
        paintYaxis=new Paint();
        paintYaxis.setStyle(Paint.Style.FILL_AND_STROKE);
        paintYaxis.setStrokeWidth(3);
        paintYaxis.setStrokeCap(Paint.Cap.ROUND);
        paintYaxis.setAntiAlias(true);

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


        paintBg=new Paint();
       paintBg.setStyle(Paint.Style.FILL);
       paintBg.setStrokeWidth(PaintWidthbg);
       paintBg.setTextSize(paintTextSize);
       paintBg.setColor(0x80C1CDCD);
       paintBg.setStrokeCap(Paint.Cap.ROUND);
       paintBg.setAntiAlias(true);



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

        //draw bg  set paint
        paintBg=new Paint();
        paintBg.setStyle(Paint.Style.FILL);
        paintBg.setStrokeWidth(PaintWidthbg);
        paintBg.setTextSize(paintTextSize);
        paintBg.setColor(0x80C1CDCD);
        paintBg.setStrokeCap(Paint.Cap.ROUND);
        paintBg.setAntiAlias(true);

        //draw bottom
        paintBotBg=new Paint();
        paintBotBg.setStyle(Paint.Style.FILL);
        paintBotBg.setStrokeWidth(PaintWidthbg);
        paintBotBg.setTextSize(paintTextSize);
        paintBotBg.setColor(Color.BLUE);
        paintBotBg.setStrokeCap(Paint.Cap.ROUND);
        paintBotBg.setAntiAlias(true);

        paintBotXaxis=new Paint();
        paintBotXaxis.setStyle(Paint.Style.FILL);
        paintBotXaxis.setTextAlign(Paint.Align.CENTER);
        paintBotXaxis.setStrokeWidth(PaintWidthbg);
        paintBotXaxis.setTextSize(paintTextSize);
        paintBotXaxis.setColor(Color.WHITE);
        paintBotXaxis.setStrokeCap(Paint.Cap.ROUND);
        paintBotXaxis.setAntiAlias(true);
    }



    @Override
    protected void onDraw(Canvas canvas) {

        //measurePath();
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        //绘制辅助线
       // canvas.drawPath(mAssistPath,paint);
        drawBottom(canvas);
        drawAxis(canvas);
        drawBg(canvas);

        paint.setColor(Color.GREEN);
        Path dst = new Path();
        dst.rLineTo(0, 0);
        float distance = mPathMeasure.getLength() * drawScale;

        if (mPathMeasure.getSegment(0, distance, dst, true)) {
            Log.e("drawshadowandpoint","****************"+"drawScale:"+drawScale);
            //绘制线
            canvas.drawPath(dst, paint);
            float[] pos = new float[2];
            mPathMeasure.getPosTan(distance, pos, null);
            //绘制阴影
            drawShadowArea(canvas, dst, pos);
            //绘制点
            //drawPoint(canvas,pos);
        }
        drawPoint(canvas,new float[2] );
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
        paint.setColor(0x50A4D3EE);
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
        pathTips.reset();
        for (int i=0;i<baseResults.size();i++) {
            Log.e("drawtipsandpoint","i:"+i);
            redPaint.setColor(Color.WHITE);
            BaseResult baseResult=baseResults.get(i);
            canvas.drawCircle(getRealX(i), getRealY(Float.parseFloat(baseResult.getFirstValue())), 20, redPaint);
            redPaint.setColor(Color.BLUE);
            canvas.drawCircle(getRealX(i), getRealY(Float.parseFloat(baseResult.getFirstValue())), 10, redPaint);

            //画ｔｉｐｓ
            Point point = new Point();
            point.set((int)getRealX(i), (int)getRealY(Float.parseFloat(baseResult.getFirstValue()))-Ymove);
            RectF rectFtip = new RectF(point.x - tipwidth / 2, point.y - tipheight, point.x + tipwidth / 2, point.y);
            pathTips.reset();
            pathTips.addRoundRect(rectFtip, tangleruondxy, tangleruondxy, Path.Direction.CW);
            Path tangle = new Path();
            tangle.moveTo(point.x - tanglebotmar, point.y);
            tangle.lineTo(point.x, point.y + tangleymove);
            tangle.lineTo(point.x + tanglebotmar, point.y);
            tangle.close();
            pathTips.addPath(tangle);
           // canvas.drawPath(pathAll, paintshadow);
            canvas.drawPath(pathTips, paintTips);
            Rect rectbound = new Rect();
            String text = Float.parseFloat(baseResult.getFirstValue())+"";
            paintTipValue.getTextBounds(text, 0, text.length(), rectbound);

            canvas.drawText(text, point.x, point.y - tipheight / 2 + rectbound.height() / 2, paintTipValue);
        }
    }

    /**
     * 画坐标系
     * @param canvas
     */
    public void drawAxis(Canvas canvas){
        PathYaxis.moveTo(yLeft,height-xbottom);

        //canvas.drawLine(yLeft,height-xbottom,yLeft,xtop,paintYaxis);
        for(int i=0;i<YaxisNumber;i++){
          canvas.drawLine(0,height-xbottom-(i+1)*((heightReal-YaxisLengthMar)/YaxisNumber),lengthFlag,height-xbottom-(i+1)*((heightReal-YaxisLengthMar)/YaxisNumber),paintYaxis);

            String text=(60*(i+1))+"";
            Rect rectboud=new Rect();
            paintYvalue.getTextBounds(text,0,text.length(),rectboud);
            canvas.drawText(text,YvalueLeft,height-xbottom-(i+1)*((heightReal-YaxisLengthMar)/YaxisNumber)+rectboud.height()/2,paintYvalue);
            //画横着的虚线

            canvas.drawLine(yLeft,height-xbottom-(i+1)*((heightReal-YaxisLengthMar)/YaxisNumber),width-yright,height-xbottom-(i+1)*((heightReal-YaxisLengthMar)/YaxisNumber),paintYline);
        }
        //canvas.drawLine(yLeft,height-xbottom,width-yright,height-xbottom,paintYaxis);
        for(int i=0;i<baseResults.size();i++){
           // canvas.drawLine(yLeft+(i+1)*((widthReal-XaxisLengthMar)/mPointList.size()),height-xbottom,yLeft+(i+1)*((widthReal-XaxisLengthMar)/mPointList.size()),height-xbottom-lengthFlag,paintYaxis);
            drawXaxisValue(getRealX(i),i,canvas);
        }
    }



    /**
     * draw bottom
     * @param canvas
     */
    private void drawBottom(Canvas canvas){
        RectF rectFbottom=new RectF(0,height-xbottom,width,height);
        canvas.drawRect(rectFbottom,paintBotBg);
    }
    /**
     * draw grid bg
     * @param canvas
     */
    private void drawBg(Canvas canvas){


        for(int i=0;i<gridXNumber;i++){
            canvas.drawLine(getRealX(i,gridXNumber),height-xbottom,getRealX(i,gridXNumber),xtop,paintBg);
        }

        for(int i=0;i<gridYNumber;i++){
            canvas.drawLine(yLeft,xtop+i*(heightReal)/gridYNumber,width-yright,xtop+i*(heightReal)/gridYNumber,paintBg);
        }



    }

    /**
     * draw x axis date value
     * @param x
     * @param index
     * @param canvas
     */
    private void drawXaxisValue(float x,int index,Canvas canvas){
        BaseResult baseResult=baseResults.get(index);
        String tx1="null";
        String tx2="null";
        int flag=0;//;0 第一个 1 代表 不同年 2：同年不同天（） 3 同年同天（包括同月）
        if(index==0){
            tx1=baseResult.getY()+"-"+baseResult.getM()+"-"+baseResult.getD();
            tx2=baseResult.getHour()+":"+baseResult.getMinite();
            Rect rect1=new Rect(),rect2=new Rect();
            paintBotXaxis.getTextBounds(tx1,0,tx1.length(),rect1);
            paintBotXaxis.getTextBounds(tx2,0,tx2.length(),rect2);
            canvas.drawText(tx1,x,heightReal+xtop+xbottom/4+rect1.height()/2,paintBotXaxis);
            canvas.drawText(tx2,x,heightReal+xtop+3*xbottom/4+rect2.height()/2,paintBotXaxis);
            flag=0;
        }
        else {
            flag=1;
            if(baseResults.get(index-1).getY()!=baseResult.getY()){
                tx1=baseResult.getY()+"-"+baseResult.getM()+"-"+baseResult.getD();
                tx2=baseResult.getHour()+":"+baseResult.getMinite();
                Rect rect1=new Rect(),rect2=new Rect();
                paintBotXaxis.getTextBounds(tx1,0,tx1.length(),rect1);
                paintBotXaxis.getTextBounds(tx2,0,tx2.length(),rect2);
                canvas.drawText(tx1,x,heightReal+xtop+xbottom/4+rect1.height()/2,paintBotXaxis);
                canvas.drawText(tx2,x,heightReal+xtop+3*xbottom/4+rect2.height()/2,paintBotXaxis);
            }
            else{
                if(index<=baseResults.size()-2)
                {
                    if((baseResults.get(index-1).getM()==baseResult.getM()&&baseResults.get(index-1).getD()==baseResult.getD())||(baseResults.get(index+1).getM()==baseResult.getM()&&baseResults.get(index+1).getD()==baseResult.getD())){
                        flag=3;
                    }
                    else{
                        flag=2;
                    }
                }
                else{
                    if(baseResults.get(index-1).getM()==baseResult.getM()&&baseResults.get(index-1).getD()==baseResult.getD()){
                        flag=3;
                    }
                    else{
                        flag=2;
                    }
                }

            }

            {

            }
        }
        switch ( flag){
            case 0:
                break;
            case 1:
                break;
            case 2:
                tx1=baseResult.getM()+"-"+baseResult.getD();
                // tx2=baseResult.getHour()+":"+baseResult.getMinite();
                Rect rect1=new Rect(),rect2=new Rect();
                paintBotXaxis.getTextBounds(tx1,0,tx1.length(),rect1);
                // paintBotXaxis.getTextBounds(tx2,0,tx2.length(),rect2);
                canvas.drawText(tx1,x,heightReal+xtop+xbottom/2+rect1.height()/2,paintBotXaxis);
                //  canvas.drawText(tx2,x,heightReal+3*xbottom/4-rect2.height()/2,paintBotXaxis);
                break;
            case 3:
                tx1=baseResult.getM()+"-"+baseResult.getD();
                tx2=baseResult.getHour()+":"+baseResult.getMinite();
                Rect rect11=new Rect(),rect22=new Rect();
                paintBotXaxis.getTextBounds(tx1,0,tx1.length(),rect11);
                paintBotXaxis.getTextBounds(tx2,0,tx2.length(),rect22);
                canvas.drawText(tx1,x,heightReal+xtop+xbottom/4+rect11.height()/2,paintBotXaxis);
                canvas.drawText(tx2,x,heightReal+xtop+3*xbottom/4+rect22.height()/2,paintBotXaxis);
                break;

        }

    }

    private PathEffect getPathEffect(float length) {
        return new DashPathEffect(new float[]{length * drawScale, length}, 0);
    }

    public List<BaseResult> getBaseResults() {
        return baseResults;
    }

    public void setBaseResults(List<BaseResult> baseResults) {
        this.baseResults = baseResults;
        measurePath();
        this.invalidate();
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

        final int lineSize = baseResults.size();

        for (int valueIndex = 0; valueIndex <= lineSize-2; ++valueIndex) {
              BaseResult baseResult=baseResults.get(valueIndex);
              Log.e("fisrtvalue",baseResult.getFirstValue()+"i:"+valueIndex);
            currentPointX=getRealX(valueIndex);//mPointList.get(valueIndex).x;
            currentPointY=getRealY((float)(Float.parseFloat(baseResult.getFirstValue())));
            nextPointX=getRealX(valueIndex+1);//mPointList.get(valueIndex+1).x;
            nextPointY=getRealY(Float.parseFloat(baseResults.get(valueIndex+1).getFirstValue()));

            if( valueIndex==lineSize-2) {
                nextNPointX=0;//any value
                nextNPointY =0;//any value
            }
            else{
                nextNPointX = getRealX(valueIndex + 2);//mPointList.get(valueIndex + 2).x;
                nextNPointY = getRealY(Float.parseFloat(baseResults.get(valueIndex+2).getFirstValue()));
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
        return yLeft+(index)*((widthReal-XaxisLengthMar)/(baseResults.size()-1));
    }

    private float getRealX(int index, int all){
        return yLeft+(index)*((widthReal)/all);
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
         if(baseResults!=null)
         measurePath();
    }

}
