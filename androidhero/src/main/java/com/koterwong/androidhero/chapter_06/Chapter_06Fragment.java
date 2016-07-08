package com.koterwong.androidhero.chapter_06;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.koterwong.androidhero.BaseFragment;
import com.koterwong.androidhero.MyApp;
import com.koterwong.androidhero.R;
import com.koterwong.androidhero.chapter_06.surfaceview.SurfaceHolder;
import com.koterwong.androidhero.chapter_06.viewholder.BaseHolder;
import com.koterwong.androidhero.chapter_06.viewholder.CanvasHolder;
import com.koterwong.androidhero.chapter_06.viewholder.ClockHolder;
import com.koterwong.androidhero.chapter_06.viewholder.LayerHolder;
import com.koterwong.androidhero.chapter_06.viewholder.XfermodeHolder;

import butterknife.Bind;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/02 17:12
 * <p>
 * Description:
 * =================================================
 */
public class Chapter_06Fragment extends BaseFragment {

    @Bind(R.id.chapter_06_fl_content)
    FrameLayout mContentFl;
    @Bind(R.id.chapter_06_radio)
    RadioGroup mRadioG;

    private static SparseArray<BaseHolder> mHolders;

    static {
        mHolders = new SparseArray<>();
        mHolders.put(R.id.rb_canvas,new CanvasHolder(MyApp.getApp()));
        mHolders.put(R.id.rb_clock, new ClockHolder(MyApp.getApp()));
        mHolders.put(R.id.rb_layer, new LayerHolder(MyApp.getApp()));
        mHolders.put(R.id.rb_xfermode, new XfermodeHolder(MyApp.getApp()));
        mHolders.put(R.id.rb_surface_view, new SurfaceHolder(MyApp.getApp()));
    }

    @Override protected int getLayoutID() {
        return R.layout.fragment_chapter_06;
    }

    @Override protected void initData() {
        mRadioG.check(R.id.rb_canvas);
        controlShowView(R.id.rb_canvas);
        mRadioG.setOnCheckedChangeListener(getOnCheckedChangeListener());
    }

    private RadioGroup.OnCheckedChangeListener getOnCheckedChangeListener() {
        return new RadioGroup.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlShowView(checkedId);
            }
        };
    }

    private void controlShowView(int checkedId) {
        mContentFl.removeAllViews();
        BaseHolder holder = mHolders.get(checkedId);
        View contentView = holder.getContentView();
        //mContentLl个contentView.getParent并不是一个对象。
        if (contentView.getParent() instanceof ViewGroup){
            ((ViewGroup) contentView.getParent()).removeAllViews();
        }
        mContentFl.addView(contentView);
        holder.initData();
    }
}
