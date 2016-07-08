package com.koterwong.androidhero.chapter_04.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.koterwong.androidhero.R;
import com.koterwong.androidhero.chapter_04.FocusListViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/02 08:41
 * <p/>
 * Description: 动态改变的ListView
 * =================================================
 */
public class FocusFragment extends Fragment {

    @Bind(R.id.focus_list_view) ListView listView;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_focus_list_view, container, false);
        ButterKnife.bind(this,inflate);
        return inflate;
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<String> data = new ArrayList<String>();
        data.add("I am item 1");
        data.add("I am item 2");
        data.add("I am item 3");
        data.add("I am item 4");
        data.add("I am item 5");
        data.add("I am item 6");
        data.add("I am item 7");
        data.add("I am item 8");
        final FocusListViewAdapter adapter = new FocusListViewAdapter(getActivity(), data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                adapter.setCurrentItem(position);
            }
        });
    }

    @Override public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
