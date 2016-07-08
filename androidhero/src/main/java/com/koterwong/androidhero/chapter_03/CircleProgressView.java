package com.koterwong.androidhero.chapter_03;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/01 11:54
 * <p/>
 * Description: 考虑状态的保存与恢复
 * =================================================
 */
public class CircleProgressView extends View {

    private int mHeight;
    private int mWidth;
    /**
     * 绘制内部圆需要的成员变量
     */
    private Paint mCirclePaint;
    private float mCenter;
    private float mInsideRadius;
    /**
     * 绘制外面弧度所需要的成员变量
     */
    private Paint mArcPaint;
    private RectF mArcRectF;
    private float mSweepAngle = 90;
    /**
     * 绘制中间Text
     */
    private Paint mTextPaint;
    private String mShowText;
    private float mShowTextSize;

    public CircleProgressView(Context context, AttributeSet attrs,
                              int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleProgressView(Context context) {
        super(context);
    }

    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        this.initView();
    }

    private void initView() {
        float length = 0;
        length = Math.min(mWidth,mHeight);

        mCenter = length / 2;
        mInsideRadius = (float) (length * 0.5 / 2);
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(getResources().getColor(android.R.color.holo_blue_bright));

        mArcRectF = new RectF(
                (float) (length * 0.1), (float) (length * 0.1),
                (float) (length * 0.9), (float) (length * 0.9));

        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(getResources().getColor(android.R.color.holo_blue_bright));
        mArcPaint.setStrokeWidth((float) (length * 0.05));
        mArcPaint.setStyle(Paint.Style.STROKE);

        mShowText = setShowText();
        mShowTextSize = setShowTextSize();
        mTextPaint = new Paint();
        mTextPaint.setTextSize(mShowTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }
    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制圆
        canvas.drawCircle(mCenter, mCenter, mInsideRadius, mCirclePaint);
        // 绘制弧线
        canvas.drawArc(mArcRectF, -90, mSweepAngle, false, mArcPaint);
        // 绘制文字
        canvas.drawText(mShowText, 0, mShowText.length(),
                mCenter, mCenter + (mShowTextSize / 4), mTextPaint);
    }

    private float setShowTextSize() {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,20,getResources().getDisplayMetrics());
    }

    private String setShowText() {
        return "Android Skill";
    }

    public void forceInvalidate() {
        this.invalidate();
    }

    public void setSweepValue(float sweepValue) {
        if (sweepValue >= 0) {
            mSweepAngle = sweepValue;
        } else {
            mSweepAngle = 90;
        }
        this.invalidate();
    }
}
