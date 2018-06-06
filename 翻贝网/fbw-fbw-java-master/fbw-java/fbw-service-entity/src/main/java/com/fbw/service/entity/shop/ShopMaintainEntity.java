package com.fbw.service.entity.shop;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * <功能详细描述>商家维护实体
 * @author FBW0115
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class ShopMaintainEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 商户id
     */
    private Integer shopId;

    /**
     * 系统维护人id
     */
    private Integer maintainerId;

    /**
     * 负责人账号
     */
    private String parentMobile;

    /**
     * 负责人姓名
     */
    private String parentName;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 持卡人
     */
    private String cardHolder;

    /**
     * 所属银行
     */
    private String bankName;

    /**
     * 银行卡号
     */
    private String bankCard;

    /**
     * 开户行
     */
    private String openCardBank;

    /**
     * 银行卡所属省
     */
    private String bankProvince;

    /**
     * 银行卡所属市
     */
    private String bankCity;

    /**
     * 银行卡正面照
     */
    private String bankCardPhoto;

    /**
     * 营业执照照片
     */
    private String licensePhoto;

    /**
     * 身份证正面照
     */
    private String idcardFrontPhoto;

    /**
     * 身份证背面照
     */
    private String idcardBackPhoto;

    /**
     * 子账号
     */
    private String childMobile;

    /**
     * 操作人ID
     */
    private Integer adminId;

    /**
     * 合约开始时间
     */
    private String contractStartTime;

    /**
     * 合约接着时间
     */
    private String contractEndTime;

    /**
     * 合同图片
     */
    private String contractPhotoList;

    /**
     * 创建时间
     */
    private String createdAt;

    /**
     * 更新时间
     */
    private String updatedAt;

    private Integer trackId;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getShopId()
    {
        return shopId;
    }

    public void setShopId(Integer shopId)
    {
        this.shopId = shopId;
    }

    public Integer getMaintainerId()
    {
        return maintainerId;
    }

    public void setMaintainerId(Integer maintainerId)
    {
        this.maintainerId = maintainerId;
    }

    public String getParentMobile()
    {
        return parentMobile;
    }

    public void setParentMobile(String parentMobile)
    {
        this.parentMobile = parentMobile;
    }

    public String getParentName()
    {
        return parentName;
    }

    public void setParentName(String parentName)
    {
        this.parentName = parentName;
    }

    public String getIdCard()
    {
        return idCard;
    }

    public void setIdCard(String idCard)
    {
        this.idCard = idCard;
    }

    public String getCardHolder()
    {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder)
    {
        this.cardHolder = cardHolder;
    }

    public String getBankName()
    {
        return bankName;
    }

    public void setBankName(String bankName)
    {
        this.bankName = bankName;
    }

    public String getBankCard()
    {
        return bankCard;
    }

    public void setBankCard(String bankCard)
    {
        this.bankCard = bankCard;
    }

    public String getOpenCardBank()
    {
        return openCardBank;
    }

    public void setOpenCardBank(String openCardBank)
    {
        this.openCardBank = openCardBank;
    }

    public String getBankProvince()
    {
        return bankProvince;
    }

    public void setBankProvince(String bankProvince)
    {
        this.bankProvince = bankProvince;
    }

    public String getBankCity()
    {
        return bankCity;
    }

    public void setBankCity(String bankCity)
    {
        this.bankCity = bankCity;
    }

    public String getBankCardPhoto()
    {
        return bankCardPhoto;
    }

    public void setBankCardPhoto(String bankCardPhoto)
    {
        this.bankCardPhoto = bankCardPhoto;
    }

    public String getLicensePhoto()
    {
        return licensePhoto;
    }

    public void setLicensePhoto(String licensePhoto)
    {
        this.licensePhoto = licensePhoto;
    }

    public String getIdcardFrontPhoto()
    {
        return idcardFrontPhoto;
    }

    public void setIdcardFrontPhoto(String idcardFrontPhoto)
    {
        this.idcardFrontPhoto = idcardFrontPhoto;
    }

    public String getIdcardBackPhoto()
    {
        return idcardBackPhoto;
    }

    public void setIdcardBackPhoto(String idcardBackPhoto)
    {
        this.idcardBackPhoto = idcardBackPhoto;
    }

    public String getChildMobile()
    {
        return childMobile;
    }

    public void setChildMobile(String childMobile)
    {
        this.childMobile = childMobile;
    }

    public Integer getAdminId()
    {
        return adminId;
    }

    public void setAdminId(Integer adminId)
    {
        this.adminId = adminId;
    }

    public String getContractStartTime()
    {
        return contractStartTime;
    }

    public void setContractStartTime(String contractStartTime)
    {
        this.contractStartTime = contractStartTime;
    }

    public String getContractEndTime()
    {
        return contractEndTime;
    }

    public void setContractEndTime(String contractEndTime)
    {
        this.contractEndTime = contractEndTime;
    }

    public String getContractPhotoList()
    {
        return contractPhotoList;
    }

    public void setContractPhotoList(String contractPhotoList)
    {
        this.contractPhotoList = contractPhotoList;
    }

    public String getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(String createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public Integer getTrackId()
    {
        return trackId;
    }

    public void setTrackId(Integer trackId)
    {
        this.trackId = trackId;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("ShopsMaintainEntity [id=").append(id).append(", shopId=").append(shopId)
                .append(", maintainerId=").append(maintainerId).append(", parentMobile=").append(parentMobile)
                .append(", parentName=").append(parentName).append(", idCard=").append(idCard).append(", cardHolder=")
                .append(cardHolder).append(", bankName=").append(bankName).append(", bankCard=").append(bankCard)
                .append(", openCardBank=").append(openCardBank).append(", bankProvince=").append(bankProvince)
                .append(", bankCity=").append(bankCity).append(", bankCardPhoto=").append(bankCardPhoto)
                .append(", licensePhoto=").append(licensePhoto).append(", idcardFrontPhoto=").append(idcardFrontPhoto)
                .append(", idcardBackPhoto=").append(idcardBackPhoto).append(", childMobile=").append(childMobile)
                .append(", adminId=").append(adminId).append(", contractStartTime=").append(contractStartTime)
                .append(", contractEndTime=").append(contractEndTime).append(", contractPhotoList=")
                .append(contractPhotoList).append(", createdAt=").append(createdAt).append(", updatedAt=")
                .append(updatedAt).append(", trackId=").append(trackId).append("]");
        return builder.toString();
    }

}