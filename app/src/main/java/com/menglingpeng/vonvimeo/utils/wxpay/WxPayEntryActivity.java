package com.menglingpeng.vonvimeo.utils.wxpay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.menglingpeng.vonvimeo.base.BaseActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WxPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {

   private IWXAPI iwxapi;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_wx_pay_entry;
    }

    @Override
    protected void initViews() {
        super.initViews();

        iwxapi = WXAPIFactory.createWXAPI(this, WxPayConfig.APP_ID, true);
        iwxapi.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        iwxapi.handleIntent(intent, this);
    }


    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {

        Log.d("WxPay", "onPayFinish, errCode = " + baseResp.errCode);// 支付结果码
        /**
         * 结果码参考：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=8_5
         */
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            int code = baseResp.errCode;
            switch (code) {
                case 0:
                    showToast("支付成功");
                    break;
                case -1:
                    finish();
                    // 支付失败 可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、
                    // 其他异常等
                    showToast("支付失败");
                    break;
                case -2:
                    finish();
                    showToast("支付取消");
                    break;
            }
        }
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
    }

}
