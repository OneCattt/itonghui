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
 * 个人地推信息表 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月6日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_ground_personal_info_busin")
@JsonInclude(Include.NON_NULL)
public class GroundPersonalInfoBusinEntity implements Serializable
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
     * 个人地推id
     */
    @Column(name = "salesman_id")
    private String salesmanId;

    /**
     * 个人地推手机号
     */
    @Column(name = "salesman_mobile")
    private String salesmanMobile;

    /**
     * 个人地推姓名
     */
    @Column(name = "salesman_name")
    private String salesmanName;

    /**
     * 个人地推二维码
     */
    @Column(name = "salesman_qr_code")
    private String salesmanQrCode;

    /**
     * 有效注册用户数量
     */
    @Column(name = "valid_register_amount")
    private Integer validRegisterAmount;

    /**
     * 注册用户数量
     */
    @Column(name = "register_amount")
    private Integer registerAmount;

    /**
     * 有效充值数量
     */
    @Column(name = "valid_Recharge_amount")
    private Integer validRechargeAmount;

    /**
     * 有效订单数量
     */
    @Column(name = "valid_order_amount")
    private Integer validOrderAmount;

    /**
     * 地推注册收入
     */
    @Column(name = "ground_register_fee")
    private BigDecimal groundRegisterFee;

    /**
     * 地推其他收入
     */
    @Column(name = "ground_other_fee")
    private BigDecimal groundOtherFee;

    /**
     * 地推总体收入（元）
     */
    @Column(name = "ground_total_fee")
    private BigDecimal groundTotalFee;

    /**
     * 地推备注
     */
    @Column(name = "ground_remark")
    private String groundRemark;

    /**
     * 创建时间
     */
    @Column(name = "created_date")
    private String createdDate;

    /**
     * 创建时间
     */
    @Column(name = "status")
    private String status;

    /**
     * 注册时间
     */
    @Column(name = "register_fee")
    private BigDecimal registerFee;

    private Integer orderAmount;

    private BigDecimal groundOrderActivityFee;

    private static final long serialVersionUID = 1L;

    public BigDecimal getRegisterFee()
    {
        return registerFee;
    }

    public void setRegisterFee(BigDecimal registerFee)
    {
        this.registerFee = registerFee;
    }

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

    public String getSalesmanId()
    {
        return salesmanId;
    }

    public void setSalesmanId(String salesmanId)
    {
        this.salesmanId = salesmanId;
    }

    public String getSalesmanMobile()
    {
        return salesmanMobile;
    }

    public void setSalesmanMobile(String salesmanMobile)
    {
        this.salesmanMobile = salesmanMobile;
    }

    public String getSalesmanName()
    {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName)
    {
        this.salesmanName = salesmanName;
    }

    public String getSalesmanQrCode()
    {
        return salesmanQrCode;
    }

    public void setSalesmanQrCode(String salesmanQrCode)
    {
        this.salesmanQrCode = salesmanQrCode;
    }

    public Integer getValidRegisterAmount()
    {
        return validRegisterAmount;
    }

    public void setValidRegisterAmount(Integer validRegisterAmount)
    {
        this.validRegisterAmount = validRegisterAmount;
    }

    public Integer getRegisterAmount()
    {
        return registerAmount;
    }

    public void setRegisterAmount(Integer registerAmount)
    {
        this.registerAmount = registerAmount;
    }

    public BigDecimal getGroundOtherFee()
    {
        return groundOtherFee;
    }

    public void setGroundOtherFee(BigDecimal groundOtherFee)
    {
        this.groundOtherFee = groundOtherFee;
    }

    public BigDecimal getGroundTotalFee()
    {
        return groundTotalFee;
    }

    public void setGroundTotalFee(BigDecimal groundTotalFee)
    {
        this.groundTotalFee = groundTotalFee;
    }

    public String getGroundRemark()
    {
        return groundRemark;
    }

    public void setGroundRemark(String groundRemark)
    {
        this.groundRemark = groundRemark;
    }

    public String getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(String createdDate)
    {
        this.createdDate = createdDate;
    }

    public Integer getValidRechargeAmount()
    {
        return validRechargeAmount;
    }

    public void setValidRechargeAmount(Integer validRechargeAmount)
    {
        this.validRechargeAmount = validRechargeAmount;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Integer getValidOrderAmount()
    {
        return validOrderAmount;
    }

    public void setValidOrderAmount(Integer validOrderAmount)
    {
        this.validOrderAmount = validOrderAmount;
    }

    public BigDecimal getGroundRegisterFee()
    {
        return groundRegisterFee;
    }

    public void setGroundRegisterFee(BigDecimal groundRegisterFee)
    {
        this.groundRegisterFee = groundRegisterFee;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("GroundPersonalInfoBusinEntity [id=").append(id).append(", cityId=").append(cityId)
                .append(", salesmanId=").append(salesmanId).append(", salesmanMobile=").append(salesmanMobile)
                .append(", salesmanName=").append(salesmanName).append(", salesmanQrCode=").append(salesmanQrCode)
                .append(", validRegisterAmount=").append(validRegisterAmount).append(", registerAmount=")
                .append(registerAmount).append(", validRechargeAmount=").append(validRechargeAmount)
                .append(", validOrderAmount=").append(validOrderAmount).append(", groundRegisterFee=")
                .append(groundRegisterFee).append(", groundOtherFee=").append(groundOtherFee)
                .append(", groundTotalFee=").append(groundTotalFee).append(", groundRemark=").append(groundRemark)
                .append(", createdDate=").append(createdDate).append(", status=").append(status)
                .append(", registerFee=").append(registerFee).append("]");
        return builder.toString();
    }

    public Integer getOrderAmount()
    {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount)
    {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getGroundOrderActivityFee()
    {
        return groundOrderActivityFee;
    }

    public void setGroundOrderActivityFee(BigDecimal groundOrderActivityFee)
    {
        this.groundOrderActivityFee = groundOrderActivityFee;
    }

}