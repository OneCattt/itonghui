package com.fbw.service.business.user.entity;

/**
 * 
 * <功能详细描述>权限实体
 * @author FBW0115
 * @version [版本号, 2017年9月13日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AuthReqEntity
{

    /**
     * 商户id
     */
    private String shopId;

    /**
     * 商户名称
     */
    private String shopName;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 主账号号码
     */
    private String parentMobile;

    /**
     * 子账号号码
     */
    private String sonMobile;

    /**
     * 短信验证码
     */
    private String code;

    /**
     * 买单收银
     */
    private String payCashier;

    /**
     * 设置翻倍日及ktv/酒店夜场专场
     */
    private String editEvent;

    /**
     * 订单管理
     */
    private String orderManager;

    /**
     * 评价管理
     */
    private String commentManager;

    /**
     * 财务结款
     */
    private String moneyManager;

    /**
     * 查看翻倍日
     */
    private String eventCalendar;

    /**
     * 地推管理
     */
    private String groundManager;

    /**
     * 专属二维码
     */
    private String exclusiveQR;

    /**
     * 授权管理
     */
    private String admin;

    /**
     * 跟踪id
     */
    private String trackId;

    public String getShopId()
    {
        return shopId;
    }

    public void setShopId(String shopId)
    {
        this.shopId = shopId;
    }

    public String getShopName()
    {
        return shopName;
    }

    public void setShopName(String shopName)
    {
        this.shopName = shopName;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getRoleId()
    {
        return roleId;
    }

    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }

    public String getParentMobile()
    {
        return parentMobile;
    }

    public void setParentMobile(String parentMobile)
    {
        this.parentMobile = parentMobile;
    }

    public String getSonMobile()
    {
        return sonMobile;
    }

    public void setSonMobile(String sonMobile)
    {
        this.sonMobile = sonMobile;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getPayCashier()
    {
        return payCashier;
    }

    public void setPayCashier(String payCashier)
    {
        this.payCashier = payCashier;
    }

    public String getEditEvent()
    {
        return editEvent;
    }

    public void setEditEvent(String editEvent)
    {
        this.editEvent = editEvent;
    }

    public String getOrderManager()
    {
        return orderManager;
    }

    public void setOrderManager(String orderManager)
    {
        this.orderManager = orderManager;
    }

    public String getCommentManager()
    {
        return commentManager;
    }

    public void setCommentManager(String commentManager)
    {
        this.commentManager = commentManager;
    }

    public String getMoneyManager()
    {
        return moneyManager;
    }

    public void setMoneyManager(String moneyManager)
    {
        this.moneyManager = moneyManager;
    }

    public String getEventCalendar()
    {
        return eventCalendar;
    }

    public void setEventCalendar(String eventCalendar)
    {
        this.eventCalendar = eventCalendar;
    }

    public String getGroundManager()
    {
        return groundManager;
    }

    public void setGroundManager(String groundManager)
    {
        this.groundManager = groundManager;
    }

    public String getExclusiveQR()
    {
        return exclusiveQR;
    }

    public void setExclusiveQR(String exclusiveQR)
    {
        this.exclusiveQR = exclusiveQR;
    }

    public String getAdmin()
    {
        return admin;
    }

    public void setAdmin(String admin)
    {
        this.admin = admin;
    }

    public String getTrackId()
    {
        return trackId;
    }

    public void setTrackId(String trackId)
    {
        this.trackId = trackId;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("AuthReqEntity [shopId=").append(shopId).append(", shopName=").append(shopName)
                .append(", userId=").append(userId).append(", roleId=").append(roleId).append(", parentMobile=")
                .append(parentMobile).append(", sonMobile=").append(sonMobile).append(", code=").append(code)
                .append(", payCashier=").append(payCashier).append(", editEvent=").append(editEvent)
                .append(", orderManager=").append(orderManager).append(", commentManager=").append(commentManager)
                .append(", moneyManager=").append(moneyManager).append(", eventCalendar=").append(eventCalendar)
                .append(", groundManager=").append(groundManager).append(", exclusiveQR=").append(exclusiveQR)
                .append(", admin=").append(admin).append(", trackId=").append(trackId).append("]");
        return builder.toString();
    }

}
