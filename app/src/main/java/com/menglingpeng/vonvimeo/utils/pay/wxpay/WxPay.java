package com.menglingpeng.vonvimeo.utils.pay.wxpay;

import android.app.Activity;
import android.content.Context;

import com.menglingpeng.vonvimeo.utils.pay.Pay;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WxPay {

    private static WxPay wxPay;
    private Context mContext;
    private IWXAPI mIWXAPI;
    private Pay.PayListener payListener;

    //未安装微信或微信版本过低
    public static final int WEIXIN_VERSION_LOW = 0x001;
    //支付参数异常
    public static final int PAY_PARAMETERS_ERROE = 0x002;
    //支付失败
    public static final int PAY_ERROR= 0x003;

    private WxPay(Activity context) {
        mContext = context;
    }

    public static WxPay getInstance(Activity context){
        if (wxPay == null) {
            synchronized(WxPay.class){
                if (wxPay == null) {
                    wxPay = new WxPay(context);
                }
            }
        }
        return wxPay;
    }

    /**
     * 初始化微信支付接口
     * @param appId
     */
    public void init(String appId){
        mIWXAPI= WXAPIFactory.createWXAPI(mContext, null);
        mIWXAPI.registerApp(appId);
    }

    /**
     * 获取微信接口
     * @return
     */
    public IWXAPI getWXApi() {
        return mIWXAPI;
    }

    /**
     * 调起支付
     * @param appId
     * @param partnerId
     * @param prepayId
     * @param nonceStr
     * @param timeStamp
     * @param sign
     */
    public void startWXPay(String appId,String partnerId,String prepayId,
                           String nonceStr,String timeStamp,String sign,Pay.PayListener listener){
        payListener = listener;
        init(appId);

        if (!checkWx()) {
            if(listener != null) {
                listener.onPayError(WEIXIN_VERSION_LOW,"未安装微信或者微信版本过低");
            }
            return;
        }



        PayReq request = new PayReq();
        request.appId = appId;
        request.partnerId = partnerId;
        request.prepayId= prepayId;
        request.packageValue = "Sign=WXPay";
        request.nonceStr=nonceStr;
        request.timeStamp= timeStamp;
        request.sign= sign;
        mIWXAPI.sendReq(request);
    }
}
