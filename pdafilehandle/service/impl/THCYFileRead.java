package com.itonghui.biz.pdafilehandle.service.impl;

import com.itonghui.util.DateUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itonghui.biz.outWarehouse.dao.OutWarehouseMapper;
import com.itonghui.biz.outWarehouse.model.OutWarehouse;
import com.itonghui.biz.outWarehouse.service.OutWarehouseService;
import com.itonghui.biz.outWarehouse.vo.OutWarehouseVo;
import com.itonghui.biz.pdafilehandle.config.PdaConfig;
import com.itonghui.biz.pdafilehandle.dao.PdaStorageApplicationMapper;
import com.itonghui.biz.pdafilehandle.model.PdaFileHandleMo;
import com.itonghui.biz.pdafilehandle.service.FileRead;
import com.itonghui.biz.pdafilehandle.service.FileReadWay;
import com.itonghui.framework.common.BeanCopy;
import com.itonghui.framework.exception.BusinessException;
import com.itonghui.framework.spring.SpringApplicationContextUtil;
import com.itonghui.framework.util.ObjectUtils;

/**
 * @Copyright (c) by anhui tonghui information technology Co., Ltd.
 * @All right reserved.
 * @Create Date: 2018/6/27/0027 15:40
 * @Create Author: jiangruliang@finscm.com
 * @File Name: DJCYFileRead：车辆提货：车辆出园txt读取
 * @Last version: 1.0
 * @Function:
 * @Last Update Date:
 * @Change Log:
 */
@Service("THCYFileRead")
public class THCYFileRead implements FileReadWay {
  private static final String SYS_NO = "b2b2c biz THCYFileRead";

  /**
   * 保存txt内容到数据库
   *
   * @Author:jiangruliang@finscm.com
   * @date: 2018/6/27/0027 13:56
   */
  @Override
  public int saveTxt(File file) {
    PdaStorageApplicationMapper pdaStorageApplicationMapper = SpringApplicationContextUtil
        .getBean(PdaStorageApplicationMapper.class);
    PdaConfig config = SpringApplicationContextUtil.getBean(PdaConfig.class);
    BufferedReader reader = null;
    try {
      InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf-8");
      reader = new BufferedReader(read);
      List<PdaFileHandleMo> pdaFileHandleMos = new ArrayList<>();
      String temp;
      while ((temp = reader.readLine()) != null) {
        String[] list = temp.split(" ");
        // 判断单号是否存在
        Map<String, Object> map = new HashMap<>();
        map.put("barCode",list[0]);
        if (pdaStorageApplicationMapper.selectWhetherHaveOutNumByBarCode(map)>0){
          PdaFileHandleMo pdaFileHandleMo = new PdaFileHandleMo();
          pdaFileHandleMo.setBarcode(list[0]);
          pdaFileHandleMo.setCarNumber(list[1]);
          pdaFileHandleMo.setCode(list[2]);
          pdaFileHandleMo.setPdaDate(DateUtil.string2Date(list[3], "yyyy-MM-ddHH:mm:ss"));
          pdaFileHandleMos.add(pdaFileHandleMo);
        }

      }
      if (pdaFileHandleMos.size() > 0) {
        int flag=pdaStorageApplicationMapper.insertTHCYBatches(pdaFileHandleMos);
        // 查询当前子单号为当前code的对应的 入库/出库单号 修改入库/出库状态
        if (flag != pdaFileHandleMos.size()) {
          throw new BusinessException(SYS_NO + " 0002", "批量插入数据失败");
        }
        commonFileDeal(pdaStorageApplicationMapper, pdaFileHandleMos, SYS_NO);

      }
      reader.close();
      File file1 = new File(config.usedData + "/" + file.getName());
      //剪切已插入数据库文件到新文件夹
      FileRead.copyAndDelOld(file, file1);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    return 1;
  }

  /**
   * 出库数据处理
   *
   * @Author:jiangruliang@finscm.com
   * @date: 2018/7/11/0011 14:24
   */
  static void basicDataDeal(PdaStorageApplicationMapper pdaStorageApplicationMapper,
      OutWarehouseMapper outWarehouseMapper, List<PdaFileHandleMo> pdaFileHandleMos,
      String sysNo) {
    OutWarehouseService outWarehouseService = SpringApplicationContextUtil
        .getBean(OutWarehouseService.class);
    int flag;
    for (PdaFileHandleMo mo : pdaFileHandleMos) {
      Map<String, Object> map = new HashMap<>();
      //说明当前子单号已完成，修改入库/出库状态
        //获取当前子单号入库货物重量
        map.put("deliveryedNum", mo.getNetWeight());
        map.put("outWarehouseNum", mo.getBarcode());
        flag = outWarehouseMapper.updateByOutWarehouseNum(map);
        if (flag != 1) {
          throw new BusinessException(sysNo + " 0003", "更新已出库数量失败");
        }
        OutWarehouse outWarehouse = outWarehouseMapper
            .selectByOutWarehouseNum(mo.getBarcode());
        if (ObjectUtils.isNullObj(outWarehouse)) {
          throw new BusinessException(sysNo + " 0004", "该出库单号未查出数据");
        }
          OutWarehouseVo outWarehouseVo=BeanCopy.object2Object(outWarehouse,OutWarehouseVo.class);
          outWarehouseVo.setWeight(mo.getNetWeight());
          outWarehouseService.updateWareNumber(outWarehouseVo);
    }
  }

  static void commonFileDeal(PdaStorageApplicationMapper pdaStorageApplicationMapper,
      List<PdaFileHandleMo> pdaFileHandleMos, String sysNo) {
    int flag;
    for (PdaFileHandleMo mo : pdaFileHandleMos) {
      Map<String, Object> map = new HashMap<>();
      map.put("code", mo.getCode());
      map.put("barcode", mo.getBarcode());
      flag = pdaStorageApplicationMapper.selectOutCountByCode(map);
      if (flag == 5) {
        //说明当前子单号已完成，修改
        flag = pdaStorageApplicationMapper.updateOutByCode(map);
        if (flag <0) {
          throw new BusinessException(sysNo + " 0006", "更新PDA已处理状态失败");
        }
      }
    }
  }
}
