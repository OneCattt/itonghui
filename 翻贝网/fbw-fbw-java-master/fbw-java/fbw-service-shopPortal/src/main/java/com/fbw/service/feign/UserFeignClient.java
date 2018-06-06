package com.fbw.service.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fbw.service.config.feign.FeignDisableHystrixConfiguration;
import com.fbw.service.entity.user.UserCommentBusinEntity;
import com.fbw.service.entity.user.UserGroundInfoBusinEntity;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.entity.user.UserOrderInfoBusinEntity;
import com.fbw.service.entity.user.UserRedPacketBusinEntity;

/**
 * 
 * <功能详细描述> 用户套件的feign客户端
 * @author JACK HUANG
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@FeignClient(name = "fbw-service-user", configuration = FeignDisableHystrixConfiguration.class)
public interface UserFeignClient
{
    /**
     * 
     * <功能详细描述>获取用户基本信息list
     * @param mobile 手机号
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/getUserBaseInfo?mobile={mobile}", method = RequestMethod.GET)
    public UserInfoBusinEntity getUserBaseInfo(@PathVariable("mobile") String mobile);

    /**
     * 保存用户基本信息 <功能详细描述>
     * @param userInforBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/saveUserBaseInfo", method = RequestMethod.POST)
    public boolean saveUserBaseInfo(@RequestBody UserInfoBusinEntity userInforBusinEntity);

    /**
     * 通过用户id获取用户详细信息 <功能详细描述>
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/getUserBaseInfoById?id={id}", method = RequestMethod.GET)
    public UserInfoBusinEntity getUserBaseInfoById(@PathVariable("id") int id);

    /**
     * 根据用户Id更新用户基本信息 <功能详细描述>
     * @param userInforBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/updateUserBaseInfoById", method = RequestMethod.POST)
    public boolean updateUserBaseInfoById(@RequestBody UserInfoBusinEntity userInforBusinEntity);

    /**
     * 根据mobile查询用户余额 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/queryUserBalance", method = RequestMethod.GET)
    public String getBalanceByMobile(@RequestParam("mobile") String mobile);

    /**
     * 获取用户信息列表 <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/getUserInfoList", method = RequestMethod.GET)
    public List<UserInfoBusinEntity> getUserInfoList();

    /**
     * 用户实名认证 <功能详细描述>
     * @param realName
     * @param IDCard
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/updateRealName", method = RequestMethod.POST)
    public boolean updateRealName(@RequestParam("realName") String realName, @RequestParam("IDCard") String IDCard,
            @RequestParam("id") Integer id);

    /**
     * 验证身份信息 <功能详细描述>
     * @param realName
     * @param IDCard
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/selectRealNameIsTrue", method = RequestMethod.POST)
    public boolean selectRealNameIsTrue(@RequestParam("realName") String realName,
            @RequestParam("IDCard") String IDCard, @RequestParam("id") Integer id);

    /**
     * 设置支付密码 <功能详细描述>
     * @param payPassword
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/updatePayPassword", method = RequestMethod.POST)
    public boolean updatePayPassword(@RequestParam("payPassword") String payPassword, @RequestParam("id") Integer id);

    /**
     * 判断支付密码是否正确 <功能详细描述>
     * @param payPassword
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/selectPayPwdIsRight", method = RequestMethod.POST)
    public boolean selectPayPwdIsRight(@RequestParam("payPassword") String payPassword, @RequestParam("id") Integer id);

    /**
     * 判断是否设置支付密码 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/isSetPayPasswordRight", method = RequestMethod.POST)
    public boolean isSetPayPasswordRight(@RequestParam("mobile") String mobile);

    /**
     * 关闭支付密码 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/updateClosePayPassword", method = RequestMethod.POST)
    public boolean updateClosePayPassword(@RequestParam("mobile") String mobile);

    /**
     * 获取当前用户所有订单列表 <功能详细描述>
     * @param userId
     * @param begin
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userOrder/getUserOrderList", method = RequestMethod.GET)
    public List<UserOrderInfoBusinEntity> getUserOrderList(@RequestParam("userId") int userId,
            @RequestParam("begin") int begin);

    /**
     * 获取当前用户所有未评价订单列表 <功能详细描述>
     * @param userId
     * @param begin
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userOrder/getUserNoCommOrderList", method = RequestMethod.GET)
    public List<UserOrderInfoBusinEntity> getUserNoCommOrderList(@RequestParam("userId") int userId,
            @RequestParam("begin") int begin);

    /**
     * 获取某一订单详细信息 <功能详细描述>
     * @param orderId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userOrder/getUserOrderInfo", method = RequestMethod.GET)
    public UserOrderInfoBusinEntity getUserOrderInfo(@RequestParam("orderId") int orderId);

    /**
     * 获取当前用户所有收藏列表 <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userCollect/getUserCollectList", method = RequestMethod.GET)
    public List<String> getUserCollectList(@RequestParam("userId") Integer userId);

    /**
     * 获取当前用户所有收藏探店id <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userCollect/getUserCollectInterviewShopList", method = RequestMethod.GET)
    public List<String> getUserCollectInterviewShopList(@RequestParam("userId") Integer userId);

    /**
     * 删除或点击收藏的店铺 <功能详细描述>
     * @param userId
     * @param shopId
     * @param status
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userCollect/updateUserCollect", method = RequestMethod.POST)
    public boolean updateUserCollect(@RequestParam("userId") Integer userId, @RequestParam("shopId") Integer shopId,
            @RequestParam("status") Integer status);

    /**
     * 用户点击取消或收藏探店 <功能详细描述>
     * @param userId
     * @param interviewShopId
     * @param status
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userCollect/updateUserCollectInterviewShop", method = RequestMethod.POST)
    public boolean updateUserCollectInterviewShop(@RequestParam("userId") Integer userId,
            @RequestParam("interviewShopId") Integer interviewShopId, @RequestParam("status") Integer status);

    /**
     * 判断当前商户是否被该用户收藏 <功能详细描述>
     * @param userId
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userCollect/getUserIsCollect", method = RequestMethod.GET)
    public int getUserIsCollect(@RequestParam("userId") Integer userId, @RequestParam("shopId") Integer shopId);

    /**
     * 判断当前探店是否被该用户收藏 <功能详细描述>
     * @param userId
     * @param interviewShopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userCollect/getUserIsCollectInterviewShop", method = RequestMethod.GET)
    public int getUserIsCollectInterviewShop(@RequestParam("userId") Integer userId,
            @RequestParam("interviewShopId") Integer interviewShopId);

    /**
     * 获取当前用户暂未评价数量 <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userOrder/getCountUserNoCommOrder", method = RequestMethod.GET)
    public int getCountUserNoCommOrder(@RequestParam("userId") Integer userId);

    /**
     * 根据手机号查询90天内所有订单 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userOrder/getUserOrderListByMobile", method = RequestMethod.GET)
    public List<Map<String, Object>> getUserOrderListByMobile(@RequestParam("mobile") String mobile);

    /**
     * 更新余额信息 <功能详细描述>
     * @param mobile
     * @param balance
     * @param newBalance
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/updateUserbalance", method = RequestMethod.POST)
    public boolean updateUserbalance(@RequestParam("mobile") String mobile, @RequestParam("balance") String balance,
            @RequestParam("newBalance") String newBalance);

    /**
     * 获取当前用户某一个订单的评论 <功能详细描述>
     * @param orderId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userComment/getOneUserCommentInfo", method = RequestMethod.GET)
    public UserCommentBusinEntity getOneUserCommentInfo(@RequestParam("orderId") Integer orderId);

    /**
     * 获取当前店铺最近最好的评价内容 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userComment/getOneRecentShopComment", method = RequestMethod.GET)
    public UserCommentBusinEntity getOneRecentShopComment(@RequestParam("shopId") Integer shopId);

    /**
     * 获取当前商店的评论总数 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userComment/getOneShopCommentNum", method = RequestMethod.GET)
    public Integer getOneShopCommentNum(@RequestParam("shopId") Integer shopId);

    /**
     * 获取当前商店所有评论,根据isNoPic判断是否带图片 <功能详细描述>
     * @param shopId
     * @param begin
     * @param isNoPic
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userComment/getOneShopAllComment", method = RequestMethod.GET)
    public List<UserCommentBusinEntity> getOneShopAllComment(@RequestParam("shopId") Integer shopId,
            @RequestParam("begin") Integer begin, @RequestParam("isNoPic") Integer isNoPic);

    /**
     * 获取当前商店带图片的评论总数 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userComment/getOneShopAllPicNum", method = RequestMethod.GET)
    public Integer getOneShopAllPicNum(@RequestParam("shopId") Integer shopId);

    /**
     * 插入用户评论 <功能详细描述>
     * @param userCommentBusinEntity
     * @param isNoName
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userComment/insertUserComment", method = RequestMethod.POST)
    public boolean insertUserComment(@RequestBody UserCommentBusinEntity userCommentBusinEntity,
            @RequestParam("isNoName") int isNoName);

    /**
     * 获取当前商店带图片的评论总数 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userComment/getOneShopAvgScore", method = RequestMethod.GET)
    public String getOneShopAvgScore(@RequestParam("shopId") Integer shopId);

    /**
     * 获取可用最大的红包 <功能详细描述>
     * @param userRedPacketBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/getRedPacketWithBig", method = RequestMethod.POST)
    public UserRedPacketBusinEntity getRedPacketWithBig(@RequestBody UserRedPacketBusinEntity userRedPacketBusinEntity);

    /**
     * 保存用户订单信息 <功能详细描述>
     * @param userOrderInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userOrder/saveUserOrderInfo", method = RequestMethod.POST)
    public boolean saveUserOrderInfo(@RequestBody UserOrderInfoBusinEntity userOrderInfoBusinEntity);

    /**
     * 更新红包状态 <功能详细描述>
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/updateRedPacketStatus", method = RequestMethod.POST)
    public boolean updateRedPacketStatus(@RequestParam("id") int id);

    /**
     * 获取当前用户红包列表 <功能详细描述>
     * @param status
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/selectRedPacketListById", method = RequestMethod.GET)
    public List<UserRedPacketBusinEntity> selectRedPacketListById(@RequestParam("status") Integer status,
            @RequestParam("userId") Integer userId);

    /**
     * 获取用户的消费次数 <功能详细描述>
     * @param shopId
     * @param userMobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userOrder/getUserConsumptionTimes", method = RequestMethod.GET)
    public int getUserConsumptionTimes(@RequestParam("shopId") int shopId,
            @RequestParam("userMobile") String userMobile);

    /**
     * 
     * <功能详细描述> 保存用户地推信息
     * @param userGroundInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/saveUserGroundInfo", method = RequestMethod.POST)
    public boolean saveUserGroundInfo(@RequestBody UserGroundInfoBusinEntity userGroundInfoBusinEntity);

    /**
     * 
     * <功能详细描述>更新用户地推注册信息状态
     * @param mobile
     * @param status
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/updateUserGroundRegisterStatus", method = RequestMethod.POST)
    public boolean updateUserGroundRegisterStatus(@RequestParam("mobile") String mobile,
            @RequestParam("status") String status);

    /**
     * 
     * <功能详细描述>查询用户的设备ID信息
     * @param deviceId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/queryUserDeviceIdInfo", method = RequestMethod.GET)
    public boolean queryUserDeviceIdInfo(@RequestParam("deviceId") String deviceId);

}
