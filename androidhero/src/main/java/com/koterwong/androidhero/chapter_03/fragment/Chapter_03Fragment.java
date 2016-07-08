package com.koterwong.androidhero.chapter_03.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.koterwong.androidhero.R;
import com.koterwong.androidhero.chapter_03.CircleProgressView;
import com.koterwong.androidhero.chapter_03.TopBar;
import com.koterwong.androidhero.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/05/31 22:49
 * <p/>
 * Description:
 * =================================================
 */
public class Chapter_03Fragment extends Fragment {


    @Bind(R.id.top_bar)
    TopBar topBar;
    @Bind(R.id.circle_progress)
    CircleProgressView mProView;

    @Nullable @Override public View onCreateView(LayoutInflater inflater,
                                                 @Nullable ViewGroup container,
                                                 @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chapter_03, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        topBar.setOnTopbarClickListener(new TopBar.OnTopBarListener() {
            @Override public void onLeftClick(View view) {
                ToastUtils.showShort("left");
            }

            @Override public void onRightClick(View view) {
                ToastUtils.showShort("right");
            }
        });
        mHandler.sendEmptyMessageDelayed(0, 100);
    }

    @OnClick(R.id.my_scroll_view) void toMyScrollView(){
        getFragmentManager().beginTransaction()
                .addToBackStack(this.getClass().getSimpleName())
                .replace(android.R.id.content,new MyScrollViewFragment(),MyScrollViewFragment.class.getSimpleName())
                .commit();
    }

    @OnClick(R.id.my_view_layout) void toMyViewLayout(){
        getFragmentManager().beginTransaction()
                .addToBackStack(this.getClass().getSimpleName())
                .replace(android.R.id.content,new MyViewLayoutFragment(),MyViewLayoutFragment.class.getSimpleName())
                .commit();
    }

    private static int mCurrentPro = 0;
    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mCurrentPro != 360) {
                mCurrentPro += 10;
                mProView.setSweepValue(mCurrentPro);
                mHandler.sendEmptyMessageDelayed(0, 20);
            }
        }
    };
}
