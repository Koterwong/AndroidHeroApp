package com.koterwong.androidhero.chapter_12;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.koterwong.androidhero.BaseFragment;
import com.koterwong.androidhero.R;
import com.koterwong.androidhero.chapter_12.viewholder.ActivityAnimHolder;
import com.koterwong.androidhero.chapter_12.viewholder.BaseHolder;
import com.koterwong.androidhero.chapter_12.viewholder.MDAnimHolder;
import com.koterwong.androidhero.chapter_12.viewholder.NotificationHolder;
import com.koterwong.androidhero.chapter_12.viewholder.TintAndClipHolder;
import com.koterwong.androidhero.chapter_12.viewholder.PaletteHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/02 17:12
 * <p>
 * Description:
 * =================================================
 */
public class Chapter_12Fragment extends BaseFragment{

    public static final String ACTION = "com.koterwong.androidhero";
    public static final String NAME = "rgb";

    @Bind(R.id.chapter_12_tab)
    TabLayout mTabLayout;
    @Bind(R.id.chapter_12_view_pager)
    ViewPager mViewPager;

    private List<BaseHolder> mViews;
    private String[] mTitle = {"Palette", "tint和clip","转场动画","MD动画","Notification"};

    @Override protected int getLayoutID() {
        return R.layout.fragment_chapter_12;
    }

    @Override protected void initData() {
        getActivity().registerReceiver(myReceiver,intentFilter);
        mViews = new ArrayList<>();
        mViews.add(new PaletteHolder(getActivity()));
        mViews.add(new TintAndClipHolder(getActivity()));
        mViews.add(new ActivityAnimHolder(getActivity()));
        mViews.add(new MDAnimHolder(getActivity()));
        mViews.add(new NotificationHolder(getActivity()));
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(myAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private PagerAdapter myAdapter = new PagerAdapter() {

        @Override public int getCount() {
            return mViews.size();
        }

        @Override public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViews.get(position).getContentView());
            mViews.get(position).initData();
            return mViews.get(position).getContentView();
        }

        @Override public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @Override public CharSequence getPageTitle(int position) {
            return mTitle[position];
        }
    };

    private IntentFilter intentFilter = new IntentFilter(ACTION);

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override public void onReceive(Context context, Intent intent) {
            mTabLayout.setBackgroundColor(intent.getIntExtra(NAME,0xff000000));
        }
    };

    //不要忘记销毁是反注册广播。
    @Override public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(myReceiver);
    }
}
