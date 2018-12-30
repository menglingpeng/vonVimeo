package com.menglingpeng.vonvimeo.utils.pay;

import android.app.Activity;
import android.text.TextUtils;

import com.menglingpeng.vonvimeo.utils.pay.alipay.Alipay;
import com.menglingpeng.vonvimeo.utils.pay.unionpay.UPPay;
import com.menglingpeng.vonvimeo.utils.pay.wxpay.WxPay;

import org.json.JSONException;
import org.json.JSONObject;

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

    public void toWxPay(String payParameters, PayListener listener) {
        if (payParameters != null) {
            JSONObject param = null;
            try {
                param = new JSONObject(payParameters);
            } catch (JSONException e) {
                e.printStackTrace();
                if (listener != null) {
                    listener.onPayError(WxPay.PAY_PARAMETERS_ERROE, "参数异常");
                }
                return;
            }
            if (TextUtils.isEmpty(param.optString("appId")) || TextUtils.isEmpty(param.optString("partnerId"))
                    || TextUtils.isEmpty(param.optString("prepayId")) || TextUtils.isEmpty(param.optString("nonceStr"))
                    || TextUtils.isEmpty(param.optString("timeStamp")) || TextUtils.isEmpty(param.optString("sign"))) {
                if (listener != null) {
                    listener.onPayError(WxPay.PAY_PARAMETERS_ERROE, "参数异常");
                }
                return;
            }
            toWxPay(param.optString("appId"),
                    param.optString("partnerId"), param.optString("prepayId"),
                    param.optString("nonceStr"), param.optString("timeStamp"),
                    param.optString("sign"), listener);

        } else {
            if (listener != null) {
                listener.onPayError(WxPay.PAY_PARAMETERS_ERROE, "参数异常");
            }
        }
    }

    public void toAliPay(String payParameters, PayListener listener) {
        if (payParameters != null) {
            if (listener != null) {
                Alipay.getInstance(mContext).startAliPay(payParameters, listener);
            }
        } else {
            if (listener != null) {
                listener.onPayError(Alipay.PAY_PARAMETERS_ERROE, "参数异常");
            }
        }
    }


    public void toUUPay(String payParameters, PayListener listener) {
        if (payParameters != null) {
            JSONObject param = null;
            try {
                param = new JSONObject(payParameters);
            } catch (JSONException e) {
                e.printStackTrace();
                if (listener != null) {
                    listener.onPayError(UPPay.PAY_PARAMETERS_ERROE, "参数异常");
                }
                return;
            }
            if (TextUtils.isEmpty(param.optString("mode")) || TextUtils.isEmpty(param.optString("tn"))){
                if (listener != null) {
                    listener.onPayError(UPPay.PAY_PARAMETERS_ERROE, "参数异常");
                }
                return;
            }
            toUUPay(param.optString("mode"),
                    param.optString("tn"), listener);
        } else {
            if (listener != null) {
                listener.onPayError(WxPay.PAY_PARAMETERS_ERROE, "参数异常");
            }
        }
    }

    public void toUUPay(String mode, String tn, PayListener listener) {
        if (listener == null) {
            listener.onPayError(UPPay.PAY_PARAMETERS_ERROE, "参数异常");
            return;
        }
        if (TextUtils.isEmpty(mode)) {
            mode = "00";
        }
        if (TextUtils.isEmpty(tn)) {
            listener.onPayError(UPPay.PAY_PARAMETERS_ERROE, "参数异常");
            return;
        }
        UPPay.getInstance(mContext).startUPPay(mode, tn, listener);
    }
}
