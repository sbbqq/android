package com.example.moduleview.developerandroid.CustomView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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
    Paint mDeafultPaint;
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
        options.inSampleSize =4;       // 缩放图片
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.needle, options);
        mMatrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();                                 // 创建 Path
        canvas.translate(500,500);
        path.addCircle(0, 0, 400, Path.Direction.CW);           // 添加一个圆形

        PathMeasure measure = new PathMeasure(path, false);     // 创建 PathMeasure

        currentValue += 0.001;                                  // 计算当前的位置在总长度上的比例[0,1]
        if (currentValue >= 1) {
            currentValue = 0;
        }

// 获取当前位置的坐标以及趋势的矩阵
        measure.getMatrix(measure.getLength() * currentValue, mMatrix, PathMeasure.TANGENT_MATRIX_FLAG | PathMeasure.POSITION_MATRIX_FLAG );//PathMeasure.TANGENT_MATRIX_FLAG || PathMeasure.POSITION_MATRIX_FLAG

        mMatrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);   // <-- 将图片绘制中心调整到与当前点重合(注意:此处是前乘pre)

        canvas.drawPath(path, mDeafultPaint);                                   // 绘制 Path
        canvas.drawBitmap(mBitmap, mMatrix, mDeafultPaint);                     // 绘制箭头

        invalidate();
    }
}
