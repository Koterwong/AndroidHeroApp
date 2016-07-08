package com.koterwong.androidhero.chapter_07.viewholder;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.koterwong.androidhero.R;

import butterknife.OnClick;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/03 15:08
 * <p>
 * Description:
 * =================================================
 */
public class TweenHolder extends BaseHolder {

    public TweenHolder(Context context) {
        super(context);
    }

    @Override protected int getLayoutId() {
        return R.layout.anim_tween;
    }

    @Override public void initData() {

    }

    @OnClick( {R.id.alpha, R.id.rotate, R.id.rotate_self, R.id.translate, R.id.scale, R.id.scale_slef, R.id.anim_set})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.alpha:
                btnAlpha(view);
                break;
            case R.id.rotate:
                btnRotate(view);
                break;
            case R.id.rotate_self:
                btnRotateSelf(view);
                break;
            case R.id.translate:
                btnTranslate(view);
                break;
            case R.id.scale:
                btnScale(view);
                break;
            case R.id.scale_slef:
                btnScaleSelf(view);
                break;
            case R.id.anim_set:
                btnSet(view);
                break;
        }
    }


    public void btnAlpha(View view) {
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(1000);
        view.startAnimation(aa);
    }

    public void btnRotate(View view) {
        //旋转中心点。这里没有指定旋转中心。
        RotateAnimation ra = new RotateAnimation(0, 360);
        ra.setDuration(1000);
        ra.setRepeatCount(2);
        view.startAnimation(ra);
    }

    public void btnRotateSelf(View view) {
        RotateAnimation ra = new RotateAnimation(0, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5F,
                RotateAnimation.RELATIVE_TO_SELF, 0.5F);
        ra.setDuration(1000);
        view.startAnimation(ra);
    }

    public void btnTranslate(View view) {
        TranslateAnimation ta = new TranslateAnimation(0, 200, 0, 300);
        ta.setDuration(1000);
        view.startAnimation(ta);
    }

    public void btnScale(View view) {
        ScaleAnimation sa = new ScaleAnimation(0, 2, 0, 2);
        sa.setDuration(1000);
        view.startAnimation(sa);
    }

    public void btnScaleSelf(View view) {
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5F,
                Animation.RELATIVE_TO_SELF, 0.5F);
        sa.setDuration(1000);
        view.startAnimation(sa);
    }

    public void btnSet(View view) {
        AnimationSet as = new AnimationSet(true);
        as.setDuration(1000);

        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(1000);
        as.addAnimation(aa);

        TranslateAnimation ta = new TranslateAnimation(0, 100, 0, 200);
        ta.setDuration(1000);
        as.addAnimation(ta);

        view.startAnimation(as);
    }
}
