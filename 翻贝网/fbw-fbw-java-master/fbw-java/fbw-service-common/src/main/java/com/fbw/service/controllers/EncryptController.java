package com.fbw.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.tooles.ToolsClass;

/**
 * 
 * <一句话功能简述> 加密Controller <功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年8月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
public class EncryptController extends BaseController
{

    @Autowired
    private ToolsClass toolsClass;

    @RequestMapping(value = "/getrandomstring")
    public String getRandomString(int length)
    {
        return toolsClass.getRandomString(length);
    }

    @RequestMapping(value = "/getmd5")
    public String getmd5(String string)
    {
        return toolsClass.getMd5(string, 0);
    }

    @RequestMapping(value = "/getsaltmd5")
    public String getsaltmd5(String string)
    {
        return toolsClass.getMd5(string, 1);
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        // TODO Auto-generated method stub

    }
}
