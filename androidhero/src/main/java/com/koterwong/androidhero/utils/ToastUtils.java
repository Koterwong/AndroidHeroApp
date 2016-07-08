package com.koterwong.androidhero.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Author：Koterwong，Date：2016/5/20.
 * Description: ToastUtils
 */
public class ToastUtils {

    public static Context mContext;


    private ToastUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void register(Context context) {
        mContext = context.getApplicationContext();
    }


    private static void check() {
        if (mContext == null) {
            throw new NullPointerException(
                    "Must initial call ToastUtils.register(Context context) in your " +
                            "< ? extends Application class>");
        }
    }


    public static void showShort(int resId) {
        check();
        Toast.makeText(mContext, resId, Toast.LENGTH_SHORT).show();
    }


    public static void showShort(String message) {
        check();
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }


    public static void showLong(int resId) {
        check();
        Toast.makeText(mContext, resId, Toast.LENGTH_LONG).show();
    }


    public static void showLong(String message) {
        check();
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }


    public static void showLongX2(String message) {
        showLong(message);
        showLong(message);
    }


    public static void showLongX2(int resId) {
        showLong(resId);
        showLong(resId);
    }


    public static void showLongX3(int resId) {
        showLong(resId);
        showLong(resId);
        showLong(resId);
    }


    public static void showLongX3(String message) {
        showLong(message);
        showLong(message);
        showLong(message);
    }
}
