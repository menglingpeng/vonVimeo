package com.menglingpeng.vonvimeo.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.menglingpeng.designersshow.R;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;
import java.net.URL;

public class ShareAndOpenInBrowserUtil {

    private static final int THUMB_SIZE = 150; //设置分享到朋友圈的缩略图宽高大小
    private static final String APP_ID = "wx17fa470c77da3077";

    public static void share(Context context, String shareText) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, shareText);
        context.startActivity(Intent.createChooser(intent, context.getResources().getString(R.string
                .detail_toolbar_overflow_menu_share_create_chooser_title)));
    }

    public static void openInBrowser(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(url);
        intent.setData(uri);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(Intent.createChooser(intent, context.getResources().getString(R.string
                    .detail_toolbar_overflow_menu_open_in_browser_create_chooser_title)));
        }
    }

    public static void shareToWeiChat(Context context, final String shareUrl, String imageUrl, final String sharTitle,
                                      final String shareDescription){
        final IWXAPI api;
        api = WXAPIFactory.createWXAPI(context, APP_ID, true);//创建一个实例
        api.registerApp(APP_ID);//注册实例
        final String url = imageUrl;
        String webpageUrl = shareUrl;
        String title = sharTitle;
        String description = shareDescription;
        new Thread(new Runnable() {//创建一个子线程
            @Override
            public void run() {
                try{
                    //远程获取的缩略图的图片地址


                    WXWebpageObject webpage = new WXWebpageObject();
                    webpage.webpageUrl = shareUrl;//分享出去的网页地址
                    WXMediaMessage msg = new WXMediaMessage(webpage);
                    msg.title = sharTitle;//分享的标题
                    msg.description = shareDescription;//分享的描述信息
                    //获取网络图片资源
                    Bitmap bmp = BitmapFactory.decodeStream(new URL(url).openStream());
                    //创建缩略图
                    Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
                    bmp.recycle();
                    msg.thumbData = bmpToByteArray(thumbBmp, true);

                    SendMessageToWX.Req req = new SendMessageToWX.Req();
                    req.transaction = String.valueOf(System.currentTimeMillis());
                    req.message = msg;

                    req.scene = SendMessageToWX.Req.WXSceneSession;//分享到微信好友
                    //req.scene = SendMessageToWX.Req.WXSceneTimeline;//分享到微信朋友圈
                    api.sendReq(req);
                }catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }).start();

}


    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

}
