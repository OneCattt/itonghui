package com.fbw.service.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.base.BaseService;
import com.fbw.service.contents.ErrorMsgConstant;
import com.fbw.service.contents.RechargeConstant;
import com.fbw.service.contents.WeChatRechargeConstant;
import com.fbw.service.exception.InnerCode;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.CommonFeignClient;
import com.fbw.service.util.HttpRequestUtil;
import com.fbw.service.util.NomalUtil;

/**
 * 
 * <功能详细描述> 充值service层
 * @author JACK HUANG
 * @version [版本号, 2017年8月20日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class WeChatAppPayService extends BaseService
{

    @Autowired
    CommonFeignClient commonFeignClient;

    /**
     * 微信回调地址
     */
    // @Value("${fbwconfig.weChat.app_notify}")
    private String WECHAT_APP_NOTIFY = "http://release.fbw-china.com:8040/recharge/NotifyAppWechatPay";

    /**
     * 微信支付类型
     */
    private final String WECHAT_PAY_TYPE = "APP";

    /**
     * 
     * <功能详细描述> 微信统一下单接口
     * @param rechargeNumber 充值号码
     * @param money 充值金额
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> unifiedOrder(String rechargeNumber, BigDecimal money, String reqestIp, String mobiles)
            throws InnerException
    {
        // 获取发送微信统一订单接口请求报文
        String postDate = getSendWeChatPayMsg(rechargeNumber, money, reqestIp, mobiles);
        // 发送请求
        String returndata = HttpRequestUtil.sendPost(WeChatRechargeConstant.RECHARGE_WECHAT_UNIFIED_ORDER_URL,
                postDate);
        // 效验微信支付请求返回报文
        checkPayResMsg(returndata);
        // 构造成功报文
        return buildSuccessMsg(NomalUtil.getMapFromXML(returndata));
    }

    /**
     * 
     * <功能详细描述>效验微信支付请求返回报文
     * @param returnData
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private void checkPayResMsg(String returnData) throws InnerException
    {
        if (NomalUtil.isNull(returnData))
        {
            throw new InnerException(ErrorMsgConstant.RECHARGE_WECHAT_UNIFIED_ORDER_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_WECHAT_UNIFIED_ORDER_FAIL));
        }

    }

    /**
     * 
     * <功能详细描述> 构造微信成功报文
     * @param retcode
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private Map<String, Object> buildSuccessMsg(Map<String, Object> retcode) throws InnerException
    {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("appid", retcode.get("appid"));
        data.put("partnerid", retcode.get("mch_id"));
        data.put("prepayid", retcode.get("prepay_id"));
        data.put("noncestr", retcode.get("nonce_str"));
        data.put("package", "Sign=WXPay");
        data.put("timestamp", String.valueOf(System.currentTimeMillis()).substring(0, 10));
        data.put("sign", getWeChatSign(data));
        return data;

    }

    /**
     * 
     * <功能详细描述> 获取发送微信支付请求报文
     * @param rechargeNumber
     * @param money
     * @param reqestIp
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public String getSendWeChatPayMsg(String orderNumber, BigDecimal money, String reqestIp, String mobiles)
            throws InnerException
    {
        Map<String, Object> unifiedorder = new HashMap<String, Object>();
        // 应用ID 微信开放平台审核通过的应用APPID
        unifiedorder.put("appid", WeChatRechargeConstant.RECHARGE_WECHAT_APPID);
        // 商户号 微信支付分配的商户号
        unifiedorder.put("mch_id", WeChatRechargeConstant.RECHARGE_WECHAT_MCHID);
        // 随机字符串，不长于32位
        unifiedorder.put("nonce_str", commonFeignClient.getrandomstring(32));
        // 商品描述
        unifiedorder.put("body", RechargeConstant.RECHARGE_GOODS_DES);
        // 附加数据 ，在查询API和支付通知中原样返回
        unifiedorder.put("attach", mobiles);
        // 商户订单号 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一
        unifiedorder.put("out_trade_no", orderNumber);
        // 订单总金额，单位为分
        unifiedorder.put("total_fee", (int) Math.floor(money.doubleValue() * 100));
        // 用户端实际ip
        unifiedorder.put("spbill_create_ip", "47.93.158.253");
        // 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数
        unifiedorder.put("notify_url", WECHAT_APP_NOTIFY);
        // 支付类型
        unifiedorder.put("trade_type", WECHAT_PAY_TYPE);
        // 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
        unifiedorder.put("sign", getWeChatSign(unifiedorder));
        List<Map.Entry<String, Object>> list = NomalUtil.mapSortToList(unifiedorder);
        return NomalUtil.getXmlFromMap(list);

    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        // TODO Auto-generated method stub

    }
}
