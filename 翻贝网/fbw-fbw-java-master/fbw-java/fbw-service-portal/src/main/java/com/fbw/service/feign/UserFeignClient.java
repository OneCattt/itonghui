package com.fbw.service.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fbw.service.config.feign.FeignDisableHystrixConfiguration;
import com.fbw.service.entity.common.CommonRsEntity;
import com.fbw.service.entity.user.UserBalanceDetailEntity;
import com.fbw.service.entity.user.UserCollectBusinEntity;
import com.fbw.service.entity.user.UserCommentBannerConfEntity;
import com.fbw.service.entity.user.UserCommentBusinEntity;
import com.fbw.service.entity.user.UserDeviceInfoEntity;
import com.fbw.service.entity.user.UserGroundInfoBusinEntity;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.entity.user.UserOrderInfoBusinEntity;
import com.fbw.service.entity.user.UserPointDetailEntity;
import com.fbw.service.entity.user.UserRedPacketBusinEntity;
import com.fbw.service.entity.user.UserShopFeedbackBusinEntity;
import com.fbw.service.exception.InnerException;

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
     * 通过微信号获取用户详细信息 <功能详细描述>
     * @param mobile
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/selectByOpenId", method = RequestMethod.GET)
    public UserInfoBusinEntity selectByOpenId(@RequestParam("openId") String openId);

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
    public CommonRsEntity updateRealName(@RequestParam("realName") String realName,
            @RequestParam("IDCard") String IDCard, @RequestParam("id") Integer id);

    /**
     * 验证身份信息 <功能详细描述>
     * @param realName
     * @param IDCard
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/selectRealNameIsTrue", method = RequestMethod.POST)
    public CommonRsEntity selectRealNameIsTrue(@RequestParam("realName") String realName,
            @RequestParam("IDCard") String IDCard, @RequestParam("id") Integer id);

    /**
     * 设置支付密码 <功能详细描述>
     * @param payPassword
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/updatePayPassword", method = RequestMethod.POST)
    public boolean updatePayPassword(@RequestParam("payPassword") String payPassword,
            @RequestParam("mobile") String mobile);

    /**
     * 通过id更新用户信息 <功能详细描述>
     * @param userInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/updateUserInfoSelective", method = RequestMethod.POST)
    public boolean updateUserInfoSelective(@RequestBody UserInfoBusinEntity userInfoBusinEntity);

    /**
     * 判断支付密码是否正确 <功能详细描述>
     * @param payPassword
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/selectPayPwdIsRight", method = RequestMethod.POST)
    public boolean selectPayPwdIsRight(@RequestParam("payPassword") String payPassword,
            @RequestParam("mobile") String mobile);

    /**
     * 判断是否设置支付密码 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/isSetPayPasswordRight", method = RequestMethod.POST)
    public int isSetPayPasswordRight(@RequestParam("mobile") String mobile);

    /**
     * 关闭支付密码 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/updateClosePayPassword", method = RequestMethod.POST)
    public boolean updateClosePayPassword(@RequestParam("mobile") String mobile);

    /**
     * 更新用户积分<功能详细描述>
     * @param remark（1:消费买单，2:评论十字以上,3:带图评论,4:带图评论以及十字以上,5:消费买单会员日双倍，6:积分兑换奖品,7:大转盘抽奖，8:优质评论）
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/updateUserPoint", method = RequestMethod.POST)
    public CommonRsEntity updateUserPoint(@RequestBody UserPointDetailEntity userPointDetail);

    /**
     * 翻贝轨迹 <功能详细描述>
     * @param id
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/selectUserdoubleTrail", method = RequestMethod.GET)
    public UserInfoBusinEntity selectUserdoubleTrail(@RequestParam("id") Integer id);

    /**
     * 存储用户商家反馈 <功能详细描述>
     * @param userInforBusinEntity
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/saveUserAndShopfeedBack", method = RequestMethod.POST)
    public boolean saveUserAndShopfeedBack(@RequestBody UserShopFeedbackBusinEntity userInforBusinEntity);

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
    public UserOrderInfoBusinEntity getUserOrderInfo(@RequestParam("orderNumber") String orderNumber,
            @RequestParam("refundStatus") String refundStatus);

    /**
     * 查询当前订单评论状态和退款状态 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userOrder/getUserOrderCommentAndRefund", method = RequestMethod.GET)
    public UserOrderInfoBusinEntity getUserOrderCommentAndRefund(@RequestParam("orderNumber") String orderNumber);

    /**
     * 根据订单号更新评论状态 <功能详细描述>
     * @param orderNumber
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userOrder/updateCommentStatus", method = RequestMethod.POST)
    public boolean updateCommentStatus(@RequestParam("orderNumber") String orderNumber);

    /**
     * 获取7天内未评价订单 <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/userOrder/selectSevenDayNocommentOrder")
    public List<UserOrderInfoBusinEntity> selectSevenDayNocommentOrder();

    /**
     * 获取当前店铺订单数量 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/userOrder/getShopOrderNumber")
    public int getShopOrderNumber(@RequestParam("shopId") Integer shopId);

    /**
     * 获取当前用户所有收藏列表 <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userCollect/getUserCollectList", method = RequestMethod.GET)
    public List<UserCollectBusinEntity> getUserCollectList(@RequestParam("userId") Integer userId,
            @RequestParam("begin") Integer begin);

    /**
     * 获取当前用户所有收藏探店id <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userCollect/getUserCollectInterviewShopList", method = RequestMethod.GET)
    public List<String> getUserCollectInterviewShopList(@RequestParam("userId") Integer userId,
            @RequestParam("begin") Integer begin);

    /**
     * 删除或点击收藏的店铺 <功能详细描述>
     * @param userId
     * @param shopId
     * @param status
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userCollect/updateUserCollect", method = RequestMethod.POST)
    public CommonRsEntity updateUserCollect(@RequestParam("userId") Integer userId,
            @RequestParam("shopId") Integer shopId, @RequestParam("status") Integer status);

    /**
     * 用户点击取消或收藏探店 <功能详细描述>
     * @param userId
     * @param interviewShopId
     * @param status
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userCollect/updateUserCollectInterviewShop", method = RequestMethod.POST)
    public CommonRsEntity updateUserCollectInterviewShop(@RequestParam("userId") Integer userId,
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
     * 获取当前店铺收藏数量 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userCollect/getCollectNumByShopId", method = RequestMethod.GET)
    public int getCollectNumByShopId(@RequestParam("shopId") Integer shopId);

    /**
     * 获取当前探店收藏数量 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userCollect/getCollectInterviewNumByShopId", method = RequestMethod.GET)
    public int getCollectInterviewNumByShopId(@RequestParam("interviewShopId") Integer interviewShopId);

    /**
     * 获取所有店铺收藏量 <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/userCollect/getAllShopCollectNumber")
    public List<UserCollectBusinEntity> getAllShopCollectNumber();

    /**
     * 获取当前用户暂未评价数量 <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userOrder/getCountUserNoCommOrder", method = RequestMethod.GET)
    public int getCountUserNoCommOrder(@RequestParam("userId") Integer userId);

    /**
     * 根据手机号查询90天内所有订单和充值明细 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userOrder/getUserBalanceDetailById", method = RequestMethod.GET)
    public List<UserBalanceDetailEntity> getUserBalanceDetailById(@RequestParam("userId") Integer userId,
            @RequestParam("begin") Integer begin);

    /**
     * 存储用户余额明细 <功能详细描述>
     * @param userBalanceDetail
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userOrder/insertUserBalanceDetail", method = RequestMethod.POST)
    public boolean insertUserBalanceDetail(@RequestBody UserBalanceDetailEntity userBalanceDetail);

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
    public UserCommentBusinEntity getOneUserCommentInfo(@RequestParam("orderId") String orderId);

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
     * 获取当前商店的平均评论分数/评论数量/最近最好评论（头像、昵称等）<功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userComment/getOneShopInfoMainComment", method = RequestMethod.GET)
    public UserCommentBusinEntity getOneShopInfoMainComment(@RequestParam("shopId") Integer shopId);

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
            @RequestParam("begin") Integer begin, @RequestBody UserCommentBannerConfEntity userCommentBanner);

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
    public CommonRsEntity insertUserComment(@RequestBody UserCommentBusinEntity userCommentBusinEntity);

    /**
     * 获取评价banner<功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userComment/getCommentBanner", method = RequestMethod.GET)
    public List<UserCommentBannerConfEntity> getCommentBanner();

    /**
     * 获取评价banner<功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userComment/getCommentBannerById", method = RequestMethod.GET)
    public UserCommentBannerConfEntity getCommentBannerById(@RequestParam("id") Integer id);

    /**
     * 获取评价banner对应评论数量<功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userComment/getCommentBannerNumber", method = RequestMethod.GET)
    public int getCommentBannerNumber(@RequestBody UserCommentBannerConfEntity userCommentBanner);

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
    public List<UserRedPacketBusinEntity> selectRedPacketListById(@RequestParam("userId") Integer userId,
            @RequestParam("begin") Integer begin);

    /**
     * 获取用户已使用、已过期红包列表 <功能详细描述>
     * @param status
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/selectUsedRedPacketListById", method = RequestMethod.GET)
    public List<UserRedPacketBusinEntity> selectUsedRedPacketListById(@RequestParam("userId") Integer userId,
            @RequestParam("begin") Integer begin);

    /**
     * <功能详细描述>获取用户的消费次数
     * @param shopId
     * @param userMobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userOrder/getUserConsumptionTimes", method = RequestMethod.GET)
    public int getUserConsumptionTimes(@RequestParam("shopId") int shopId, @RequestParam("userId") int userId);

    /**
     * 
     * <功能详细描述>获取商家的消费次数
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userOrder/getShopConsumptionTimes", method = RequestMethod.GET)
    public int getShopConsumptionTimes(@RequestParam("shopId") int shopId);

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
     * <功能详细描述>查询用户地推信息
     * @param mobile
     * @param status
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/queryUserGroundInfo", method = RequestMethod.GET)
    public UserGroundInfoBusinEntity queryUserGroundInfo(@RequestParam("mobile") String mobile);

    /**
     * 
     * <功能详细描述>查询用户的设备ID信息
     * @param deviceId 设备ID
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/queryUserDeviceIdInfo", method = RequestMethod.GET)
    public boolean queryUserDeviceIdInfo(@RequestParam("deviceId") String deviceId);

    /**
     * 
     * <功能详细描述> 更新地推用户注册状态
     * @param mobile 手机号
     * @param status 状态
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/updateGroundUserResgisterStatus", method = RequestMethod.POST)
    public boolean updateGroundUserResgisterStatus(@RequestParam("mobile") String mobile,
            @RequestParam("status") String status);

    /**
     * 保存用户设备信息 <功能详细描述>
     * @param userGroundInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/saveUserDeiviceInfo", method = RequestMethod.POST)
    public boolean saveUserDeiviceInfo(@RequestBody UserDeviceInfoEntity userDeviceInfoEntity);

    /**
     * 查询该手机号是否是地推人员 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/selectIsGroundByMobile", method = RequestMethod.POST)
    public boolean selectIsGroundByMobile(@RequestParam("mobile") String mobile);

    /**
     * 自动评价 <功能详细描述>
     * @param orderNumber
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userComment/autoInsertUserComment", method = RequestMethod.POST)
    public CommonRsEntity autoInsertUserComment(@RequestParam("orderNumber") String orderNumber,
            @RequestParam("userId") Integer userId);

}
