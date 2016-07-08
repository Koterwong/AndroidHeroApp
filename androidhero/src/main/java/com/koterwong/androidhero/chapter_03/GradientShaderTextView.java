package com.koterwong.androidhero.chapter_03;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/05/31 21:33
 * <p/>
 * Description:梯度颜色渐变的TextView
 *          1、设置画笔的线性梯度颜色渐变器
 *          2、不断移动的Matrix和界面重绘
 *
 * bug:关闭屏幕再开启屏幕界面重绘双层效果叠加。
 * =================================================
 */
public class GradientShaderTextView extends TextView {

    /**
     * 水平渐变梯度，继承shader着色器
     */
    private LinearGradient mLinearGradient;
    /**
     * 定义自己的矩阵
     */
    private Matrix mMatrix;

    private int mWidth = 0;
    private int mTranslate = 0;

    public GradientShaderTextView(Context context) {
        super(context);
    }

    public GradientShaderTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = getMeasuredWidth();
        if (mWidth > 0) {
            /**
             * 关键是获取当前绘制TextView的画笔。
             */
            Paint mPaint = getPaint();
            mLinearGradient = new LinearGradient(0, 0, mWidth, 0,
                    new int[]{Color.BLACK, 0xFFffffff,Color.BLACK}, null, Shader.TileMode.CLAMP);
            mPaint.setShader(mLinearGradient);
            mMatrix = new Matrix();
        }
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mMatrix != null) {
            mTranslate += mWidth / 5;
            if (mTranslate > mWidth * 2) {
                mTranslate = -mWidth;
            }
            //设置矩阵的移动范围，通过不断移动该矩阵达到渐变的效果。
            mMatrix.setTranslate(mTranslate, 0);
            //设置线性梯度着色器的矩阵范围。
            mLinearGradient.setLocalMatrix(mMatrix);
            postInvalidateDelayed(100);
        }
    }
}
