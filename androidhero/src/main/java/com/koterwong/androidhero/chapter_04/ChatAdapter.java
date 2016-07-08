package com.koterwong.androidhero.chapter_04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koterwong.androidhero.R;

import java.util.List;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/02 07:11
 * <p>
 * Description:
 * =================================================
 */
public class ChatAdapter extends BaseAdapter {

    private List<ChatBean> mDatas;
    private Context mContext;

    public ChatAdapter(List<ChatBean> mDatas, Context context) {
        this.mDatas = mDatas;
        this.mContext = context;
    }

    @Override public int getCount() {
        return mDatas.size();
    }

    @Override public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public int getItemViewType(int position) {
        if (mDatas.get(position).getType() == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override public int getViewTypeCount() {
        return 2;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            if (getItemViewType(position)==0) {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_chat_out, parent, false);
                    holder = new ViewHolder(convertView);
                    convertView.setTag(holder);

            }else{
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_chat_in,parent,false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }
        }else{
            holder  = (ViewHolder) convertView.getTag();
        }
        if (getItemViewType(position)==0){
            TextView mCartOut = holder.findView(R.id.chat_text_out);
            ImageView mIcon = holder.findView(R.id.chat_icon);
            mCartOut.setText(mDatas.get(position).getChatText());
            mIcon.setImageBitmap(mDatas.get(position).getUseShort());
        }else{
            TextView mCartIn = holder.findView(R.id.chat_in_text);
            ImageView mIcon = holder.findView(R.id.chat_in_icon);
            mCartIn.setText(mDatas.get(position).getChatText());
            mIcon.setImageBitmap(mDatas.get(position).getUseShort());
        }
        return convertView;
    }
}
