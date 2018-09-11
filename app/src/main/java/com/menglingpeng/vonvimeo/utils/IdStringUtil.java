package com.menglingpeng.vonvimeo.utils;

public class IdStringUtil {

    /**
     * 获取Vimeo返回的Json中的各种ID
     * @param uri
     * @return
     */
    public static String getId(String uri){
        String id;
        String spacer = "/";
        id = uri.substring(uri.lastIndexOf(spacer) + 1);
        return id;
    }
}
