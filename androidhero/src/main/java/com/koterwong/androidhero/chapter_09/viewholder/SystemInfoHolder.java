package com.koterwong.androidhero.chapter_09.viewholder;


import android.content.Context;
import android.widget.TextView;

import com.koterwong.androidhero.R;
import com.koterwong.androidhero.chapter_09.SystemInfoTools;

import butterknife.Bind;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/04 19:15
 * <p/>
 * Description:
 * =================================================
 */
public class SystemInfoHolder extends BaseHolder {


    @Bind(R.id.system_info_tv)
    TextView minfoTv;

    public SystemInfoHolder(Context context) {
        super(context);
    }

    @Override protected int getLayoutId() {
        return R.layout.view_system_info;
    }

    @Override public void initData() {
        minfoTv.setTextColor(0x88000000);
        String systemInfoStr = "===========BuildInfo==============\r\n"
                + SystemInfoTools.getBuildInfo();
        systemInfoStr += "===========SystemPropertyInfo=============\r\n";
        systemInfoStr += SystemInfoTools.getSystemPropertyInfo();
        minfoTv.setText(systemInfoStr);
    }
}
