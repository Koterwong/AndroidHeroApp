package com.koterwong.androidhero.chapter_03;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/05/31 21:04
 * <p>
 * Description:给TextView设置两个矩形的背景
 * =================================================
 */
public class RectTextView extends TextView {

    /**
     * 绘制矩形的画笔
     */
    private Paint paint1;
    /**
     * 绘制背景的画笔
     */
    private Paint paint2;
    /**
     * 外部矩形
     */
    private RectF mRectF1;
    /**
     * 内部矩形
     */
    private RectF mRectF2;

    public RectTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context);
    }

    public RectTextView(Context context) {
        this(context, null);
    }

    private void init(Context context) {
        paint1 = new Paint();
        paint1.setColor(context.getResources().getColor(android.R.color.holo_blue_light));
        paint1.setStyle(Paint.Style.FILL);
        paint2= new Paint();
        paint2.setColor(Color.YELLOW);
        paint2.setStyle(Paint.Style.FILL);

        mRectF1 = new RectF();
        mRectF2 = new RectF();
    }

    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF1.left = 0;
        mRectF1.top  =0;
        mRectF1.right = getMeasuredWidth();
        mRectF1.bottom = getMeasuredHeight();

        mRectF2.left = 10;
        mRectF2.top = 10;
        mRectF2.right = getMeasuredWidth()-10;
        mRectF2.bottom = getMeasuredHeight()-10;
    }

    @Override protected void onDraw(Canvas canvas) {
        canvas.drawRect(mRectF1,paint1);
        canvas.drawRect(10,10,getMeasuredWidth()-10,getMeasuredHeight()-10,paint2);
        canvas.save();
        //drawText之前让画板平移画板平移。
//        canvas.translate(10,0);
        super.onDraw(canvas);
        canvas.restore();
    }
}
