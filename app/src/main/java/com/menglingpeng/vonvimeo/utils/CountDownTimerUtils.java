package com.menglingpeng.vonvimeo.utils;

import android.app.Activity;
import android.os.CountDownTimer;
import android.widget.TextView;

public class CountDownTimerUtils {

    public static  CountDownTimer timer;

    public static void setCountDownTimer(final TextView textView){

        timer = new CountDownTimer(30 * 60 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                textView.setText(formatTime(l));
            }

            @Override
            public void onFinish() {
                textView.setText("00:00");

            }
        };

    }

    /**
     * 将毫秒转化为 分钟：秒 的格式
     *
     * @param millisecond 毫秒
     * @return
     */
    public static String formatTime(long millisecond) {
        int minute;//分钟
        int second;//秒数
        minute = (int) ((millisecond / 1000) / 60);
        second = (int) ((millisecond / 1000) % 60);
        if (minute < 10) {
            if (second < 10) {
                return "0" + minute + ":" + "0" + second;
            } else {
                return "0" + minute + ":" + second;
            }
        }else {
            if (second < 10) {
                return minute + ":" + "0" + second;
            } else {
                return minute + ":" + second;
            }
        }

    }

    /**
     * 取消倒计时
     */
    public static void timerCancel() {
        timer.cancel();
    }

    /**
     * 开始倒计时
     */
    public static void timerStart() {
        timer.start();
    }
}
