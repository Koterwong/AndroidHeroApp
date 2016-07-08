package com.koterwong.androidhero.chapter_03;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/01 13:26
 * <p/>
 * Description:自定义的音频条
 * =================================================
 */
public class VolumeView extends View {

    private int mWidth;
    private int mRectWidth;
    /**
     * 音频条的最大高度
     */
    private int mMaxRectHeight;
    private Paint mPaint;
    private int mRectCount = 12;

    /**
     * 每个音频条之间的偏移量
     */
    private int mOffset;

    public VolumeView(Context context) {
        super(context);
        initView();
    }

    public VolumeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public VolumeView(Context context, AttributeSet attrs,
                      int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mMaxRectHeight = getHeight();
        mRectWidth = mWidth / mRectCount;

        LinearGradient mLinearGradient = new LinearGradient(
                0,
                0,
                mRectWidth,
                mMaxRectHeight,
                Color.YELLOW,
                Color.BLUE,
                Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mRectCount; i++) {
            double mRandom = Math.random();
            float currentHeight = (float) (mMaxRectHeight * mRandom);
            canvas.drawRect(
                    i * mRectWidth,
                    currentHeight,
                    (float) ((1 + i) * mRectWidth) - (float) (mRectWidth * 0.2),
                    mMaxRectHeight,
                    mPaint);
        }
        postInvalidateDelayed(500);
    }

    public int getmRectCount() {
        return mRectCount;
    }

    public void setmRectCount(int mRectCount) {
        this.mRectCount = mRectCount;
    }
}
