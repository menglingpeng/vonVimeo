package com.menglingpeng.vonvimeo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.menglingpeng.vonvimeo.base.BaseApplication;

public class SharedPrefUtils {

    private static Context context = BaseApplication.getContext();
    private static SharedPreferences sp = context.getSharedPreferences("ShotsJson", Context.MODE_PRIVATE);
    private static SharedPreferences.Editor editor = sp.edit();


    /**
     * 首次启动应用后，保存标志位。
     * 保存用户登陆状态。
     */
    public static boolean saveState(String key, Boolean is) {
        editor.putBoolean(key, is);
        return editor.commit();
    }

    /**
     * 判断应用是否第一次启动。
     * 获取用户登陆状态。
     */
    public static boolean getState(String key) {
        Boolean is = false;
        if (key.equals(Constants.IS_FIRST_START)) {
            //is不存在则是第一次启动，值为ture
            is = sp.getBoolean(Constants.IS_FIRST_START, true);
        } else {
            //is不存在则是没有登陆，值为false
            is = sp.getBoolean(key, false);
        }
        return is;
    }
}
