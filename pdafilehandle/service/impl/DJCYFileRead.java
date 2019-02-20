package com.itonghui.biz.pdafilehandle.service.impl;

import com.itonghui.biz.matching.model.PositionCollect;
import com.itonghui.biz.matching.service.PositionTotalService;
import com.itonghui.biz.storageapplication.model.StorageApplication;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itonghui.biz.pdafilehandle.config.PdaConfig;
import com.itonghui.biz.pdafilehandle.dao.PdaStorageApplicationMapper;
import com.itonghui.biz.pdafilehandle.model.PdaFileHandleMo;
import com.itonghui.biz.pdafilehandle.service.FileRead;
import com.itonghui.biz.pdafilehandle.service.FileReadWay;
import com.itonghui.biz.storageapplication.dao.StorageApplicationMapper;
import com.itonghui.framework.exception.BusinessException;
import com.itonghui.framework.spring.SpringApplicationContextUtil;
import com.itonghui.framework.util.ObjectUtils;
import com.itonghui.util.DateUtil;

/**
 * @Copyright (c) by anhui tonghui information technology Co., Ltd.
 * @All right reserved.
 * @Create Date: 2018/6/27/0027 15:40
 * @Create Author: jiangruliang@finscm.com
 * @File Name: DJCYFileRead：车辆送货：车辆出园txt读取
 * @Last version: 1.0
 * @Function:
 * @Last Update Date:
 * @Change Log:
 */
@Service("DJCYFileRead")
public class DJCYFileRead implements FileReadWay {

  private static final String SYS_NO = "b2b2c biz DJCYFileRead";

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
        if (pdaStorageApplicationMapper.selectWhetherHaveIntNumByBarCode(map)>0){
          PdaFileHandleMo pdaFileHandleMo = new PdaFileHandleMo();
          pdaFileHandleMo.setBarcode(list[0]);
          pdaFileHandleMo.setCarNumber(list[1]);
          pdaFileHandleMo.setCode(list[2]);
          pdaFileHandleMo.setPdaDate(DateUtil.string2Date(list[3], "yyyy-MM-ddHH:mm:ss"));
          pdaFileHandleMos.add(pdaFileHandleMo);
        }

      }      if (pdaFileHandleMos.size() > 0) {
        int flag = pdaStorageApplicationMapper.insertDJCYBatches(pdaFileHandleMos);
        // 查询当前子单号为当前code的对应的 入库/出库单号 修改入库/出库状态
        if (flag != pdaFileHandleMos.size()) {
          throw new BusinessException(SYS_NO + " 0002", "批量插入数据失败");
        }
        DJGBFileRead.commonFileDeal(pdaStorageApplicationMapper, pdaFileHandleMos, SYS_NO);
      }
      reader.close();
      File file1 = new File(config.usedData + "/" + file.getName());
      FileRead.copyAndDelOld(file, file1);//剪切已插入数据库文件到新文件夹
    } catch (Exception e) {
      throw new BusinessException(SYS_NO + " 0001", e.getMessage());

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
   * 入库数据处理
   *
   * @Author:jiangruliang@finscm.com
   * @date: 2018/7/11/0011 14:24
   */
  public static int basicDataDeal(PdaStorageApplicationMapper pdaStorageApplicationMapper,
      StorageApplicationMapper storageApplicationMapper, List<PdaFileHandleMo> pdaFileHandleMos,
      String sysNo) {
    int flag = 0;
    for (PdaFileHandleMo mo : pdaFileHandleMos) {
      PositionTotalService positionTotalService = SpringApplicationContextUtil
          .getBean(PositionTotalService.class);
        StorageApplication storageApplication = storageApplicationMapper.selectByReceiptNumber(mo.getBarcode());
        if(!ObjectUtils.isNullObj(storageApplication) && 2 == storageApplication.getStorageType()){// 订单入库修改持仓
        	PositionCollect positionCollect = new PositionCollect();
        	positionCollect.setOrderId(storageApplication.getOrderId());
        	positionCollect.setQuantity(mo.getNetWeight());// 实际入库数量 
        	flag = positionTotalService.updatePositionCollect(positionCollect);
        	if(flag <= 0){
        		throw new BusinessException(SYS_NO + " 0671", "修改持仓失败！");
        	}
        }
        Map<String, Object> map = new HashMap<>();
        map.put("storagedNum", mo.getNetWeight());
        map.put("receiptNumber", mo.getBarcode());
        flag = storageApplicationMapper.updateByReceiptNumber(map);
        if (flag != 1) {
          throw new BusinessException(sysNo + " 0003", "更新已入库数量失败");
        }
    }
    return flag;
  }

  /**
   * @return void
   * @Author:jiangruliang@finscm.com
   * @date: 2018/6/28/0028 9:20
   */
  static void basicSetter(BufferedReader reader, List<PdaFileHandleMo> pdaFileHandleMos)
      throws IOException {

  }
}
