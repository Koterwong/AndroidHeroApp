package com.koterwong.androidhero.chapter_06;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/03 14:28
 * <p>
 * Description:仪表盘
 * =================================================
 */
public class ClockView extends View {

    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        //绘制外圈圆。
        Paint mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(5);
        canvas.drawCircle(width / 2, height / 2, height / 2 , mCirclePaint);

        //绘制刻度和文字
        Paint mDegreePaint = new Paint();
        mDegreePaint.setStrokeWidth(3);
        for (int i = 0; i < 24; i++) {
            if (i == 0 || i == 6 || i == 12 || i == 18) {
                mDegreePaint.setStrokeWidth(5);
                mDegreePaint.setTextSize(30);
                canvas.drawLine(width / 2, 0, width / 2, 60, mDegreePaint);
                canvas.drawText(String.valueOf(i),
                        width / 2 - mDegreePaint.measureText(String.valueOf(i)) / 2,
                        60 + mDegreePaint.measureText(String.valueOf(i)), mDegreePaint);
            } else {
                mDegreePaint.setStrokeWidth(3);
                mDegreePaint.setTextSize(15);
                canvas.drawLine(width / 2, 0, width / 2, 40, mDegreePaint);

                canvas.drawText(String.valueOf(i),
                        width / 2 - mDegreePaint.measureText(String.valueOf(i)) / 2,
                        40 + mDegreePaint.measureText(String.valueOf(i)), mDegreePaint);
            }
            canvas.rotate(15, width / 2, height / 2);
        }

        //绘制中心点
        Paint mPointerPaint = new Paint();
        mPointerPaint.setStrokeWidth(30);
        canvas.drawPoint(width / 2, height / 2, mPointerPaint);
        //平移画板前将之前的绘制内容保存
        canvas.save();
        //移动画板绘制时针
        canvas.translate(width / 2, height / 2);
        Paint mHourPaint = new Paint();
        mHourPaint.setStrokeWidth(20);
        canvas.drawLine(0, 0, 0, -50, mHourPaint);
        //绘制分针
        Paint mMinutePaint = new Paint();
        mMinutePaint.setStrokeWidth(10);
        canvas.drawLine(0, 0, -70, 0, mMinutePaint);
        canvas.restore();
    }
}
