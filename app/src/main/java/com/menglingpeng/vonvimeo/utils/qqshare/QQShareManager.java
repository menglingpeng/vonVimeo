package com.menglingpeng.vonvimeo.utils.qqshare;

import android.os.Bundle;

import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.UiError;

import java.util.ArrayList;

public class QQShareManager {
    public static Bundle params;

    /**
     * 分享到QQ，默认图文
     *
     * @param imgUrl 图片url
     */
    public static void shareToQQ(String imgUrl) {
    params = new Bundle();
    params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE,QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
    params.putString(QQShare.SHARE_TO_QQ_TITLE, "标题");// 标题
    params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "要分享的摘要");// 摘要
    params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,"http://www.qq.com/news/1.html");// 内容地址
    // 网络图片地址　　params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "应用名称");// 应用名称
    params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,"http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");

    params.putString(QQShare.SHARE_TO_QQ_EXT_INT, "其它附加功能");

    }

    /**
     * 分享纯图片到QQ
     *
     * @param imgUrl 图片url
     */
    public static void shareImgToQQ(String imgUrl) {
        params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);// 设置分享类型为纯图片分享
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imgUrl);// 需要分享的本地图片URL
    }

    /**
     * 分享到QQ空间
     *
     * @param imgUrl 图片url
     */
    public static void shareToQzone(String imgUrl){
        params = new Bundle();
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE,QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "标题");// 标题
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "要分享的摘要");// 摘要
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL,"http://www.qq.com/news/1.html");// 内容地址
        ArrayList<String> imgUrlList = new ArrayList<>();
        imgUrlList.add(imgUrl);
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL,imgUrlList);// 图片地址

    }

    public interface shareListerer{

        public void onComplete(Object o);
        public void onError(UiError uiError);
        public void onCancel();
    }
}
