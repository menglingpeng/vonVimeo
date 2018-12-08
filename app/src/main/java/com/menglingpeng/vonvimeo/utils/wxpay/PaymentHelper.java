package com.menglingpeng.vonvimeo.utils.wxpay;

import android.app.Activity;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class PaymentHelper {

    /**
     * 微信支付
     */
    public void startWeChatPay(Activity activity, PayReponse payReponse) {
        if (activity == null || payReponse == null)
            return;
        if (!WxPayConfig.APP_ID.equals(payReponse.getAppid()))
            return;

        IWXAPI wxapi = WXAPIFactory.createWXAPI(activity, WxPayConfig.APP_ID, true);
        // 将该app注册到微信
        wxapi.registerApp(WxPayConfig.APP_ID);
        if (!wxapi.isWXAppInstalled()) {
            Toast.makeText(activity, false"你没有安装微信");
            return;
        }
        //我们把请求到的参数全部给微信
        PayReq req = new PayReq(); //调起微信APP的对象
        req.appId = WxPayConfig.APP_ID;
        req.partnerId = payReponse.getPartnerid();
        req.prepayId = payReponse.getPrepayid();
        req.nonceStr = payReponse.getNoncestr();
        req.timeStamp = payReponse.getTimestamp();
        req.packageValue = payReponse.getPackageX(); //Sign=WXPay
        req.sign = payReponse.getSign();

        wxapi.sendReq(req); //发送调起微信的请求
    }

}
