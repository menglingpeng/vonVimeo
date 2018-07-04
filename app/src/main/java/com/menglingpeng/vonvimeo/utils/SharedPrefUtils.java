package com.menglingpeng.vonvimeo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.menglingpeng.vonvimeo.base.BaseApplication;

import java.util.HashMap;

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

    /**
     * 保存网络请求的各种参数。
     *
     * @param map 网络请求的各种参数。
     * @return
     */
    public static boolean saveParameters(HashMap<String, String> map) {
        for (String key : map.keySet()) {
            editor.putString(key, map.get(key));
        }
        return editor.commit();
    }

    /**
     *
     * 获取保存的授权token。
     *
     * @return
     */
    public static String getAuthToken() {
        String accessToken = sp.getString(Constants.ACCESS_TOKEN, null);
        return accessToken;
    }

    /**
     *
     * 退出登录时，删除保存到本地的授权token。
     *
     * @return
     */
    public static void deleteAuthToken() {
        editor.putString(Constants.ACCESS_TOKEN, Constants.APP_ACCESS_TOKEN);
        editor.commit();
    }

}
