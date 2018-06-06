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
 * 商家地推信息表 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月6日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_ground_shop_info_busin")
@JsonInclude(Include.NON_NULL)
public class GroundShopInfoBusinEntity implements Serializable
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
     * 商户id
     */
    @Column(name = "shop_id")
    private String shopId;

    /**
     * 商户代码
     */
    @Column(name = "shop_code")
    private String shopCode;

    /**
     * 商家地推类型 1:普通地推 2：合伙人地推
     */
    @Column(name = "shop_ground_type")
    private String shopGroundType;

    /**
     * 商户登记账号
     */
    @Column(name = "register_shop_account")
    private String registerShopAccount;

    /**
     * 商户地址
     */
    @Column(name = "shop_address")
    private String shopAddress;

    /**
     * 商户名称
     */
    @Column(name = "shop_name")
    private String shopName;

    /**
     * 商户商务人员id
     */
    @Column(name = "shop_salesman_id")
    private String shopSalesmanId;

    /**
     * 商户商务人员姓名
     */
    @Column(name = "shop_salesman_name")
    private String shopSalesmanName;

    /**
     * 商家地推二维码
     */
    @Column(name = "shop_qr_code")
    private String shopQrCode;

    /**
     * 注册用户数量
     */
    @Column(name = "register_amount")
    private Integer registerAmount;

    /**
     * 有效注册用户数量
     */
    @Column(name = "valid_register_amount")
    private Integer validRegisterAmount;

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
     * 有效订单数量
     */
    @Column(name = "vaild_order_amount")
    private Integer vaildOrderAmount;

    /**
     * 地推已提现金额
     */
    @Column(name = "ground_withdrawals_fee")
    private BigDecimal groundWithdrawalsFee;

    /**
     * 账户余额
     */
    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    /**
     * 状态
     */
    @Column(name = "status")
    private String status;

    /**
     * 创建时间
     */
    @Column(name = "created_date")
    private String createdDate;

    /**
     * 合约开始时间
     */
    @Column(name = "contract_start_date")
    private String contractStartDate;

    /**
     * 合约结束时间
     */
    @Column(name = "contract_end_date")
    private String contractEndDate;

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

    public String getShopId()
    {
        return shopId;
    }

    public void setShopId(String shopId)
    {
        this.shopId = shopId;
    }

    public String getShopGroundType()
    {
        return shopGroundType;
    }

    public void setShopGroundType(String shopGroundType)
    {
        this.shopGroundType = shopGroundType;
    }

    public String getRegisterShopAccount()
    {
        return registerShopAccount;
    }

    public void setRegisterShopAccount(String registerShopAccount)
    {
        this.registerShopAccount = registerShopAccount;
    }

    public String getShopAddress()
    {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress)
    {
        this.shopAddress = shopAddress;
    }

    public String getShopName()
    {
        return shopName;
    }

    public void setShopName(String shopName)
    {
        this.shopName = shopName;
    }

    public String getShopSalesmanId()
    {
        return shopSalesmanId;
    }

    public void setShopSalesmanId(String shopSalesmanId)
    {
        this.shopSalesmanId = shopSalesmanId;
    }

    public String getShopSalesmanName()
    {
        return shopSalesmanName;
    }

    public void setShopSalesmanName(String shopSalesmanName)
    {
        this.shopSalesmanName = shopSalesmanName;
    }

    public String getShopQrCode()
    {
        return shopQrCode;
    }

    public void setShopQrCode(String shopQrCode)
    {
        this.shopQrCode = shopQrCode;
    }

    public Integer getRegisterAmount()
    {
        return registerAmount;
    }

    public void setRegisterAmount(Integer registerAmount)
    {
        this.registerAmount = registerAmount;
    }

    public Integer getVaildOrderAmount()
    {
        return vaildOrderAmount;
    }

    public void setVaildOrderAmount(Integer vaildOrderAmount)
    {
        this.vaildOrderAmount = vaildOrderAmount;
    }

    public BigDecimal getGroundWithdrawalsFee()
    {
        return groundWithdrawalsFee;
    }

    public void setGroundWithdrawalsFee(BigDecimal groundWithdrawalsFee)
    {
        this.groundWithdrawalsFee = groundWithdrawalsFee;
    }

    public BigDecimal getAccountBalance()
    {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance)
    {
        this.accountBalance = accountBalance;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(String createdDate)
    {
        this.createdDate = createdDate;
    }

    public String getContractStartDate()
    {
        return contractStartDate;
    }

    public void setContractStartDate(String contractStartDate)
    {
        this.contractStartDate = contractStartDate;
    }

    public String getContractEndDate()
    {
        return contractEndDate;
    }

    public void setContractEndDate(String contractEndDate)
    {
        this.contractEndDate = contractEndDate;
    }

    public Integer getValidRegisterAmount()
    {
        return validRegisterAmount;
    }

    public void setValidRegisterAmount(Integer validRegisterAmount)
    {
        this.validRegisterAmount = validRegisterAmount;
    }

    public BigDecimal getGroundRegisterFee()
    {
        return groundRegisterFee;
    }

    public void setGroundRegisterFee(BigDecimal groundRegisterFee)
    {
        this.groundRegisterFee = groundRegisterFee;
    }

    public BigDecimal getGroundOtherFee()
    {
        return groundOtherFee;
    }

    public void setGroundOtherFee(BigDecimal groundOtherFee)
    {
        this.groundOtherFee = groundOtherFee;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("GroundShopInfoBusinEntity [id=").append(id).append(", cityId=").append(cityId)
                .append(", shopId=").append(shopId).append(", shopCode=").append(shopCode).append(", shopGroundType=")
                .append(shopGroundType).append(", registerShopAccount=").append(registerShopAccount)
                .append(", shopAddress=").append(shopAddress).append(", shopName=").append(shopName)
                .append(", shopSalesmanId=").append(shopSalesmanId).append(", shopSalesmanName=")
                .append(shopSalesmanName).append(", shopQrCode=").append(shopQrCode).append(", registerAmount=")
                .append(registerAmount).append(", validRegisterAmount=").append(validRegisterAmount)
                .append(", groundRegisterFee=").append(groundRegisterFee).append(", groundOtherFee=")
                .append(groundOtherFee).append(", vaildOrderAmount=").append(vaildOrderAmount)
                .append(", groundWithdrawalsFee=").append(groundWithdrawalsFee).append(", accountBalance=")
                .append(accountBalance).append(", status=").append(status).append(", createdDate=").append(createdDate)
                .append(", contractStartDate=").append(contractStartDate).append(", contractEndDate=")
                .append(contractEndDate).append("]");
        return builder.toString();
    }

    public String getShopCode()
    {
        return shopCode;
    }

    public void setShopCode(String shopCode)
    {
        this.shopCode = shopCode;
    }

}