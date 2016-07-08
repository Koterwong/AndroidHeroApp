package com.koterwong.androidhero.chapter_09.bean;

import android.graphics.drawable.Drawable;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/04 19:42
 * <p/>
 * Description:
 * =================================================
 */
public class PMAppInfo {
    private String appLabel;
    private Drawable appIcon;
    private String pkgName;

    public PMAppInfo() {
    }

    public String getAppLabel() {
        return appLabel;
    }

    public void setAppLabel(String appName) {
        this.appLabel = appName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }
}
