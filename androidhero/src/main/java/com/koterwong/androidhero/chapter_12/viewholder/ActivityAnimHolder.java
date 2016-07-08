package com.koterwong.androidhero.chapter_12.viewholder;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.koterwong.androidhero.R;
import com.koterwong.androidhero.chapter_12.AnimAActivity;

import butterknife.OnClick;

/**
 * Description:
 * <p/>
 * Created By：Koterwong; Time: 2016/07/07 18:58
 */
public class ActivityAnimHolder extends BaseHolder {

    public ActivityAnimHolder(Context context) {
        super(context);
    }

    @Override protected int getLayoutId() {
        return R.layout.view_activity_anim;
    }

    @Override public void initData() {

    }

    @OnClick(R.id.btn_activity_anim)
    public void toActivity(View view){
        mContext.startActivity(new Intent(mContext,AnimAActivity.class));
    }
}
