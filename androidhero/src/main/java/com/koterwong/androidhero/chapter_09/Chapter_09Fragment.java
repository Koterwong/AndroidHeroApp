package com.koterwong.androidhero.chapter_09;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.koterwong.androidhero.BaseFragment;
import com.koterwong.androidhero.MyApp;
import com.koterwong.androidhero.R;
import com.koterwong.androidhero.chapter_09.viewholder.AMProcessHolder;
import com.koterwong.androidhero.chapter_09.viewholder.BaseHolder;
import com.koterwong.androidhero.chapter_09.viewholder.PMAppInfoHolder;
import com.koterwong.androidhero.chapter_09.viewholder.SystemInfoHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/02 17:12
 * <p/>
 * Description:
 * =================================================
 */
public class Chapter_09Fragment extends BaseFragment {

    @Bind(R.id.chapter_09_tab)
    TabLayout mTabLayout;
    @Bind(R.id.chapter_09_view_pager)
    ViewPager mViewPager;

    private List<BaseHolder> mViews;
    private String[] mTitle = {"systemInfo", "PMAppInfo", "AmProcessInfo"};

    @Override protected int getLayoutID() {
        return R.layout.fragment_chapter_09;
    }

    @Override protected void initData() {
        mViews = new ArrayList<>();
        mViews.add(new SystemInfoHolder(MyApp.getApp()));
        mViews.add(new PMAppInfoHolder(MyApp.getApp()));
        mViews.add(new AMProcessHolder(MyApp.getApp()));
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
}
