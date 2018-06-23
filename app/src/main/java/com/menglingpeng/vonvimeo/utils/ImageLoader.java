package com.menglingpeng.vonvimeo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class ImageLoader {

    public static <T> void load(T t, String url, ImageView imageView, Boolean isContext) {
        RequestBuilder<Bitmap> requestBuilder = null;
        RequestOptions requestOptions = new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy
                .AUTOMATIC);
        if (isContext) {
            Context context = (Context) t;
            requestBuilder = Glide.with(context).asBitmap();
        } else {
            Fragment fragment = (Fragment) t;
            requestBuilder = Glide.with(fragment).asBitmap();
        }
        requestBuilder.apply(requestOptions);
        requestBuilder.load(url).apply(requestOptions).into(imageView);
    }

}
