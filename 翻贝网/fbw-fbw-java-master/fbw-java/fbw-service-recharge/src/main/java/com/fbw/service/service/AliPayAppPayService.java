package com.fbw.service.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.fbw.service.base.BaseService;
import com.fbw.service.contents.AliPayRechargeConstant;
import com.fbw.service.contents.ErrorMsgConstant;
import com.fbw.service.contents.RechargeConstant;
import com.fbw.service.exception.InnerCode;
import com.fbw.service.exception.InnerException;
import com.fbw.service.util.AlipayCore;

import net.sf.json.JSONArray;

/**
 * 
 * <功能详细描述> 支付宝APP版支付统一下单接口
 * @author JACK HUANG
 * @version [版本号, 2017年8月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class AliPayAppPayService extends BaseService
{
    /**
     * 支付宝回调地址
     */
    // @Value("${fbwconfig.aliPay.app_notify}")
    private final String ALIPAY_APP_NOTIFY = "http://release.fbw-china.com:8040/recharge/NotifyAppAliPay";

    /**
     * 支付宝请求使用的编码格式
     */
    private final String ALIPAY_RES_CHARSET = "utf-8";

    /**
     * 支付宝请求报文格式
     */
    private final String ALIPAY_RES_FORMAT = "JSON";

    /**
     * 
     * <功能详细描述> 支付宝统一下单接口
     * @param rechargeNumber
     * @param money
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public String unifiedOrder(String rechargeNumber, BigDecimal money, String mobiles) throws InnerException
    {
        // 获取发送支付宝统一订单接口请求报文，返回给客户端
        return getSendMsg(rechargeNumber, money, mobiles);

    }

    /**
     * 
     * <功能详细描述> 获取发送支付宝统一订单接口请求报文
     * @param rechargeNumber
     * @param money
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private String getSendMsg(String orderNumber, BigDecimal money, String mobiles) throws InnerException
    {
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, String> biz_content = new HashMap<String, String>();
        // 该笔订单允许的最晚付款时间，逾期将关闭交易
        biz_content.put("timeout_express", AliPayRechargeConstant.RECHARGE_ALIPAY_ORDER_TIMEOUT_EXPRESS);
        // 支付宝商户ID
        biz_content.put("seller_id", AliPayRechargeConstant.RECHARGE_ALIPAY_SELLER_ID);
        // 销售产品码，商家和支付宝签约的产品码，为固定值
        biz_content.put("product_code", AliPayRechargeConstant.RECHARGE_ALIPAY_PRODUCT_CODE);
        // 订单总金额，单位为元，精确到小数点后两位
        biz_content.put("total_amount", String.valueOf(money));
        // 商品的标题
        biz_content.put("subject", RechargeConstant.RECHARGE_GOODS_DES);
        // 对一笔交易的具体描述信息
        biz_content.put("body", RechargeConstant.RECHARGE_GOODS_DES);
        // 公用回传参数
        biz_content.put("passback_params", mobiles);
        // 充值订单号
        biz_content.put("out_trade_no", orderNumber);
        JSONArray jsonArray = JSONArray.fromObject(biz_content);
        Map<String, String> alipay = new HashMap<String, String>();
        // 支付宝分配给开发者的应用ID
        alipay.put("app_id", AliPayRechargeConstant.RECHARGE_ALIPAY_APP_ID);
        alipay.put("biz_content", jsonArray.toString().substring(1, jsonArray.toString().length() - 1));
        // 支付宝请求使用的编码格式
        alipay.put("charset", ALIPAY_RES_CHARSET);
        // 支付宝请求报文格式
        alipay.put("format", ALIPAY_RES_FORMAT);
        // 支付宝统一订单方法名
        alipay.put("method", AliPayRechargeConstant.RECHARGE_ALIPAY_UNIFIED_ORDER_METHOD);
        // 支付宝回调地址
        alipay.put("notify_url", ALIPAY_APP_NOTIFY);
        // 商户生成签名字符串所使用的签名算法类型
        alipay.put("sign_type", "RSA");
        alipay.put("timestamp", format.format(new Date()));
        // 调用的接口版本，固定为：1.0
        alipay.put("version", "1.0");
        alipay.put("sign", getAliPaySign(alipay));
        // 获取支付宝JSON请求报文
        return getAliPayJsonMsg(alipay);

    }

    /**
     * 
     * <功能详细描述> 获取支付宝JSON请求报文
     * @param alipay
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private String getAliPayJsonMsg(Map<String, String> alipay) throws InnerException
    {
        String json4 = "";
        try
        {
            // 把数组所有元素排序
            json4 = AlipayCore.createLinkString(alipay);
        }
        catch (UnsupportedEncodingException e)
        {
            // 充值阿里转码异常
            throw new InnerException(ErrorMsgConstant.RECHARGE_ALIPAY_ENCODING_ERROR,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_ALIPAY_ENCODING_ERROR));
        }
        return json4;

    }

    /**
     * 
     * <功能详细描述> 获取支付宝签名
     * @param alipay
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private String getAliPaySign(Map<String, String> alipay) throws InnerException
    {
        String sign = "";
        try
        {
            sign = AlipaySignature.rsaSign(alipay, AliPayRechargeConstant.RECHARGE_ALIPAY_PRIVATE_KEY, "UTF-8");
        }
        catch (AlipayApiException e1)
        {
            // 充值阿里验签异常
            throw new InnerException(ErrorMsgConstant.RECHARGE_ALIPAY_SIGN_ERROR,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_ALIPAY_SIGN_ERROR));
        }
        return sign;
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        // TODO Auto-generated method stub

    }
}
