package com.koterwong.androidhero.chapter_07.viewholder;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.koterwong.androidhero.R;
import com.koterwong.androidhero.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/03 18:10
 * <p/>
 * Description:
 * =================================================
 */
public class WeiXingHolder extends BaseHolder {

    @Bind(R.id.imageView_b)
    ImageView imageViewB;
    @Bind(R.id.imageView_c)
    ImageView imageViewC;
    @Bind(R.id.imageView_d)
    ImageView imageViewD;
    @Bind(R.id.imageView_e)
    ImageView imageViewE;
    @Bind(R.id.imageView_a)
    ImageView imageViewA;

    private boolean mFlag = true;

    public WeiXingHolder(Context context) {
        super(context);
    }

    @Override protected int getLayoutId() {
        return R.layout.anim_weixing;
    }

    @Override public void initData() {

    }

    @OnClick( {R.id.imageView_b, R.id.imageView_c, R.id.imageView_d, R.id.imageView_e, R.id.imageView_a}) public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView_b:
                ToastUtils.showShort("b");
                break;
            case R.id.imageView_c:
                ToastUtils.showShort("c");
                break;
            case R.id.imageView_d:
                ToastUtils.showShort("d");
                break;
            case R.id.imageView_e:
                ToastUtils.showShort("e");
                break;
            case R.id.imageView_a:
                if (mFlag) {
                    startAnim();
                } else {
                    closeAnim();
                }
                break;
        }
    }

    private void closeAnim() {
        ObjectAnimator animator0 = ObjectAnimator.ofFloat(imageViewA, "alpha", 0.5f, 1f);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageViewB, "translationX", 0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageViewC, "translationY", 0f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageViewD, "translationX", 0f);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(imageViewE, "translationY", 0f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(animator0, animator1, animator2, animator3, animator4);
        set.start();
        mFlag = true;
    }

    private void startAnim() {
        ObjectAnimator animator0 = ObjectAnimator.ofFloat(imageViewA, "alpha", 1f, 0.5f);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageViewB, "translationX", -200f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageViewC, "translationY", -200f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageViewD, "translationX", 200f);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(imageViewE, "translationY", 200f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.setInterpolator(new OvershootInterpolator());
        set.playTogether(animator0, animator1, animator2, animator3, animator4);
        set.start();
        mFlag = false;
    }
}
