package com.koterwong.androidhero.chapter_04.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.koterwong.androidhero.R;
import com.koterwong.androidhero.chapter_04.ChatAdapter;
import com.koterwong.androidhero.chapter_04.ChatBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/02 07:04
 * <p/>
 * Description:
 * =================================================
 */
public class ChatItemFragment extends Fragment {

    @Bind(R.id.chat_list_view)
    ListView mListView;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_chat_list_view, container, false);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<ChatBean> mDatas = new ArrayList<>();
        mDatas.add(new ChatBean(0, "hello ! What`s up", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
        mDatas.add(new ChatBean(1, "Hi ! What`s up", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
        mDatas.add(new ChatBean(0, "hello ! What`s up", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
        mDatas.add(new ChatBean(1, "Hi ! What`s up", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
        mDatas.add(new ChatBean(0, "hello ! What`s up", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
        mDatas.add(new ChatBean(1, "Hi ! What`s up", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
        mDatas.add(new ChatBean(0, "hello ! What`s up", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
        mDatas.add(new ChatBean(1, "Hi ! What`s up", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
        mDatas.add(new ChatBean(0, "hello ! What`s up", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
        mDatas.add(new ChatBean(1, "Hi ! What`s up", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
        mDatas.add(new ChatBean(0, "hello ! What`s up", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
        mListView.setAdapter(new ChatAdapter(mDatas, getActivity()));
    }

    @Override public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
