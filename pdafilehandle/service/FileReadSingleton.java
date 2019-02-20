package com.itonghui.biz.pdafilehandle.service;

import com.itonghui.biz.pdafilehandle.enums.PdaFileHandleEnum;
import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright (c) by anhui tonghui information technology Co., Ltd.
 * @All right reserved.
 * @Create Date: 2018/7/5/0005 16:44
 * @Create Author: jiangruliang@finscm.com
 * @File Name: FileReadSingleton:单例模式：此类是为了避免FileReadContext类重复读取PdaFileHandleEnum
 * @Last version: 1.0
 * @Function:
 * @Last Update Date:
 * @Change Log:
 */
public class FileReadSingleton {

  private static FileReadSingleton instance = null;

  private FileReadSingleton() {
  }

  private static synchronized void syncInit() {
    if (instance == null) {
      instance = new FileReadSingleton();
    }
  }

  public static FileReadSingleton getInstance() {
    if (instance == null) {
      syncInit();
    }
    return instance;
  }

  private static Map<String, String> fileRead = new HashMap<>();

  static {
    for (PdaFileHandleEnum t : PdaFileHandleEnum.values()) {
      fileRead.put(t.type(), t.clazz());
    }
  }

  public String fileRead(String type) {
    return fileRead.get(type);
  }

}
