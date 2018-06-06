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
     * portal充值支付列表
     */
    static final String PORTAL_RECHARGE_PAY_LIST_KEY = "portal_recharge_pay_list_conf";

    /**
     * 服务费配置
     */
    static final String PORTAL_SERVICE_FEE = "protal:dict:serviceFee";

    /**
     * 首页城市信息
     */
    static final String PORTAL_HOME_CITYS_KEY = "portal:home:citys";

    /**
     * 首页广告banner（top、local、nationwide）
     */
    static final String PORTAL_HOME_AD_BANNER = "portal:home:ad:banner:";

    /**
     * 分类广告banner
     */
    static final String PORTAL_HOME_AD_CLASS_BANNER = "portal:home:ad:class:banner:";

    /**
     * 首页分类
     */
    static final String PORTAL_HOME_SHOP_CLASS = "portal:home:shop:class:";

    /**
     * 首页品质好店
     */
    static final String PORTAL_HOME_QUALITY_SHOP = "portal:home:quality:shop:";

    /**
     * 筛选商户分类
     */
    static final String PORTAL_HOME_SHOP_FIRST_CLASS = "portal:home:shop:first:class:";

    /**
     * 筛选类型
     */
    static final String PORTAL_HOME_SHOP_DISTRICT = "portal:home:shop:district:";

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
     * 品质好店shopid集合
     */
    static final String PORTAL_HOME_QUALITY_SHOPID = "portal:home:quality:shopid";

    /**
     * 商家收藏量
     */
    static final String PORTAL_HOME_SHOP_BROWSENUM = "portal:home:shop:browsenum:";

    /**
     * 探店收藏量
     */
    static final String PORTAL_HOME_INTERVIEW_BROWSENUM = "portal:home:interview:browsenum:";

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
     * 登陆短信验证码
     */
    static final String PORTAL_LOGIN_SMS_CODE = "portal:login:sms:conf";

    /**
     * 地推注册登陆时间配置
     */
    static final String PORTAL_GROUND_REGISTER_LOGIN_DATE_CONF = "PORTAL:GROUND:REGISTER:FISTER_LOGIN_DATE:";

    /**
     * 榜单下方评论
     */
    static final String PORTAL_DISCOVER_ONE_SHOP_COMMENT = "portal:discover:one:shop:comment";

    /**
     * 不固定榜单
     */
    static final String PORTAL_USER_DOUBLE_TRAIL = "portal:user:double:trail:";

    /**
     * 黑名单号码key
     */
    static final String PORTAL_RECHARGE_RATE = "portal_recharge_rate";

}
