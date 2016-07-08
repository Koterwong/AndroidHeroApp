package com.koterwong.androidhero.chapter_04.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.koterwong.androidhero.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/01 21:25
 * <p/>
 * Description:
 * =================================================
 */
public class Chapter_04Fragment extends Fragment {

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_chapter_04, container, false);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @OnClick(R.id.btn_flexible_list_view) void toFlexible() {
        clickedOn(new FlexibleFragment());
    }

    @OnClick(R.id.btn_hide_list_view) void toHideListView() {
        clickedOn(new ScrollHideFragment());
    }

    @OnClick(R.id.btn_chat_list_view) void toChatListView() {
        clickedOn(new ChatItemFragment());
    }

    @OnClick(R.id.btn_focus_list_view) void toFocusListView() {
        clickedOn(new FocusFragment());
    }

    private void clickedOn(@NonNull Fragment fragment) {
        final String tag = fragment.getClass().toString();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(android.R.id.content, fragment, tag)
                .commit();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
