package com.itonghui.biz.pdafilehandle.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Copyright (c) by anhui tonghui information technology Co., Ltd.
 * @All right reserved.
 * @Create Date: 2018/6/27/0027 13:34
 * @Create Author: jiangruliang@finscm.com
 * @File Name: FileReadImpl:读取指定文件夹
 * @Last version: 1.0
 * @Function:
 * @Last Update Date:
 * @Change Log:
 */
public class FileRead {

  /**
   * 获取路径下的所有文件/文件夹
   *
   * @param directoryPath 需要遍历的文件夹路径
   */
  public static void getAllFile(String directoryPath) {
    File baseFile = new File(directoryPath);
    if (baseFile.isFile() || !baseFile.exists()) {
      return;
    }
    File[] files = baseFile.listFiles();
    if (files == null) {
      return;
    }
    for (File file : files) {
      fileType(file.getName().substring(file.getName().length() - 8, file.getName().length() - 4),
          file);
    }
  }

  /**
   * 根据文件所属类型跳到对应方法
   *
   * @Author:jiangruliang@finscm.com
   * @date: 2018/6/27/0027 18:00
   */
  private static void fileType(String type, File file) {
    FileReadContext.excuteFileRead(type, file);
  }

  /**
   * 实现剪切文件方法
   *
   * @Author:jiangruliang@finscm.com
   * @date: 2018/6/28/0028 16:12
   */
  public static void copyAndDelOld(File f1, File f2) throws IOException {
    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f1));
    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f2));
    int len;
    byte[] b = new byte[1024];
    while ((len = bis.read(b)) != -1) {
      bos.write(b, 0, len);
    }
    bis.close();
    bos.close();
    boolean r = f1.delete();
    if (!r) {
      throw new IOException("删除旧文件失败！");
    }
  }

}
