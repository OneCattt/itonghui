package com.itonghui.biz.pdafilehandle.model;

import java.util.Date;
import java.util.List;

/**
 * @Copyright (c) by anhui tonghui information technology Co., Ltd.
 * @All right reserved.
 * @Create Date: 2018/6/27/0027 14:01
 * @Create Author: jiangruliang@finscm.com
 * @File Name: PdaFileHandleMo：车辆送货：车辆出园Mo类
 * @Last version: 1.0
 * @Function:
 * @Last Update Date:
 * @Change Log:
 */
public class PdaFileHandleMo {


  private String barcode;//送货单号,type=1（入库单二维码）
  private String carNumber;//车牌号
  private String code;//子单号：一个送货单对应多个子单号，每辆车的每个货物上都有子单号
  private Date pdaDate;//当前时间,type=3（卸货时间）
  private Double weight;//毛重量
  private Double mass;//品味（质量）
  private String barnNumber;//仓号
  private String goods;//货物品名
  private Integer pdaType;//入库类型（1：车辆入园，2：满载过磅，3：入库卸货，4：空载过磅，5：车辆出园）
  private Integer whetherDeal;//该数据是否已处理（0：未处理，1：已处理）
  private Double emWeight;//空重/皮重
  private Double netWeight;//净重
  private String containerNo;//集装箱号
  private List<PdaFileHandleMo> list;
  private double totalWeight;

  public List<PdaFileHandleMo> getList() {
    return list;
  }

  public void setList(List<PdaFileHandleMo> list) {
    this.list = list;
  }

  public double getTotalWeight() {
    return totalWeight;
  }

  public void setTotalWeight(double totalWeight) {
    this.totalWeight = totalWeight;
  }

  public Double getEmWeight() {
    return emWeight;
  }

  public void setEmWeight(Double emWeight) {
    this.emWeight = emWeight;
  }

  public Double getNetWeight() {
    return netWeight;
  }

  public void setNetWeight(Double netWeight) {
    this.netWeight = netWeight;
  }

  public String getContainerNo() {
    return containerNo;
  }

  public void setContainerNo(String containerNo) {
    this.containerNo = containerNo;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getBarcode() {
    return barcode;
  }

  public void setBarcode(String barcode) {
    this.barcode = barcode;
  }

  public String getCarNumber() {
    return carNumber;
  }

  public void setCarNumber(String carNumber) {
    this.carNumber = carNumber;
  }


  public Double getWeight() {
    return weight;
  }

  public void setWeight(Double weight) {
    this.weight = weight;
  }

  public Double getMass() {
    return mass;
  }

  public void setMass(Double mass) {
    this.mass = mass;
  }

  public String getBarnNumber() {
    return barnNumber;
  }

  public void setBarnNumber(String barnNumber) {
    this.barnNumber = barnNumber;
  }

  public String getGoods() {
    return goods;
  }

  public void setGoods(String goods) {
    this.goods = goods;
  }

  public Date getPdaDate() {
    return pdaDate;
  }

  public void setPdaDate(Date pdaDate) {
    this.pdaDate = pdaDate;
  }

  public Integer getPdaType() {
    return pdaType;
  }

  public void setPdaType(Integer pdaType) {
    this.pdaType = pdaType;
  }

  public Integer getWhetherDeal() {
    return whetherDeal;
  }

  public void setWhetherDeal(Integer whetherDeal) {
    this.whetherDeal = whetherDeal;
  }
}
