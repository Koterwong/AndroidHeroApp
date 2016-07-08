package com.koterwong.androidhero.chapter_05.viewholder;

import android.content.Context;
import android.view.View;

import com.koterwong.androidhero.R;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/02 12:04
 * <p>
 * Description:
 * =================================================
 */
public class DragViewUseLayout extends BaseHolder {

    public DragViewUseLayout(Context context) {
        super(context);
    }

    @Override protected View initView() {
        return mInflater.inflate(R.layout.view_drag_by_layout,null);
    }

    @Override public void initData() {

    }
}
