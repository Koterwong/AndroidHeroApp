package com.koterwong.androidhero.chapter_04.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.koterwong.androidhero.R;
import com.koterwong.androidhero.chapter_04.FlexibleListView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/01 21:43
 * <p/>
 * Description:弹性滚动的ListView
 * =================================================
 */
public class FlexibleFragment extends Fragment {

    @Bind(R.id.flexible_list_view) FlexibleListView mFlexibleLv;
    private String[] data = new String[30];

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_flexible_list_view, container, false);
        ButterKnife.bind(this,inflate);
        return inflate;
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        for (int i = 0; i < 30; i++) {
            data[i] = "条目" + i;
        }
        mFlexibleLv.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data));
    }

    @Override public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
