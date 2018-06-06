package com.fbw.service.entity.ground;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 地推注册用户详细表 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月6日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_ground_register_user_detail_busin")
@JsonInclude(Include.NON_NULL)
public class GroundRegisterUserDetailBusinEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    /**
     * 城市id
     */
    @Column(name = "city_id")
    private String cityId;

    /**
     * 商户地推店员id
     */
    @Column(name = "shop_assistant_id")
    private String shopAssistantId;

    /**
     * 商户id
     */
    @Column(name = "shop_id")
    private String shopId;

    /**
     * 个人地推id
     */
    @Column(name = "salesman_id")
    private String salesmanId;

    /**
     * 注册来源
     */
    @Column(name = "register_source")
    private String registerSource;

    /**
     * 注册手机号
     */
    @Column(name = "register_mobile")
    private String registerMobile;

    /**
     * 订单数量
     */
    @Column(name = "order_amount")
    private Integer orderAmount;

    /**
     * 地推订单数量
     */
    @Column(name = "ground_order_amount")
    private Integer groundOrderAmount;

    /**
     * 地推类型1：个人地推 2：商家地推
     */
    @Column(name = "ground_type")
    private String groundType;

    /**
     * 地推订单实付
     */
    @Column(name = "ground_order_activity_fee")
    private BigDecimal groundOrderActivityFee;

    /**
     * 注册时间
     */
    @Column(name = "register_date")
    private String registerDate;

    @Column(name = "register_status")
    private String registerStatus;

    /**
     * 地推id
     */
    @Column(name = "history_ground_id")
    private String historyGroundId;

    /**
     * 注册用户数
     */
    private Integer userNumber;

    /**
     * 有效注册用户数
     */
    private Integer validUserNumber;

    public String getHistoryGroundId()
    {
        return historyGroundId;
    }

    public void setHistoryGroundId(String historyGroundId)
    {
        this.historyGroundId = historyGroundId;
    }

    public String getRegisterStatus()
    {
        return registerStatus;
    }

    public void setRegisterStatus(String registerStatus)
    {
        this.registerStatus = registerStatus;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getCityId()
    {
        return cityId;
    }

    public void setCityId(String cityId)
    {
        this.cityId = cityId;
    }

    public String getShopAssistantId()
    {
        return shopAssistantId;
    }

    public void setShopAssistantId(String shopAssistantId)
    {
        this.shopAssistantId = shopAssistantId;
    }

    public String getShopId()
    {
        return shopId;
    }

    public void setShopId(String shopId)
    {
        this.shopId = shopId;
    }

    public String getSalesmanId()
    {
        return salesmanId;
    }

    public void setSalesmanId(String salesmanId)
    {
        this.salesmanId = salesmanId;
    }

    public String getRegisterSource()
    {
        return registerSource;
    }

    public void setRegisterSource(String registerSource)
    {
        this.registerSource = registerSource;
    }

    public String getRegisterMobile()
    {
        return registerMobile;
    }

    public void setRegisterMobile(String registerMobile)
    {
        this.registerMobile = registerMobile;
    }

    public Integer getOrderAmount()
    {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount)
    {
        this.orderAmount = orderAmount;
    }

    public Integer getGroundOrderAmount()
    {
        return groundOrderAmount;
    }

    public void setGroundOrderAmount(Integer groundOrderAmount)
    {
        this.groundOrderAmount = groundOrderAmount;
    }

    public String getGroundType()
    {
        return groundType;
    }

    public void setGroundType(String groundType)
    {
        this.groundType = groundType;
    }

    public BigDecimal getGroundOrderActivityFee()
    {
        return groundOrderActivityFee;
    }

    public void setGroundOrderActivityFee(BigDecimal groundOrderActivityFee)
    {
        this.groundOrderActivityFee = groundOrderActivityFee;
    }

    public String getRegisterDate()
    {
        return registerDate;
    }

    public void setRegisterDate(String registerDate)
    {
        this.registerDate = registerDate;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cityId=").append(cityId);
        sb.append(", shopAssistantId=").append(shopAssistantId);
        sb.append(", shopId=").append(shopId);
        sb.append(", salesmanId=").append(salesmanId);
        sb.append(", registerSource=").append(registerSource);
        sb.append(", registerMobile=").append(registerMobile);
        sb.append(", orderAmount=").append(orderAmount);
        sb.append(", groundOrderAmount=").append(groundOrderAmount);
        sb.append(", groundType=").append(groundType);
        sb.append(", groundOrderActivityFee=").append(groundOrderActivityFee);
        sb.append(", registerDate=").append(registerDate);
        sb.append(", registerStatus=").append(registerStatus);
        sb.append(", historyGroundId=").append(historyGroundId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Integer getUserNumber()
    {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber)
    {
        this.userNumber = userNumber;
    }

    public Integer getValidUserNumber()
    {
        return validUserNumber;
    }

    public void setValidUserNumber(Integer validUserNumber)
    {
        this.validUserNumber = validUserNumber;
    }
}