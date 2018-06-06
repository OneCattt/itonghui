package com.fbw.service.business.login.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.exception.InnerException;
import com.fbw.service.util.HttpRequestUtil;

/**
 * 
 * <功能详细描述> 登陆controller层
 * @author JACK HUANG
 * @version [版本号, 2017年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
public class WeChatAppletController extends BaseController
{

    final String REQUEST_URL = "https://api.weixin.qq.com/sns/jscode2session?";

    /**
     * APPID
     */
    final String APPID = "wx34f19fb243ffde12";

    /**
     * APP密匙
     */
    final String APP_SECRET = "62007c9232062e88890bf82486cf5792";

    /**
     * 
     * <功能详细描述> 登陆
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/getWeChatAppletOpenId", method = RequestMethod.GET)
    public Map<String, Object> getWeChatAppletOpenId(String jsCode)
    {
        Map<String, Object> data = new HashMap<String, Object>();
        StringBuffer buffer = new StringBuffer(REQUEST_URL);
        String realUrl = buffer.append("appid=").append(APPID).append("&secret=").append(APP_SECRET).append("&js_code=")
                .append(jsCode).append("&grant_type=authorization_code").toString();
        try
        {
            data.put("data", HttpRequestUtil.sendGet(realUrl, null));
        }
        catch (InnerException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return successData(data);

    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(WeChatAppletController.class, errorMsg);
    }

}
