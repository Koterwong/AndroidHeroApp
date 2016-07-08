package com.koterwong.androidhero.chapter_09.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.koterwong.androidhero.MyApp;
import com.koterwong.androidhero.R;
import com.koterwong.androidhero.chapter_09.bean.AMProcessInfo;

import java.util.List;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/04 21:00
 * <p>
 * Description:
 * =================================================
 */
public class AMProcessAdapter extends BaseAdapter {

    private List<AMProcessInfo> mListData = null;
    private LayoutInflater mInfater;

    public AMProcessAdapter( List<AMProcessInfo> listData) {
        mInfater = LayoutInflater.from(MyApp.getApp());
        mListData = listData;
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInfater.inflate(R.layout.item_am_process_info, null);
            holder = new ViewHolder();
            holder.tvPID = (TextView) convertView.findViewById(R.id.tv_pid);
            holder.tvUID = (TextView) convertView.findViewById(R.id.tv_uid);
            holder.tvMemorySize = (TextView) convertView.findViewById(R.id.tv_size);
            holder.tvProcessName = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AMProcessInfo processInfo = (AMProcessInfo) getItem(position);
        holder.tvPID.setText("Pid:" + processInfo.getPid());
        holder.tvUID.setText("Uid:" + processInfo.getUid());
        holder.tvMemorySize.setText(processInfo.getMemorySize() + "KB");
        holder.tvProcessName.setText(processInfo.getProcessName());

        return convertView;
    }

    class ViewHolder {
        TextView tvPID;
        TextView tvUID;
        TextView tvMemorySize;
        TextView tvProcessName;
    }
}
