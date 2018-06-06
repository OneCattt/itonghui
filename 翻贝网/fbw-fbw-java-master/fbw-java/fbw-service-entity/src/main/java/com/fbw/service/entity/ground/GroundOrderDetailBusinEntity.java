package com.fbw.service.entity.ground;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 地推人员订单业务表 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月6日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_ground_order_detail_busin")
@JsonInclude(Include.NON_NULL)
public class GroundOrderDetailBusinEntity implements Serializable
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
     * 地推人员id
     */
    @Column(name = "group_salesman_id")
    private String groupSalesmanId;

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
     * 商户名称
     */
    @Column(name = "shop_name")
    private String shopName;

    /**
     * 商户名称
     */
    @Column(name = "shop_code")
    private String shopCode;

    /**
     * 注册手机号
     */
    @Column(name = "register_mobile")
    private String registerMobile;

    /**
     * 订单号
     */
    @Column(name = "order_number")
    private String orderNumber;

    /**
     * 地推收入
     */
    @Column(name = "ground_total_fee")
    private BigDecimal groundTotalFee;

    /**
     * 金额
     */
    @Column(name = "money")
    private BigDecimal money;

    /**
     * 地推类型1：个人地推 2：商家地推
     */
    @Column(name = "ground_type")
    private String groundType;

    /**
     * 地推订单状态 (1：充值，2：订单)
     */
    @Column(name = "ground_order_type")
    private String groundOrderType;

    /**
     * 创建时间
     */
    @Column(name = "created_date")
    private Date createdDate;

    /**
     * 地推id
     */
    @Column(name = "history_ground_id")
    private String historyGroundId;

    public String getHistoryGroundId()
    {
        return historyGroundId;
    }

    public void setHistoryGroundId(String historyGroundId)
    {
        this.historyGroundId = historyGroundId;
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

    public String getGroupSalesmanId()
    {
        return groupSalesmanId;
    }

    public void setGroupSalesmanId(String groupSalesmanId)
    {
        this.groupSalesmanId = groupSalesmanId;
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

    public String getShopName()
    {
        return shopName;
    }

    public void setShopName(String shopName)
    {
        this.shopName = shopName;
    }

    public String getRegisterMobile()
    {
        return registerMobile;
    }

    public void setRegisterMobile(String registerMobile)
    {
        this.registerMobile = registerMobile;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getGroundTotalFee()
    {
        return groundTotalFee;
    }

    public void setGroundTotalFee(BigDecimal groundTotalFee)
    {
        this.groundTotalFee = groundTotalFee;
    }

    public BigDecimal getMoney()
    {
        return money;
    }

    public void setMoney(BigDecimal money)
    {
        this.money = money;
    }

    public String getGroundType()
    {
        return groundType;
    }

    public void setGroundType(String groundType)
    {
        this.groundType = groundType;
    }

    public String getGroundOrderType()
    {
        return groundOrderType;
    }

    public void setGroundOrderType(String groundOrderType)
    {
        this.groundOrderType = groundOrderType;
    }

    public Date getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
    }

    public String getShopCode()
    {
        return shopCode;
    }

    public void setShopCode(String shopCode)
    {
        this.shopCode = shopCode;
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
        sb.append(", groupSalesmanId=").append(groupSalesmanId);
        sb.append(", shopAssistantId=").append(shopAssistantId);
        sb.append(", shopId=").append(shopId);
        sb.append(", shopName=").append(shopName);
        sb.append(", shopCode=").append(shopCode);
        sb.append(", registerMobile=").append(registerMobile);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", groundTotalFee=").append(groundTotalFee);
        sb.append(", money=").append(money);
        sb.append(", groundType=").append(groundType);
        sb.append(", groundOrderType=").append(groundOrderType);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", historyGroundId=").append(historyGroundId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}