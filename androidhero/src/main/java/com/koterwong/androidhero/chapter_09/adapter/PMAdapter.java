package com.koterwong.androidhero.chapter_09.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koterwong.androidhero.MyApp;
import com.koterwong.androidhero.R;
import com.koterwong.androidhero.chapter_09.bean.PMAppInfo;

import java.util.List;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/04 20:01
 * <p/>
 * Description:
 * =================================================
 */


public class PMAdapter extends BaseAdapter {

    private List<PMAppInfo> mAppInfoList = null;
    private LayoutInflater mInflater = null;

    public PMAdapter( List<PMAppInfo> appInfoList) {
        mInflater = LayoutInflater.from(MyApp.getApp());
        mAppInfoList = appInfoList;
    }

    @Override
    public int getCount() {
        return mAppInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAppInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_pm_system_info, null);
            holder.appIcon = (ImageView) convertView.findViewById(R.id.imageView_icon);
            holder.appLabel = (TextView) convertView.findViewById(R.id.textView_label);
            holder.appPackage = (TextView) convertView.findViewById(R.id.textView_package);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PMAppInfo appInfo = (PMAppInfo) getItem(position);
        holder.appIcon.setImageDrawable(appInfo.getAppIcon());
        holder.appLabel.setText(appInfo.getAppLabel());
        holder.appPackage.setText(appInfo.getPkgName());
        return convertView;
    }

    class ViewHolder {
        ImageView appIcon;
        TextView appLabel;
        TextView appPackage;
    }
}
