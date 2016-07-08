package com.koterwong.androidhero.chapter_07.viewholder;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.koterwong.androidhero.MyApp;
import com.koterwong.androidhero.R;
import com.koterwong.androidhero.utils.DeviceUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/04 13:12
 * <p/>
 * Description:
 * =================================================
 */
public class DrapTestHolder extends BaseHolder {

    @Bind(R.id.app_icon)
    ImageView appIcon;
    @Bind(R.id.hidden_view)
    LinearLayout hiddenView;

    private int mHiddenViewHeight;

    public DrapTestHolder(Context context) {
        super(context);
    }

    @Override protected int getLayoutId() {
        return R.layout.anim_drap_layout;
    }

    @Override public void initData() {
        mHiddenViewHeight = DeviceUtils.dp2px(MyApp.getApp(),200);
    }

    @OnClick( {R.id.app_icon, R.id.tv_hidden, R.id.hidden_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.app_icon:
                if (hiddenView.getVisibility() == View.GONE) {
                    // 打开动画
                    appIcon.setImageResource(R.drawable.ic_keyboard_arrow_up_black_36dp);
                    animateOpen(hiddenView);
                } else {
                    // 关闭动画
                    appIcon.setImageResource(R.drawable.ic_keyboard_arrow_down_black_36dp);
                    animateClose(hiddenView);
                }
                break;
            case R.id.tv_hidden:
                break;
            case R.id.hidden_view:
                break;
        }
    }

    private void animateOpen(final View view) {
        view.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(view, 0, mHiddenViewHeight);
        animator.start();
    }

    private void animateClose(final View view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    private ValueAnimator createDropAnimator(final View view, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }
}
