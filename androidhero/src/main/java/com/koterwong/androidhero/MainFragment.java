package com.koterwong.androidhero;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.koterwong.androidhero.xpuzzle.activity.XPuzzleMainActivity;
import com.koterwong.androidhero.chapter_03.fragment.Chapter_03Fragment;
import com.koterwong.androidhero.chapter_04.fragment.Chapter_04Fragment;
import com.koterwong.androidhero.chapter_05.Chapter_05Fragment;
import com.koterwong.androidhero.chapter_06.Chapter_06Fragment;
import com.koterwong.androidhero.chapter_07.Chapter_07Fragment;
import com.koterwong.androidhero.chapter_08.Chapter_08Fragment;
import com.koterwong.androidhero.chapter_09.Chapter_09Fragment;
import com.koterwong.androidhero.chapter_10.Chapter_10Fragment;
import com.koterwong.androidhero.chapter_11.Chapter_11Fragment;
import com.koterwong.androidhero.chapter_12.Chapter_12Fragment;
import com.koterwong.androidhero.chapter_13.Chapter_13Fragment;
import com.koterwong.androidhero.game2048.activity.Game2048;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ================================================
 * Created By：Koterwong ;Time: 2016/05/31 16:44
 * Description:
 * =================================================
 */
public class MainFragment extends Fragment {


    @Nullable @Override public View onCreateView(LayoutInflater inflater,
                                                 @Nullable ViewGroup container,
                                                 @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @OnClick(R.id.btn_demo_charpter) void charpter_03() {
        clickedOn(new Chapter_03Fragment());
    }

    @OnClick(R.id.btn_chapter_04) void chapter_04() {
        clickedOn(new Chapter_04Fragment());
    }

    @OnClick(R.id.btn_chapter_05) void chapter_05() {
        clickedOn(new Chapter_05Fragment());
    }

    @OnClick(R.id.btn_chapter_06) void chapter_06() {
        clickedOn(new Chapter_06Fragment());
    }

    @OnClick(R.id.btn_chapter_07) void chapter_07() {
        clickedOn(new Chapter_07Fragment());
    }

    @OnClick(R.id.btn_chapter_08) void chapter_08() {
        clickedOn(new Chapter_08Fragment());
    }

    @OnClick(R.id.btn_chapter_09) void chapter_09() {
        clickedOn(new Chapter_09Fragment());
    }

    @OnClick(R.id.btn_chapter_10) void chapter_10() {
        clickedOn(new Chapter_10Fragment());
    }

    @OnClick(R.id.btn_chapter_11) void chapter_11() {
        clickedOn(new Chapter_11Fragment());
    }

    @OnClick(R.id.btn_chapter_12) void chapter_12() {
        clickedOn(new Chapter_12Fragment());
    }

    @OnClick(R.id.btn_chapter_13) void chapter_13() {
        clickedOn(new Chapter_13Fragment());
    }

    @OnClick(R.id.btn_x_puzzle) void btn_x_puzzle() {
        clickToActivity(XPuzzleMainActivity.class);
    }

    @OnClick(R.id.btn_game_2048) void btn_game_2048() {
        clickToActivity(Game2048.class);
    }

    private void clickedOn(@NonNull Fragment fragment) {
        final String tag = fragment.getClass().toString();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(android.R.id.content, fragment, tag)
                .commit();
    }

    private void clickToActivity(Class<?> activityClass){
        getActivity().startActivity(new Intent(getContext(),activityClass));
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
