package com.koterwong.androidhero.chapter_06.surfaceview;

import android.content.Context;

import com.koterwong.androidhero.R;
import com.koterwong.androidhero.chapter_06.viewholder.BaseHolder;


/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/03 21:01
 * <p>
 * Description:
 * =================================================
 */
public class SurfaceHolder extends BaseHolder {

    public SurfaceHolder(Context context) {
        super(context);
    }

    @Override protected int getLayoutId() {
        return R.layout.view_surface;
    }

    @Override public void initData() {

    }
}
