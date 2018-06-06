package com.fbw.service.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fbw.service.entity.portal.InnerCode;

/**
 * 
 * <一句话功能简述> 控制层基类 <功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月6日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class BaseController implements BaseLogger
{

    /**
     * <一句话功能简述> 失败返回信息 <功能详细描述>
     * @param message
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> failedMessage(String errorCode)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", "failed");
        map.put("message", InnerCode.geterrorMsg(errorCode));
        map.put("errorCode", errorCode);
        return map;
    }

    /**
     * <一句话功能简述> 成功返回信息 <功能详细描述>
     * @param message
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> successMessage(String message)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", "success");
        map.put("message", message);
        return map;
    }

    /**
     * <一句话功能简述> 成功返回数据 <功能详细描述>
     * @param data
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> successData(Map<String, Object> data)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", "success");
        map.put("data", data);
        return map;
    }

    public final String getIpAddress(HttpServletRequest request)
    {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("Proxy-Client-IP");

            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("WL-Proxy-Client-IP");

            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("HTTP_CLIENT_IP");

            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");

            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getRemoteAddr();

            }
        }
        else if (ip.length() > 15)
        {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++)
            {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp)))
                {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

    /**
     * 
     * <功能详细描述> 设置第一次请求的标识
     * @see [类、类#方法、类#成员]
     */
    public void setFisrtRequestFlag(HttpServletResponse response)
    {
        response.setHeader("name", "fbw");
    }

    /**
     * <一句话功能简述> define error level log <功能详细描述>
     * @param errorMsg
     * @see [类、类#方法、类#成员]
     */
    public abstract void getErrorLog(String errorMsg);

}
