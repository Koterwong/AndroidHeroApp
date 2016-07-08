package com.koterwong.androidhero.chapter_09.viewholder;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;
import android.widget.ListView;

import com.koterwong.androidhero.MyApp;
import com.koterwong.androidhero.R;
import com.koterwong.androidhero.chapter_09.adapter.AMProcessAdapter;
import com.koterwong.androidhero.chapter_09.bean.AMProcessInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/04 20:59
 * <p>
 * Description:
 * =================================================
 */
public class AMProcessHolder extends BaseHolder {

    @Bind(R.id.chapter_09_lv_am)
    ListView mAmListView;

    private List<AMProcessInfo> mAmProcessInfoList;
    private ActivityManager mActivityManager;

    public AMProcessHolder(Context context) {
        super(context);
    }

    @Override protected int getLayoutId() {
        return R.layout.view_am_info;
    }

    @Override public void initData() {
        mActivityManager = (ActivityManager) MyApp.getApp().getSystemService(Context.ACTIVITY_SERVICE);
        mAmListView.setAdapter(new AMProcessAdapter(getRunningProcessInfo()));
    }

    private List<AMProcessInfo> getRunningProcessInfo() {
        mAmProcessInfoList = new ArrayList<>();
        List<ActivityManager.RunningAppProcessInfo> appProcessList = mActivityManager.getRunningAppProcesses();
        for (int i = 0; i < appProcessList.size(); i++) {
            ActivityManager.RunningAppProcessInfo info = appProcessList.get(i);
            int pid = info.pid;
            int uid = info.uid;
            String processName = info.processName;
            int[] memoryPid = new int[]{pid};
            Debug.MemoryInfo[] memoryInfo = mActivityManager.getProcessMemoryInfo(memoryPid);
            int memorySize = memoryInfo[0].getTotalPss();

            AMProcessInfo processInfo = new AMProcessInfo();
            processInfo.setPid("" + pid);
            processInfo.setUid("" + uid);
            processInfo.setMemorySize("" + memorySize);
            processInfo.setProcessName(processName);
            mAmProcessInfoList.add(processInfo);
        }
        return mAmProcessInfoList;
    }
}
