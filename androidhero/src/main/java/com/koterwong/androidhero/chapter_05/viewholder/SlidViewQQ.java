package com.koterwong.androidhero.chapter_05.viewholder;

import android.content.Context;
import android.view.View;

import com.koterwong.androidhero.R;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/02 16:34
 * <p>
 * Description:
 * =================================================
 */
public class SlidViewQQ extends BaseHolder {

    public SlidViewQQ(Context context) {
        super(context);
    }

    @Override protected View initView() {
        return mInflater.inflate(R.layout.view_slid_view_qq,null);
    }

    @Override public void initData() {

    }
}
