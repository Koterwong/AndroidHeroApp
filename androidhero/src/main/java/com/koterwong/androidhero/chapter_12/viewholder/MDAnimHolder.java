package com.koterwong.androidhero.chapter_12.viewholder;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;

import com.koterwong.androidhero.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/11 22:47
 * <p>
 * Description:
 * =================================================
 */
public class MDAnimHolder extends BaseHolder {



    public MDAnimHolder(Context context) {
        super(context);
    }

    @Override protected int getLayoutId() {
        return R.layout.view_circular_reveal;
    }

    @Override public void initData() {

    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP) @OnClick(R.id.fab) public void fab(View view) {
        Animator animator = ViewAnimationUtils.createCircularReveal(view,
                view.getWidth()/2,
                view.getHeight()/2,
                0,view.getWidth()/2);
        animator.setDuration(1000);
        animator.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) @OnClick(R.id.btn_ripple) public void btn_ripple(View view) {
        Animator animator = ViewAnimationUtils.createCircularReveal(
                view,
                0,
                0,
                0,
                view.getHeight()*2
        );
        animator.setDuration(1000);
        animator.start();
    }

    private boolean mIsCheck;
    private static final int[] STATE_CHECKED = new int[]{android.R.attr.state_checked};
    private static final int[] STATE_UNCHECKED = new int[]{};
    @Bind(R.id.anim_list) ImageView mImageView;

    @OnClick(R.id.anim_list) public void anim_list(View view) {
        if (mIsCheck) {
            mImageView.setImageState(STATE_UNCHECKED, true);
            mIsCheck = false;
        } else {
            mImageView.setImageState(STATE_CHECKED, true);
            mIsCheck = true;
        }
    }

}
