package com.koterwong.androidhero.chapter_06;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/03 15:45
 * <p/>
 * Description:图层是一中栈的结构。
 * =================================================
 */
public class LayerTestView extends View {

    private static final int LAYER_FLAGS =
            Canvas.MATRIX_SAVE_FLAG |
                    Canvas.CLIP_SAVE_FLAG |
                    Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                    Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                    Canvas.CLIP_TO_LAYER_SAVE_FLAG;

    public LayerTestView(Context context) {
        super(context);
    }

    public LayerTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制背景
//        canvas.drawColor(getResources().getColor(R.color.alpha_15_black));

        Paint mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(150, 150, 100, mPaint);

        //创建图层，入栈。之后所有的操作发生在这个图层上
        canvas.saveLayerAlpha(150, 150, 350, 350, 127, LAYER_FLAGS);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(250, 250, 100, mPaint);

        //保存图层，出栈将图层上的内容绘制到canvas上
        canvas.restore();

        canvas.saveLayerAlpha(400,400,800,800,127,LAYER_FLAGS);
        canvas.drawColor(Color.GREEN);

        canvas.restore();
    }
}
