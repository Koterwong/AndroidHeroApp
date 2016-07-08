package com.koterwong.androidhero.chapter_07;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.koterwong.androidhero.BaseFragment;
import com.koterwong.androidhero.R;
import com.koterwong.androidhero.chapter_07.viewholder.BaseHolder;
import com.koterwong.androidhero.chapter_07.viewholder.DrapTestHolder;
import com.koterwong.androidhero.chapter_07.viewholder.PropertyHolder;
import com.koterwong.androidhero.chapter_07.viewholder.TweenHolder;
import com.koterwong.androidhero.chapter_07.viewholder.WeiXingHolder;

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
public class Chapter_07Fragment extends BaseFragment {

    @Bind(R.id.tab)
    TabLayout mTabLayout;
    @Bind(R.id.chapter_07_view_pager)
    ViewPager mViewPager;

    private List<BaseHolder> mViews;
    private String[] mTitle = {"tweenAnim","propertyAnim","卫星菜单","下拉布局"};

    @Override protected int getLayoutID() {
        return R.layout.fragment_chapter_07;
    }

    @Override protected void initData() {
        mViews = new ArrayList<>();
        mViews.add(new TweenHolder(getActivity()));
        mViews.add(new PropertyHolder(getActivity()));
        mViews.add(new WeiXingHolder(getActivity()));
        mViews.add(new DrapTestHolder(getActivity()));
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(myAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
//        mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.indigo));
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
