package com.itonghui.biz.pdafilehandle.dao;

import com.itonghui.biz.pdafilehandle.model.PdaFileHandleMo;
import com.itonghui.framework.common.AutoPage;
import java.util.List;
import java.util.Map;

/**
 * @Copyright (c) by anhui tonghui information technology Co., Ltd.
 * @All right reserved.
 * @Create Date: 2018/6/27/0027 15:47
 * @Create Author: jiangruliang@finscm.com
 * @File Name: PdaStorageApplicationMapper:pda入库mapper+pda出库mapper
 * @Last version: 1.0
 * @Function:
 * @Last Update Date:
 * @Change Log:
 */
public interface PdaStorageApplicationMapper {

  int insertDJRYBatches(List<PdaFileHandleMo> list);

  int insertDJGBBatches(List<PdaFileHandleMo> list);

  int insertDJXHBatches(List<PdaFileHandleMo> list);

  int insertDJEBBatches(List<PdaFileHandleMo> list);

  int insertDJCYBatches(List<PdaFileHandleMo> list);

  int insertTHCYBatches(List<PdaFileHandleMo> list);

  int insertTHEBBatches(List<PdaFileHandleMo> list);

  int insertTHGBBatches(List<PdaFileHandleMo> list);

  int insertTHPGBatches(List<PdaFileHandleMo> list);

  int insertTHRYBatches(List<PdaFileHandleMo> list);

  int selectCountByCode(Map<String, Object> map);

  int selectOutCountByCode(Map<String, Object> map);

  int updateByCode(Map<String, Object> map);

  int updateOutByCode(Map<String, Object> map);

  List<PdaFileHandleMo> selectByOutWarehouseNum(AutoPage<?> page);

  List<PdaFileHandleMo> selectDetailByOutWarehouseNum(Map<String, Object> map);

  List<PdaFileHandleMo> selectByReceiptNumber(AutoPage<?> page);

  List<PdaFileHandleMo> selectDetailByReceiptNumber(Map<String, Object> map);

  /**
   * 查询当日出库净重
   *
   * @Author:jiangruliang@finscm.com
   * @date: 2018/7/25/0025 16:22
   */
  double selectCountDetailByOutWarehouseNum(Map<String, Object> map);

  /**
   * 查询当日入库净重
   *
   * @Author:jiangruliang@finscm.com
   * @param map
   * @date: 2018/7/25/0025 16:22
   * @return double
   */
  double selectCountDetailByReceiptNumber(Map<String, Object> map);

  /**
   * 查询是否该出库单号存在数据库
   *
   * @Author:jiangruliang@finscm.com
   * @param map
   * @date: 2018/7/25/0025 16:22
   * @return int
   */
  int selectWhetherHaveOutNumByBarCode(Map<String, Object> map);

  /**
   * 查询是否该入库单号存在数据库
   *
   * @Author:jiangruliang@finscm.com
   * @param map
   * @date: 2018/7/25/0025 16:22
   * @return int
   */
  int selectWhetherHaveIntNumByBarCode(Map<String, Object> map);

}