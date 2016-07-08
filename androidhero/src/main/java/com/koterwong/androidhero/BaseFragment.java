package com.koterwong.androidhero;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/02 08:51
 * <p/>
 * Description: 从chapter_05使用BaseFragment！
 * =================================================
 */
public abstract class BaseFragment extends Fragment {

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(getLayoutID(), container, false);
        ButterKnife.bind(this,mRootView);
        return mRootView;
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract int getLayoutID() ;

    protected abstract void initData();

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
