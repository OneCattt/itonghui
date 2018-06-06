package com.fbw.service.entity.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * <功能详细描述>用户信息表实体类
 * @author jiangruliang
 * @version [版本号, 2017年8月17日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class UserInfoBusinEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer userId;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 支付密码
     */
    private String payPassword;

    /**
     * 微信号
     */
    private String openId;

    /**
     * 身份证
     */
    private String identityCard;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 用户生日
     */
    private String birthday;

    /**
     * 性别,1男2女0未知
     */
    private String sex;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 节省金额
     */
    private BigDecimal saveMoney;

    /**
     * 积分余额
     */
    private Integer point;

    /**
     * 常驻城市
     */
    private String city;

    /**
     * 账号状态
     */
    private String status;

    /**
     * 是否使用支付密码
     */
    private String isUsePassword;

    private String createdAt;

    private Date updatedAt;

    /**
     * 未评价数量
     */
    private Integer uncommNumber;

    /**
     * 红包数量
     */
    private Integer redPacketNumber;

    private Integer groundFlag;

    /**
     * 翻倍轨迹所需字段
     */
    private String shopNumber;

    private String totalOrderMoney;

    private String firstShopName;

    private String favoriteShopName;

    private String firstShopTime;

    private String rechargeFee;

    private String doubleFee;

    private Integer rowNum;

    private Integer allUserNumber;

    private String consumeIndex;

    /**
     * 充值费率
     */
    private String rechargeRate;

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getPayPassword()
    {
        return payPassword;
    }

    public void setPayPassword(String payPassword)
    {
        this.payPassword = payPassword;
    }

    public String getOpenId()
    {
        return openId;
    }

    public void setOpenId(String openId)
    {
        this.openId = openId;
    }

    public String getIdentityCard()
    {
        return identityCard;
    }

    public void setIdentityCard(String identityCard)
    {
        this.identityCard = identityCard;
    }

    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public BigDecimal getBalance()
    {
        return balance;
    }

    public void setBalance(BigDecimal balance)
    {
        this.balance = balance;
    }

    public BigDecimal getSaveMoney()
    {
        return saveMoney;
    }

    public void setSaveMoney(BigDecimal saveMoney)
    {
        this.saveMoney = saveMoney;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(String createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public String getIsUsePassword()
    {
        return isUsePassword;
    }

    public void setIsUsePassword(String isUsePassword)
    {
        this.isUsePassword = isUsePassword;
    }

    public Integer getPoint()
    {
        return point;
    }

    public void setPoint(Integer point)
    {
        this.point = point;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "UserInfoBusinEntity [userId=" + userId + ", mobile=" + mobile + ", userName=" + userName + ", nickName="
                + nickName + ", avatar=" + avatar + ", payPassword=" + payPassword + ", openId=" + openId
                + ", identityCard=" + identityCard + ", realName=" + realName + ", birthday=" + birthday + ", sex="
                + sex + ", balance=" + balance + ", saveMoney=" + saveMoney + ", point=" + point + ", city=" + city
                + ", status=" + status + ", isUsePassword=" + isUsePassword + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt + "]";
    }

    public Integer getUncommNumber()
    {
        return uncommNumber;
    }

    public void setUncommNumber(Integer uncommNumber)
    {
        this.uncommNumber = uncommNumber;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getRedPacketNumber()
    {
        return redPacketNumber;
    }

    public void setRedPacketNumber(Integer redPacketNumber)
    {
        this.redPacketNumber = redPacketNumber;
    }

    public String getRechargeRate()
    {
        return rechargeRate;
    }

    public void setRechargeRate(String rechargeRate)
    {
        this.rechargeRate = rechargeRate;
    }

    public Integer getGroundFlag()
    {
        return groundFlag;
    }

    public void setGroundFlag(Integer groundFlag)
    {
        this.groundFlag = groundFlag;
    }

    public String getShopNumber()
    {
        return shopNumber;
    }

    public void setShopNumber(String shopNumber)
    {
        this.shopNumber = shopNumber;
    }

    public String getTotalOrderMoney()
    {
        return totalOrderMoney;
    }

    public void setTotalOrderMoney(String totalOrderMoney)
    {
        this.totalOrderMoney = totalOrderMoney;
    }

    public String getFirstShopName()
    {
        return firstShopName;
    }

    public void setFirstShopName(String firstShopName)
    {
        this.firstShopName = firstShopName;
    }

    public String getFavoriteShopName()
    {
        return favoriteShopName;
    }

    public void setFavoriteShopName(String favoriteShopName)
    {
        this.favoriteShopName = favoriteShopName;
    }

    public String getRechargeFee()
    {
        return rechargeFee;
    }

    public void setRechargeFee(String rechargeFee)
    {
        this.rechargeFee = rechargeFee;
    }

    public String getDoubleFee()
    {
        return doubleFee;
    }

    public void setDoubleFee(String doubleFee)
    {
        this.doubleFee = doubleFee;
    }

    public Integer getRowNum()
    {
        return rowNum;
    }

    public void setRowNum(Integer rowNum)
    {
        this.rowNum = rowNum;
    }

    public Integer getAllUserNumber()
    {
        return allUserNumber;
    }

    public void setAllUserNumber(Integer allUserNumber)
    {
        this.allUserNumber = allUserNumber;
    }

    public String getFirstShopTime()
    {
        return firstShopTime;
    }

    public void setFirstShopTime(String firstShopTime)
    {
        this.firstShopTime = firstShopTime;
    }

    public String getConsumeIndex()
    {
        return consumeIndex;
    }

    public void setConsumeIndex(String consumeIndex)
    {
        this.consumeIndex = consumeIndex;
    }

}