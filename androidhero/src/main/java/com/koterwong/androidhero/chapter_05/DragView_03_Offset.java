package com.koterwong.androidhero.chapter_05;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.koterwong.androidhero.R;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/02 11:44
 * <p>
 * Description:
 * =================================================
 */
public class DragView_03_Offset extends View {

    public DragView_03_Offset(Context context) {
        super(context);
        this.initView();
    }

    public DragView_03_Offset(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP) private void initView() {
        setBackgroundColor(getResources().getColor(R.color.orange));
        setElevation(5f);
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.colorPrimary));
        paint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,16,getResources().getDisplayMetrics()));

        String text = "offsetLeftAndRight";
        float width = paint.measureText(text);
        float height = (paint.descent() + paint.ascent()) ;
        canvas.drawText(text, 0, text.length(), getWidth() / 2 - width / 2, getHeight() / 2 - height / 2, paint);
    }

    private int mLastX;
    private int mLastY;

    @Override public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = x-mLastX;
                int dy = y-mLastY;
                offsetLeftAndRight(dx);
                offsetTopAndBottom(dy);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
