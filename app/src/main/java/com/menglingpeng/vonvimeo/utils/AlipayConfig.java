package com.menglingpeng.vonvimeo.utils;

public class AlipayConfig {

    /**
     * 支付宝支付业务：入参app_id，上文创建应用时候，已经得到
     * 由于App支付功能需要签约，因此需要上传公司信息和证件等资料进行签约
     * 以下参数，由上传完整公司信息后即可得到
     */
    public static final String APPID = "XXXXXXXXXXXXX";
    // 商户PID
    public static final String PARTNER = "XXXXXXXXXXXXX";

    // 商户收款账号

    public static final String SELLER = "XXXXXXXXXX";
    /**
     * 支付宝账户登录授权业务：入参target_id值
     * 可以用时间戳
     */
    public static final String TARGET_ID = AliayOrderInfoUtil.getOutTradeNo();

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     *  使用支付宝提供的工具生成RSA公钥和私钥
     *  工具地址：https://doc.open.alipay.com/docs/doc.htmtreeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "";
    public static final String RSA_PRIVATE = "";

}
