package com.menglingpeng.vonvimeo.utils.pay;

import android.app.Activity;

/**
 * 支付步骤说明：
 * 步骤1：从网络开始,获取交易流水号即TN
 * 步骤2：通过银联工具类启动支付插件
 * 步骤3：处理银联手机支付控件返回的支付结果
 */

public class Pay {

    private static Pay pay;
    private Activity mContext;

    private Pay(Activity context) {
        mContext = context;
    }

    public static Pay getIntance(Activity context) {
        if (pay == null) {
            synchronized (Pay.class) {
                if (pay == null) {
                    pay = new Pay(context);
                }
            }
        }
        return pay;
    }

    public interface PayListener {
        //支付成功
        void onPaySuccess();
        //支付失败
        void onPayError(int error_code, String message);
        //支付取消
        void onPayCancel();
        //银联支付结果回调
        void onUUPay(String dataOrg, String sign, String mode);
    }

    public enum PayMode {
        WXPAY, ALIPAY, UUPAY
    }

}
