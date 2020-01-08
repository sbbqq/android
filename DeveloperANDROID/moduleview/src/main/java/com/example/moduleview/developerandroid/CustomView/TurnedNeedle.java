package com.example.moduleview.developerandroid.CustomView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.moduleview.R;

/**
 * Created by wqq on 18-6-7.
 */

public class TurnedNeedle extends View {
    private float currentValue = 0;     // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度

    private float[] pos;                // 当前点的实际位置
    private float[] tan;                // 当前点的tangent值,用于计算图片所需旋转的角度
    private Bitmap mBitmap;             // 箭头图片
    private Matrix mMatrix;// 矩
    Paint mDeafultPaint,paintother;
    public TurnedNeedle(Context context) {
        super(context);
        ini();
    }

    public TurnedNeedle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        ini();
    }

    public TurnedNeedle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ini();
    }
    public void ini(){
        mDeafultPaint=new Paint();
        mDeafultPaint.setStyle(Paint.Style.STROKE);
        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize =6;       // 缩放图片
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.huixing, options);
        mMatrix = new Matrix();
        paintother=new Paint();
        paintother.setStyle(Paint.Style.STROKE);
        paintother.setStrokeWidth(20);
        paintother.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();                                 // 创建 Path
        canvas.translate(500,500);
        path.addCircle(0, 0, 350, Path.Direction.CW);           // 添加一个圆形

        PathMeasure measure = new PathMeasure(path, false);     // 创建 PathMeasure

        currentValue += 0.001;                                  // 计算当前的位置在总长度上的比例[0,1]
        if (currentValue >= 1) {
            currentValue = 0;
        }

// 获取当前位置的坐标以及趋势的矩阵
       // measure.getMatrix(measure.getLength() * currentValue, mMatrix,  PathMeasure.POSITION_MATRIX_FLAG );//PathMeasure.TANGENT_MATRIX_FLAG || PathMeasure.POSITION_MATRIX_FLAG
        measure.getPosTan(measure.getLength() * currentValue,pos,tan);

       // mMatrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);   // <-- 将图片绘制中心调整到与当前点重合(注意:此处是前乘pre)
        mMatrix.reset();                                                        // 重置Matrix
        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI); // 计算图片旋转角度
        mMatrix.preTranslate(pos[0], pos[1]);//pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2
        mMatrix.preRotate(degrees-90+9 );   // 旋转图片//mBitmap.getWidth() / 2, mBitmap.getHeight() / 2

        canvas.drawPath(path, mDeafultPaint);                                   // 绘制 Path
        canvas.drawBitmap(mBitmap, mMatrix, mDeafultPaint);
        // 绘制箭头
        canvas.drawPoint(0,0,paintother);
        canvas.drawCircle(0,0,430,mDeafultPaint);

        invalidate();
    }
}
