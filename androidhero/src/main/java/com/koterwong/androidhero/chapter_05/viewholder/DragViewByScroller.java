package com.koterwong.androidhero.chapter_05.viewholder;

import android.content.Context;
import android.view.View;

import com.koterwong.androidhero.R;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/02 13:49
 * <p>
 * Description:
 * =================================================
 */
public class DragViewByScroller extends BaseHolder {

    public DragViewByScroller(Context context) {
        super(context);
    }

    @Override protected View initView() {
        return mInflater.inflate(R.layout.view_drag_by_scroller,null);
    }

    @Override public void initData() {

    }
}
