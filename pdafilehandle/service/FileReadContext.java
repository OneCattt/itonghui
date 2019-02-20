package com.itonghui.biz.pdafilehandle.service;

import com.itonghui.biz.pdafilehandle.enums.PdaFileHandleEnum;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright (c) by anhui tonghui information technology Co., Ltd.
 * @All right reserved.
 * @Create Date: 2018/6/27/0027 14:10
 * @Create Author: jiangruliang@finscm.com
 * @File Name: FileReadContext:策略类：首先根据type去PdaFileHandleEnum找到对应类位置（clz），再通过反射获取其中saveText方法并执行
 * @Last version: 1.0
 * @Function:
 * @Last Update Date:
 * @Change Log:
 */
class FileReadContext {
  private static Map<String,String> strategyMap = new HashMap<>();

  @SuppressWarnings("rawtypes")
static void excuteFileRead(String type, File file) {
    for (PdaFileHandleEnum t : PdaFileHandleEnum.values()){
      strategyMap.put(t.type(), t.clazz());
    }
    String class_path=strategyMap.get(type);
    try {
      //PdaFileHandleEnum通过反射将PdaFileHandleEnum中映射的类实例化
      Class clazz = Class.forName(class_path);
      //调用saveTxt方法传参file
      @SuppressWarnings("unchecked") Method excute = clazz.getDeclaredMethod("saveTxt", File.class);
      excute.invoke(clazz.newInstance(), file);
    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
      e.printStackTrace();
    }


  }
}
