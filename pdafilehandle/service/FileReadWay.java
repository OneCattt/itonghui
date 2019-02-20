package com.itonghui.biz.pdafilehandle.service;

import java.io.File;

/**
 * @Copyright (c) by anhui tonghui information technology Co., Ltd.
 * @All right reserved.
 * @Create Date: 2018/6/27/0027 13:57
 * @Create Author: jiangruliang@finscm.com
 * @File Name: FileReadWay：文件读取接口
 * @Last version: 1.0
 * @Function:
 * @Last Update Date:
 * @Change Log:
 */
public interface FileReadWay {

  /**
   * 保存txt内容到数据库
   *
   * @Author:jiangruliang@finscm.com
   * @date: 2018/6/27/0027 13:56
   */
  int saveTxt(File file);

}
