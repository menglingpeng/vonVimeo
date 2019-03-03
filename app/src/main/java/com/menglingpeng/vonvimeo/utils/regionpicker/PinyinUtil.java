package com.menglingpeng.vonvimeo.utils.regionpicker;

public class PinyinUtil {

    public static String getPingYin(String inputString) {
        try {
            return Pinyin.toPinyin(inputString, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
