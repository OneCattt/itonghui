package com.fbw.service.contents;

/**
 * 
 * <功能详细描述> 支付宝充值常量类
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface AliPayRechargeConstant
{
    /**
     * 支付宝商户ID
     */
    static final String RECHARGE_ALIPAY_SELLER_ID = "2088221986804976";

    /**
     * 销售产品码，商家和支付宝签约的产品码
     */
    static final String RECHARGE_ALIPAY_PRODUCT_CODE = "QUICK_MSECURITY_PAY";

    /**
     * 支付宝订单超时时间
     */
    static final String RECHARGE_ALIPAY_ORDER_TIMEOUT_EXPRESS = "15m";

    /**
     * 支付宝分配给开发者的应用ID
     */
    static final String RECHARGE_ALIPAY_APP_ID = "2016081101735106";

    /**
     * 支付宝统一订单方法名
     */
    static final String RECHARGE_ALIPAY_UNIFIED_ORDER_METHOD = "alipay.trade.app.pay";

    /**
     * 支付宝私匙
     */
    static final String RECHARGE_ALIPAY_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKWtId+yPCUeVV2/"
            + "no70gL9d1FQ3qDBHCtgxXF1K/1nI0jK1E3lEEqI6nQJVibmCrWAhwD1BJUBmV/25"
            + "cWwMIHLzAnWM7WXeMqUT3HciQF4EcUJ2Un1frlglb2eJPsisUpGMuLYbjyvE2tpr"
            + "dQFSBtBqsqyd0HcmtVZ18PED93LJAgMBAAECgYEAinrzdGIraa84FXtEUiNGti38"
            + "e0RmPaw0AU8I8CXu2CJGqAG4vETM+nLZqAjNbJUwy8iKQekA9RpWdAqcJn7gYzwQ"
            + "hVtT69J0F3EF9Uq+exw7rmUiArzLXCe6B177NPSxclA1Gk/SI71SVPfHLI9ih1z6"
            + "RUCl9kOxSy2XKVeBPQECQQDUWdcMXN6abLgo8L+igVY1+MIqb8hxZVlkPl4uqh8b"
            + "+h3cusPZlPWl6d/OKJPiqR27K4J0nRUyL5H6+K8+ru9pAkEAx7s3jeQ+34pELDlT"
            + "QMA5DitJGvfOs06tmhuRG/I1ZRP4D5xkl0g5qm9eHkuuzrq8Vp/X65Awyer7oq1p"
            + "+N1cYQJAMlorN+89iXiGIo8TocDxZFLcF5vx95GEpNR1GLOWNNgWBKT0k79SkEjo"
            + "nMXaa0CWoC8s++HTiNbm0o/tXfu1IQJAFCElNzKyt3rK5h4c6uC0n13zbzlcZqve"
            + "STLQDB38DaTWklQzux93WFsDBFCiXIlrY9uWOlJ86Ey7cajCjmdY4QJBAJyEHsnW"
            + "2G3neBBN+rW31eJ66pJX4ySP9CH8YK5w2ZZ83no6oIw7rGOsR9msVDq0LB7qATaz" + "tQ0SeB4ODRb/eSQ=";

    /**
     * 支付宝公匙
     */
    static final String RECHARGE_ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";

}
