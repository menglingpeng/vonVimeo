package com.menglingpeng.vonvimeo.utils.alipay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.menglingpeng.vonvimeo.utils.alipay.AliayOrderInfoUtil;
import com.menglingpeng.vonvimeo.utils.alipay.AlipayConfig;

import java.util.Map;

public class PayHelper {

    public class PaymentHelper {
        private static final int SDK_PAY_FLAG = 1;
        private Activity mthis;
        private Map<String, String> result;

        /**
         * 支付宝支付
         */
        public void  startAliPay(Activity activity, PayReponse payReponse, String payRMB) {
            this.mthis = activity;
            if (activity == null || payReponse == null) {
                return;
            }
            if (TextUtils.isEmpty(AlipayConfig.PARTNER) || TextUtils.isEmpty(AlipayConfig.RSA2_PRIVATE) || TextUtils.isEmpty(AlipayConfig.SELLER)) {
                return;
            }

            /**
             * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
             * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
             * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
             * 点击支付按钮出现的错误码，请查看：https://tech.open.alipay.com/support/knowledge/index.htm?categoryId=24120&scrollcheck=1#/?_k=d783mj
             * orderInfo的获取必须来自服务端；
             */
            boolean rsa2 = (AlipayConfig.RSA2_PRIVATE.length() > 0);
            Map<String, String> params = AliayOrderInfoUtil.buildOrderParamMap(AlipayConfig.APPID, rsa2, payRMB);
            String orderParam = AliayOrderInfoUtil.buildOrderParam(params); //拼接订单信息

            String privateKey = rsa2 ? AlipayConfig.RSA2_PRIVATE : AlipayConfig.RSA_PRIVATE;
            String sign = AliayOrderInfoUtil.getSign(params, privateKey, rsa2); //然后并对订单信息使用私钥进行RSA加密
            String orderInfo = orderParam + "&" + sign;
            new AliPayThread(orderInfo).start();  //支付行为需要在独立的非ui线程中执行
        }

        /**
         * 支付宝支付异步任务
         */
        private class AliPayThread extends Thread {
            private String orderInfo;
            private AliPayThread(String orderInfo) {
                this.orderInfo = orderInfo;
            }

            @Override
            public void run() {
                PayTask alipay = new PayTask(mthis);
                result = alipay.payV2(orderInfo, true);
                Log.i("zjp", "result=" + result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                /**
                 *  官方result返回结果参考：https://docs.open.alipay.com/204/105302
                 *  我这里返回到result格式为：
                 *      {
                 resultStatus = 9000, result = {
                 "alipay_trade_app_pay_response": {
                 "code": "10000",
                 "msg": "Success",
                 "app_id": "2017112400138529",
                 "auth_app_id": "2017112400138529",
                 "charset": "utf-8",
                 "timestamp": "2018-01-29 14:46:33",
                 "total_amount": "0.01",
                 "trade_no": "2018012921001004940219217398",
                 "seller_id": "2088821472668202",
                 "out_trade_no": "0129144616-2725"
                 }
                 */
                Log.d("zjp", "result=" + result);
                mHandler.sendMessage(msg);
            }
        }

        @SuppressLint("HandlerLeak")
        private Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case SDK_PAY_FLAG:
                        // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                        Map<String, String> mapPayResult = (Map<String, String>) msg.obj;
                        String resultStatus = mapPayResult.get("resultStatus");
                        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表参考：https://docs.open.alipay.com/204/105301
                        if (TextUtils.equals(resultStatus, "9000")) {
                            showToast("支付成功");
                            EventBus.getDefault().post(new PayResultEvent());//支付成功后，发个通知
                        } else {
                            // 判断resultStatus 为非“9000”则代表可能支付失败
                            // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                            if (TextUtils.equals(resultStatus, "8000")) {
                                showToast("支付结果确认中");
                            } else if (TextUtils.equals(resultStatus, "6001")) { //用户中途取消
                                showToast("取消支付");
                            } else {
                                // 其他值就可以判断为支付失败
                                showToast("支付失败");
                            }
                        }
                        break;
                }
            }
        };
    }

}
