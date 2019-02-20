package com.itonghui.biz.pdafilehandle.enums;

/**
 * @Copyright (c) by anhui tonghui information technology Co., Ltd.
 * @All right reserved.
 * @Create Date: 2018/7/5/0005 16:00
 * @Create Author: jiangruliang@finscm.com
 * @File Name: PdaFileHandleEnum:PDA枚举类
 * @Last version: 1.0
 * @Function:
 * @Last Update Date:
 * @Change Log:
 */
public enum PdaFileHandleEnum {
  DJCY_FILE_READ("DJCY",
      "com.itonghui.biz.pdafilehandle.service.impl.DJCYFileRead"), DJEB_FILE_READ("DJEB",
      "com.itonghui.biz.pdafilehandle.service.impl.DJEBFileRead"), DJGB_FILE_READ("DJGB",
      "com.itonghui.biz.pdafilehandle.service.impl.DJGBFileRead"), DJRY_FILE_READ("DJRY",
      "com.itonghui.biz.pdafilehandle.service.impl.DJRYFileRead"), DJXH_FILE_READ("DJXH",
      "com.itonghui.biz.pdafilehandle.service.impl.DJXHFileRead"), THCY_FILE_READ("THCY",
      "com.itonghui.biz.pdafilehandle.service.impl.THCYFileRead"), THEB_FILE_READ("THEB",
      "com.itonghui.biz.pdafilehandle.service.impl.THEBFileRead"), THGB_FILE_READ("THGB",
      "com.itonghui.biz.pdafilehandle.service.impl.THGBFileRead"), THPG_FILE_READ("THPG",
      "com.itonghui.biz.pdafilehandle.service.impl.THPGFileRead"), THRY_FILE_READ("THRY",
      "com.itonghui.biz.pdafilehandle.service.impl.THRYFileRead"),;

  private String type;
  private String clazz;

  private PdaFileHandleEnum(String type, String clazz) {
    this.type = type;
    this.clazz = clazz;
  }

  public String type() {
    return type;
  }

  public String clazz() {
    return clazz;
  }

}
