package com.koterwong.androidhero.chapter_07.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.ButterKnife;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/02 10:44
 * <p/>
 * Description:
 * =================================================
 */
public abstract class BaseHolder {

    protected Context mContext;
    protected View mContentView;
    protected LayoutInflater mInflater;

    public BaseHolder(Context context) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mContentView = this.initView();
        ButterKnife.bind(this, mContentView);
    }

    private View initView() {
        return mInflater.inflate(getLayoutId(), null);
    }

    protected abstract int getLayoutId();

    public abstract void initData();

    public View getContentView() {
        return mContentView;
    }
}
