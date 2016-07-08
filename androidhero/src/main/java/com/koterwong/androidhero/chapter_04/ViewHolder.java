package com.koterwong.androidhero.chapter_04;

import android.util.SparseArray;
import android.view.View;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/02 07:14
 * <p>
 * Description:
 * =================================================
 */
public class ViewHolder  {

    private View convertView;
    private SparseArray<View> mViews;

    public ViewHolder(View itemView) {
        this.convertView =  itemView;
        mViews = new SparseArray<>();
    }

    public <T extends View>  T findView(int id){
        View view  = mViews.get(id);
        if (view==null){
            view = convertView.findViewById(id);
            mViews.put(id,view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return convertView;
    }
}
