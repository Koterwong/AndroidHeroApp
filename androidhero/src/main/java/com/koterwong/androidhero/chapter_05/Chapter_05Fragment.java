package com.koterwong.androidhero.chapter_05;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.koterwong.androidhero.BaseFragment;
import com.koterwong.androidhero.MyApp;
import com.koterwong.androidhero.R;
import com.koterwong.androidhero.chapter_05.viewholder.BaseHolder;
import com.koterwong.androidhero.chapter_05.viewholder.DragViewByScroller;
import com.koterwong.androidhero.chapter_05.viewholder.DragViewUseLayout;
import com.koterwong.androidhero.chapter_05.viewholder.SlidViewQQ;

import butterknife.Bind;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/02 10:13
 * <p>
 * Description:
 * =================================================
 */
public class Chapter_05Fragment extends BaseFragment {

    @Bind(R.id.chapter_05_fl_content) FrameLayout mContentLl;
    @Bind(R.id.chapter_05_radio) RadioGroup mRadioG;

    private static SparseArray<BaseHolder>  mHolders;

    static {
        mHolders = new SparseArray<>();
        mHolders.put(R.id.rb_drag_layout,new DragViewUseLayout(MyApp.getApp()));
        mHolders.put(R.id.rb_drag_scroller,new DragViewByScroller(MyApp.getApp()));
        mHolders.put(R.id.rb_slid_qq,new SlidViewQQ(MyApp.getApp()));
    }

    @Override protected int getLayoutID() {
        return R.layout.fragment_chapter_05;
    }

    @Override protected void initData() {
        mRadioG.check(R.id.rb_drag_layout);
        controlShowView(R.id.rb_drag_layout);
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
        mContentLl.removeAllViews();
        BaseHolder holder = mHolders.get(checkedId);
        View contentView = holder.getContentView();
        //mContentLl个contentView.getParent并不是一个对象。
        if (contentView.getParent() instanceof ViewGroup){
            ((ViewGroup) contentView.getParent()).removeAllViews();
        }
        mContentLl.addView(contentView);
        holder.initData();
    }
}

