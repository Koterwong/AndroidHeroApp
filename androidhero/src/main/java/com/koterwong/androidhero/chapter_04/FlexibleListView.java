package com.koterwong.androidhero.chapter_04;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.widget.ListView;
import android.widget.Scroller;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/01 21:34
 * <p>
 * Description:弹性ListView
 * =================================================
 */
public class FlexibleListView extends ListView {

    private int mMaxOverScrollY;

    public FlexibleListView(Context context) {
        super(context);
        this.initView(context);
    }

    public FlexibleListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context);
    }

    private void initView(Context context) {
        mMaxOverScrollY = (int) (context.getResources().getDisplayMetrics().heightPixels * 0.1);
    }

    @Override protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY,
                                             int scrollRangeX, int scrollRangeY,
                                             int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        deltaY = deltaY / 3;
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY,
                scrollRangeX, scrollRangeY, maxOverScrollX, mMaxOverScrollY, isTouchEvent);
    }
}
