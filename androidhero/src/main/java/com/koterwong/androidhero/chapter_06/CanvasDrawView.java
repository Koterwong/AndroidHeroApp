package com.koterwong.androidhero.chapter_06;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Description:
 * <p/>
 * Created By：Koterwong; Time: 2016/07/07 10:32
 */
public class CanvasDrawView extends View {

    private static final String TAG = "CanvasDrawView";

    public CanvasDrawView(Context context) {
        super(context);
    }

    public CanvasDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getResources().getDisplayMetrics().widthPixels,
                getResources().getDisplayMetrics().heightPixels);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) @SuppressLint("DrawAllocation")
    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //1、绘制矩形、演示paint stroke和fill却别
        Paint rectPaint = new Paint();
        rectPaint.setAntiAlias(true);
        rectPaint.setColor(0xff232323);
        rectPaint.setStyle(Paint.Style.STROKE);
        Rect rect = new Rect(dp2px(10), dp2px(10), dp2px(50), dp2px(50));
        canvas.drawRect(rect,rectPaint);
        rectPaint.setStyle(Paint.Style.FILL);
        rect = new Rect(dp2px(60), dp2px(10), dp2px(100), dp2px(50));
        canvas.drawRect(rect,rectPaint);
        rectPaint.setTextSize(dp2px(14));
        canvas.drawText("STROKE和FILL的区别", dp2px(110), dp2px(50),rectPaint);

        rectPaint.setStrokeWidth(dp2px(1));
        //绘制点,确定坐标即可
        canvas.drawPoint(dp2px(10),dp2px(60),rectPaint);
        //绘制直线
        canvas.drawLine(dp2px(50),dp2px(60),dp2px(100),dp2px(80),rectPaint);
        //绘制多条直线
        float []lines = new float[] {dp2px(110),dp2px(60),dp2px(200),dp2px(60),dp2px(200),dp2px(60),dp2px(220),dp2px(80)
        ,dp2px(220),dp2px(80),dp2px(180),dp2px(80) };
        canvas.drawLines(lines,rectPaint);
        //绘制圆角矩形5.0以上
        canvas.drawRoundRect(dp2px(10),dp2px(100),dp2px(60),dp2px(150),dp2px(3),dp2px(3),rectPaint);
        //绘制圆
        canvas.drawCircle(dp2px(120),dp2px(125),dp2px(25),rectPaint);
        //绘制椭圆
        RectF rectFOval = new RectF(dp2px(160),dp2px(100),dp2px(230),dp2px(150));
        canvas.drawOval(rectFOval,rectPaint);
        //绘制弧
        RectF rectF = new RectF(dp2px(10), dp2px(160), dp2px(60), dp2px(210));
        canvas.drawArc(rectF,0,120,true,rectPaint);
        rectF = new RectF(dp2px(70), dp2px(160), dp2px(120), dp2px(210));
        canvas.drawArc(rectF,0,120,false,rectPaint);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectF = new RectF(dp2px(130), dp2px(160), dp2px(180), dp2px(210));
        canvas.drawArc(rectF,0,120,true,rectPaint);
        rectF = new RectF(dp2px(190), dp2px(160), dp2px(240), dp2px(210));
        canvas.drawArc(rectF,0,120,false,rectPaint);
        rectPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("useCenter:true 后false",dp2px(10),dp2px(230),rectPaint);

        //绘制path
        Path path = new Path();
        rectPaint.setStyle(Paint.Style.FILL);
        path.moveTo(dp2px(10),dp2px(240));
        path.quadTo(dp2px(55),dp2px(260),dp2px(100),dp2px(240));
        path.lineTo(dp2px(100),dp2px(280));
        path.quadTo(dp2px(55),dp2px(260),dp2px(10),dp2px(280));
        canvas.drawPath(path,rectPaint);
    }

    public int dp2px(int dpV) {
        return (int) (getResources().getDisplayMetrics().density * dpV);
    }
}
