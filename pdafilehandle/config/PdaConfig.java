package com.itonghui.biz.pdafilehandle.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @Copyright (c) by anhui tonghui information technology Co., Ltd.
 * @All right reserved.
 * @Create Date: 2018/6/28/0028 14:38
 * @Create Author: jiangruliang@finscm.com
 * @File Name: Config:pda接口配置参数
 * @Last version: 1.0
 * @Function:
 * @Last Update Date:
 * @Change Log:
 */
@Configuration
public class PdaConfig {
  @Value("${pda.newData}")
  public String newData;

  @Value("${pda.usedData}")
  public String usedData;
}
