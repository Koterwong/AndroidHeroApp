package com.koterwong.androidhero.chapter_12.viewholder;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Outline;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.TextView;

import com.koterwong.androidhero.R;

import butterknife.Bind;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/06 21:27
 * <p/>
 * Description:
 * =================================================
 */
public class TintAndClipHolder extends BaseHolder {

    @Bind(R.id.tv_rect)
    TextView tv_rect;
    @Bind(R.id.tv_circle)
    TextView tv_circle;

    public TintAndClipHolder(Context context) {
        super(context);
    }

    @Override protected int getLayoutId() {
        return R.layout.view_tint_clipp;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override public void initData() {
        ViewOutlineProvider viewOutlineProvider0 = new ViewOutlineProvider() {
            @Override public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0,0,view.getWidth(),view.getHeight(),10);
            }
        };
        ViewOutlineProvider viewOutlineProvider1 = new ViewOutlineProvider() {
            @Override public void getOutline(View view, Outline outline) {
                outline.setOval(0,0,view.getWidth(),view.getHeight());
            }
        };
        tv_rect.setOutlineProvider(viewOutlineProvider0);
        tv_circle.setOutlineProvider(viewOutlineProvider1);
    }
}
