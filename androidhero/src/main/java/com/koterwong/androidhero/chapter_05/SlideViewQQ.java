package com.koterwong.androidhero.chapter_05;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/02 16:06
 * <p>
 * Description:
 * =================================================
 */
public class SlideViewQQ extends FrameLayout {

    private ViewDragHelper mViewDragHelper;

    private int mMenuWidth;
    private View mMainView;
    private View mMenuView;

    public SlideViewQQ(Context context) {
        this(context, null);
    }

    public SlideViewQQ(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideViewQQ(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView();
    }

    private void initView() {
        mViewDragHelper = ViewDragHelper.create(this, callBack);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuView = getChildAt(0);
        mMainView = getChildAt(1);
    }

    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMenuWidth = mMenuView.getMeasuredWidth();
    }

    private ViewDragHelper.Callback callBack = new ViewDragHelper.Callback() {

        //何时开始检验触摸事件。
        @Override public boolean tryCaptureView(View child, int pointerId) {
            return mMainView == child;
        }

        //触摸到View时回调
        @Override public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }

        //当拖拽状态改变，比如idle、dragging
        @Override public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
        }

        //当位置改变时回调，常用于滑动时更改scale
        @Override public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }

        //垂直方向滑动
        @Override public int clampViewPositionVertical(View child, int top, int dy) {
            return super.clampViewPositionVertical(child, top, dy);
        }

        //水平方向滑动、
        @Override public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (left >= mMenuWidth)
                left = mMenuWidth;
            if (left <= 0) {
                left = 0;
            }
            return left;
        }

        //手指松开，拖动结束时。
        @Override public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (mMainView.getLeft() < mMenuWidth / 2) {
                mViewDragHelper.smoothSlideViewTo(mMainView, 0, 0);
                ViewCompat.postInvalidateOnAnimation(SlideViewQQ.this);
            } else {
                mViewDragHelper.smoothSlideViewTo(mMainView, mMenuWidth, 0);
                ViewCompat.postInvalidateOnAnimation(SlideViewQQ.this);
            }
        }
    };

    @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override public void computeScroll() {
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
