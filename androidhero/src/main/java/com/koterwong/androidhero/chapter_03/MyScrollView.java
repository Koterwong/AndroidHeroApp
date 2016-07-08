package com.koterwong.androidhero.chapter_03;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Description:
 * <p/>
 * Created By：Koterwong; Time: 2016/07/06 10:45
 */
public class MyScrollView extends ViewGroup {

    private int mScreenHeight;
    private ViewDragHelper mViewDragHelper;


    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    private void init() {
        mViewDragHelper = ViewDragHelper.create(this,getCallback());
        mScreenHeight = getResources().getDisplayMetrics().heightPixels;
    }

    private ViewDragHelper.Callback getCallback() {
        return new ViewDragHelper.Callback() {
            @Override public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override public int clampViewPositionVertical(View child, int top, int dy) {
                return super.clampViewPositionVertical(child, top, dy);
            }

            @Override public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
            }

            @Override public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
            }
        };
    }


    @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
