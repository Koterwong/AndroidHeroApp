package com.koterwong.androidhero.chapter_07.viewholder;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.widget.TextView;

import com.koterwong.androidhero.MyApp;
import com.koterwong.androidhero.R;
import com.koterwong.androidhero.chapter_07.WrapperView;
import com.koterwong.androidhero.utils.ToastUtils;

import butterknife.OnClick;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/03 15:44
 * <p/>
 * Description:
 * =================================================
 */
public class PropertyHolder extends BaseHolder {

    public PropertyHolder(Context context) {
        super(context);
    }

    @Override protected int getLayoutId() {
        return R.layout.animator;
    }

    @Override public void initData() {

    }

    @OnClick(R.id.property_animator) public void onClick(View view) {
        ObjectAnimator oa = ObjectAnimator.ofFloat(view, "translationX", 0, 300);
        oa.setDuration(1000);
        oa.start();
    }

    @OnClick(R.id.property_wrapper) public void onClick_wrapper(View view) {
        WrapperView mWrapperView = new WrapperView(view);
        ObjectAnimator.ofInt(mWrapperView, "width", 500, 1000).setDuration(1000).start();
    }


    @OnClick(R.id.property_values_holder) public void onClickPropertyHodler(View view) {
        PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("translationX", 100f, 200f);
        PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("scaleX", 0, 1f, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvh1, pvh2).setDuration(1000).start();
    }


    @OnClick(R.id.property_values_timer) public void onClickPropertyTimer(final View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
        valueAnimator.addUpdateListener(
                new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        ((TextView) view).setText("$ " + (Integer) animation.getAnimatedValue());
                    }
                });
        valueAnimator.setDuration(3000);
        valueAnimator.start();
    }

    @OnClick(R.id.property_set) public void onClickAnimationSet(View view) {

        ObjectAnimator oa1 = ObjectAnimator.ofFloat(view, "translationX", 0, 300);
        ObjectAnimator oa2 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f, 1f);
        ObjectAnimator oa3 = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f, 1f);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
//        set.playTogether(oa1,oa2,oa3);
        set.play(oa2).before(oa3).after(oa1);
        set.start();
    }

    @OnClick(R.id.property_xml) public void onClickXml(View view) {
        Animator animator = AnimatorInflater.loadAnimator(MyApp.getApp(), R.animator.animtor_xml);
        animator.setTarget(view);
        animator.start();
    }

    @OnClick(R.id.property_viewCompat) public void onClickViewCompat(View view) {

        view.setAlpha(1);
        view.setTranslationX(50);
        ViewCompat.animate(view)
                .alpha(0.5f)
//                .x(100)  移动的是坐标。
                .translationX(100)
                .setDuration(1000)
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override public void onAnimationStart(View view) {

                    }

                    @Override public void onAnimationEnd(View view) {
                        ToastUtils.showShort("finish！");
                    }

                    @Override public void onAnimationCancel(View view) {

                    }
                })
                .start();

    }
}
