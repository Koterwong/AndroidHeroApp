package com.koterwong.androidhero.chapter_09.viewholder;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.ListView;

import com.koterwong.androidhero.MyApp;
import com.koterwong.androidhero.R;
import com.koterwong.androidhero.chapter_09.adapter.PMAdapter;
import com.koterwong.androidhero.chapter_09.bean.PMAppInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/04 19:45
 * <p/>
 * Description:
 * =================================================
 */
public class PMAppInfoHolder extends BaseHolder {

    public static final int ALL_APP = 0;
    public static final int SYSTEM_APP = 1;
    public static final int THIRD_APP = 2;
    public static final int SDCARD_APP = 3;

    private PackageManager pm;

    @Bind(R.id.chapter_09_lv_pm)
    ListView mPmListView;

    public PMAppInfoHolder(Context context) {
        super(context);
    }

    @Override protected int getLayoutId() {
        return R.layout.view_pm_info;
    }

    @Override public void initData() {

    }

    @OnClick( {R.id.btn_all_app, R.id.btn_system_app, R.id.btn_3rd_app, R.id.btn_sdcard_app})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_all_app:
                setListData(ALL_APP);
                break;
            case R.id.btn_system_app:
                setListData(SYSTEM_APP);
                break;
            case R.id.btn_3rd_app:
                setListData(THIRD_APP);
                break;
            case R.id.btn_sdcard_app:
                setListData(SDCARD_APP);
                break;
        }
    }
    private List<PMAppInfo> getAppInfo(int flag) {
        // 获取PackageManager对象
        pm = MyApp.getApp().getPackageManager();
        // 获取应用信息
        List<ApplicationInfo> listApplications = pm.getInstalledApplications(
                PackageManager.GET_UNINSTALLED_PACKAGES);
        List<PMAppInfo> appInfos = new ArrayList<>();
        // 判断应用类型
        switch (flag) {
            case ALL_APP:
                appInfos.clear();
                for (ApplicationInfo app : listApplications) {
                    appInfos.add(makeAppInfo(app));
                }
                break;
            case SYSTEM_APP:
                appInfos.clear();
                for (ApplicationInfo app : listApplications) {
                    if ((app.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                        appInfos.add(makeAppInfo(app));
                    }
                }
                break;
            case THIRD_APP:
                appInfos.clear();
                for (ApplicationInfo app : listApplications) {
                    if ((app.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                        appInfos.add(makeAppInfo(app));
                    } else if ((app.flags &
                            ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
                        appInfos.add(makeAppInfo(app));
                    }
                }
                break;
            case SDCARD_APP:
                appInfos.clear();
                for (ApplicationInfo app : listApplications) {
                    if ((app.flags &
                            ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {
                        appInfos.add(makeAppInfo(app));
                    }
                }
                break;
            default:
                return null;
        }
        return appInfos;
    }

    private PMAppInfo makeAppInfo(ApplicationInfo app) {
        PMAppInfo appInfo = new PMAppInfo();
        appInfo.setAppLabel((String) app.loadLabel(pm));
        appInfo.setAppIcon(app.loadIcon(pm));
        appInfo.setPkgName(app.packageName);
        return appInfo;
    }

    public void setListData(int flag) {
        PMAdapter adapter = new PMAdapter(getAppInfo(flag));
        mPmListView.setAdapter(adapter);
    }
}
