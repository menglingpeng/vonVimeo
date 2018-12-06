package com.menglingpeng.vonvimeo.utils.alipay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class AliayOrderInfoUtil {


    /**
     * 构造支付订单参数列表
     */
    public static Map<String, String> buildOrderParamMap(String app_id, boolean rsa2, String price) {
        Map<String, String> keyValues = new HashMap<String, String>();
        keyValues.put("app_id", app_id);
        keyValues.put("biz_content", bizCotent(price));
        keyValues.put("charset", "utf-8");
        keyValues.put("method", "alipay.trade.app.pay");
        keyValues.put("sign_type", rsa2 ? "RSA2" : "RSA");
        keyValues.put("timestamp", "2016-07-29 16:55:53");
        keyValues.put("version", "1.0");
        return keyValues;
    }

    /**
     * 要求外部订单号必须唯一。
     */
    public static String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

}
