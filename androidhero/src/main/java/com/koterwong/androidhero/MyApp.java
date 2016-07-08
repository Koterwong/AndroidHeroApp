package com.koterwong.androidhero;

import android.content.Context;

import com.koterwong.androidhero.game2048.config.Config;
import com.koterwong.androidhero.utils.ToastUtils;

/**
 * ================================================
 * Created By：Koterwong; Time: 2016/06/01 11:29
 * <p/>
 * Description:
 * =================================================
 */
public class MyApp extends Config {

    private static Context mApp;

    @Override public void onCreate() {
        super.onCreate();
        mApp = this;
        ToastUtils.register(this);
    }

    public static Context getApp() {
        return mApp;
    }
}
