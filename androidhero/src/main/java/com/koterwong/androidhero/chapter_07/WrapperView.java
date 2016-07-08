package com.koterwong.androidhero.chapter_07;

import android.view.View;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/04 09:53
 * <p/>
 * Description:这种情况试用于View的属性没有set/get方法的情况下。
 * 相当于给View提供了set/get方法。
 * =================================================
 */
public class WrapperView {

    private View view ;

    public WrapperView(View view) {
        this.view = view;
    }

    public int getWidth(){
        return view.getLayoutParams().width;
    }

    public void setWidth(int width){
        view.getLayoutParams().width = width;
        view.requestLayout();
    }

}
