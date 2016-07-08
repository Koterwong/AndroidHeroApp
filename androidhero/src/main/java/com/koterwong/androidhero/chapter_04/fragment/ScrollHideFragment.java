package com.koterwong.androidhero.chapter_04.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.koterwong.androidhero.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/01 22:08
 * <p>
 * Description: 滑动ListView自动隐藏Toolbar
 * =================================================
 */
public class ScrollHideFragment extends Fragment {

    @Bind(R.id.chat_list_view)
    ListView mListView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    /**
     * 系统认为是滑动的最小滑动距离
     */
    private int touchSlop;
    private int direction;
    private boolean isShow = true;
    private ObjectAnimator mAnimator;
    String[] mDatas = new String[20];

    /**
     * ListView滑动监听。
     */
    private AbsListView.OnTouchListener myOnTouchListener = new AbsListView.OnTouchListener() {
        private int downY;

        @Override public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    downY = (int) event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int mCurrentY = (int) event.getY();
                    if (downY - mCurrentY > touchSlop) {
                        direction = 0;  //hide
                    } else if (mCurrentY - downY > touchSlop) {
                        direction = 1; //show
                    }
                    if (direction == 0) {
                        if (isShow) {
                            toolbarAnim(direction);
                            isShow = !isShow;
                        }
                    } else if (direction == 1) {
                        if (!isShow) {
                            toolbarAnim(direction);
                            isShow = !isShow;
                        }
                    }
                    break;
            }
            return false;
        }
    };

    private void toolbarAnim(int direction) {

        if (mAnimator != null && mAnimator.isRunning()) {
            mAnimator.cancel();
        }
        if (direction == 0) {
            //hide
            mAnimator = ObjectAnimator.ofFloat(toolbar, "translationY", toolbar.getTranslationY(), -toolbar.getHeight());
        } else {
            mAnimator = ObjectAnimator.ofFloat(toolbar, "translationY", toolbar.getTranslationY(), 0);
        }
        mAnimator.start();
    }


    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_hide_list_view, container, false);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取TouchSlop
        touchSlop = ViewConfiguration.get(getActivity()).getScaledTouchSlop();

        for (int i = 0; i < mDatas.length; i++) {
            mDatas[i] = "条目 " + i;
        }
        mListView.setOnTouchListener(myOnTouchListener);
        mListView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mDatas));
        View header = new View(getActivity());
        header.setLayoutParams(new AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT,
                (int) getActivity().getResources().getDimension(R.dimen.abc_action_bar_default_height_material)));
        //添加HeadView
        mListView.addHeaderView(header);
    }

    @Override public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
