package com.koterwong.androidhero.chapter_05;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.koterwong.androidhero.R;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/02 13:45
 * <p/>
 * Description:这里要明白：移动的是整个view的父布局的内容。
 * 设置背景的颜色不会移动，但是内容会被移动。
 * <p/>
 * =================================================
 */
public class DragView_05_By_Scroller extends View {

    private Scroller mScroller;

    private static final String TAG = "DragView_05_By_Scroller";

    public DragView_05_By_Scroller(Context context) {
        super(context);
        this.init();
    }

    public DragView_05_By_Scroller(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) private void init() {
        mScroller = new Scroller(getContext());
        setBackgroundColor(getResources().getColor(R.color.amber));
        setElevation(5f);
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.colorPrimary));
        paint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));

        String text = "ScrollTo/ScrollBy和Scroller对象";
        float width = paint.measureText(text);
        float height = (paint.descent() + paint.ascent());
        canvas.drawText(text, 0, text.length(), getWidth() / 2 - width / 2, getHeight() / 2 - height / 2, paint);
    }

    private int mLastX;
    private int mLastY;

    @Override public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = (int) event.getX();
                mLastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mScroller.isFinished()) {
                    break;
                }
                int dx = x - mLastX;
                int dy = y - mLastY;
//                scrollBy(-dx, -dy);
                scrollTo(-dx + getScrollX(), -dy + getScrollY());
                Log.d(TAG, "onTouchEvent: " + "dx = " + dx + "dy = " + dy);
                Log.d(TAG, "onTouchEvent: " + "getScrollX()" + getScrollX() + "getScrollY()" + getScrollY());
                mLastX = (int) event.getX();
                mLastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
                mScroller.startScroll(getScrollX(), getScrollY(), -getScrollX(), -getScrollY(), 400);
                invalidate();
        }
        return true;
    }

    @Override public void computeScroll() {
        super.computeScroll();
        // 判断Scroller是否执行完毕
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            // 通过重绘来不断调用computeScroll
            invalidate();
        }
    }
}
