package com.fbw.service.contents;

/**
 * 
 * <功能详细描述> 缓存KEY常量类
 * @author JACK HUANG
 * @version [版本号, 2017年8月16日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface CacheKeyConstant
{

    /**
     * 测试号码key
     */
    static final String PORTAL_TEST_MOBILES_KEY = "portal_login_test_mobils_conf";

    /**
     * 黑名单号码key
     */
    static final String PORTAL_BLACK_MOBILES_KEY = "portal_login_black_mobils_conf";

    /**
     * portal登陆token
     */
    static final String PORTAL_LOGIN_TOKEN_KEY = "portal_login_token_conf";

    /**
     * 服务费配置
     */
    static final String PORTAL_SERVICE_FEE = "protal:dict:serviceFee";

    /**
     * 首页城市信息
     */
    static final String PORTAL_HOME_CITYS_KEY = "portal:home:citys";

    /**
     * 首页广告banner
     */
    static final String PORTAL_HOME_AD_BANNER = "portal:home:ad:banner:";

    /**
     * 首页分类banner
     */
    static final String PORTAL_HOME_CLASS_BANNER = "portal:home:class:banner:";

    /**
     * 首页品质好店
     */
    static final String PORTAL_HOME_QUALITY_SHOPS = "portal:home:quality:shops:";

    /**
     * 筛选商户分类
     */
    static final String PORTAL_HOME_SHOPS_CLASS = "portal:home:shops:class:";

    /**
     * 筛选类型
     */
    static final String PORTAL_HOME_SHOPS_DISTRICT = "portal:home:shops:district:";

    /**
     * 探店
     */
    static final String PORTAL_DISCOVER_INTERVIEW = "portal:discover:interview:";

    /**
     * 支付密码
     */
    static final String PORTAL_HOME_PAY_PASSWORD = "portal:home:pay:password";

    /**
     * 热词
     */
    static final String PORTAL_HOME_HOT_WORDS = "portal:home:hot:words:";

    /**
     * 固定榜单
     */
    static final String PORTAL_DISCOVER_FIX_RANK = "portal:discover:fix:rank:";

    /**
     * 不固定榜单
     */
    static final String PORTAL_DISCOVER_NOFIX_RANK = "portal:discover:nofix:rank:";

    /**
     * 榜单列表
     */
    static final String PORTAL_DISCOVER_RANK_LIST = "portal:discover:nofix:rank:";

    /**
     * 搜索热词
     */
    static final String PORTAL_HOME_SEARCH_ = "portal:home:search:";

    /**
     * 限时翻倍花开始时间
     */
    static final String PORTAL_WALLET_TIME_LIMIT_PAY_START_DATE = "portal:time:limit:start:date:conf";

    /**
     * 限时翻倍花结束时间
     */
    static final String PORTAL_WALLET_TIME_LIMIT_PAY_END_DATE = "portal:time:limit:end:date:conf";

    /**
     * 登陆短信验证码
     */
    static final String SHOP_PORTAL_LOGIN_SMS_CODE = "SHOP:PORTAL:LOGIN:SMS:CONF";

    /**
     * 地推注册登陆时间配置
     */
    static final String PORTAL_GROUND_REGISTER_LOGIN_DATE_CONF = "PORTAL:GROUND:REGISTER:FISTER_LOGIN_DATE_";

}
