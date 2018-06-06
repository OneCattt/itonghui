package com.fbw.serivce.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fbw.serivce.tools.CacheUtils;
import com.fbw.serivce.tools.ToolsClass;

/**
 * 
 * <功能详细描述> 认证
 * @author JACK HUANG
 * @version [版本号, 2017年9月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class ValidateModel
{

    /**
     * token忽略方法名
     */
    final String TOKEN_IGNORE_METHOD_KEY = "GATEWAY:TOKEN_IGNORE_CONF";

    /**
     * api认证的key
     */
    final String apisaltKey = "M7ML0CsKiaYKJ3fl8yTwKmR7UkEgjzxo";

    /**
     * 登陆token_key
     */
    final String PORTAL_LOGIN_TOKEN_KEY = "PORTAL:LOGIN:TOKEN_";

    @Autowired
    CacheUtils cacheUtils;

    /**
     * 
     * <功能详细描述> 效验验签请求规则
     * @param request
     * @return 效验成功返回true 否则返回false
     * @see [类、类#方法、类#成员]
     */
    public boolean validateRequestRule(HttpServletRequest request)
    {
        Object timestamp = request.getParameter("timestamp");
        Object noncestr = request.getParameter("noncestr");
        Object sign = request.getParameter("sign");
        Object channel = request.getParameter("channel");
        Object deviceId = request.getParameter("deviceid");
        Object phoneType = request.getParameter("phonetype");
        Object osVersion = request.getParameter("osversion");
        Object versionCode = request.getParameter("versioncode");
        Object iOSorAndroid = request.getParameter("iOSorAndroid");
        if (null == timestamp || null == noncestr || null == sign || null == channel || null == deviceId
                || null == phoneType || null == osVersion || null == versionCode || null == iOSorAndroid)
        {
            return false;
        }
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<?> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements())
        {
            String paramName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1)
            {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0)
                {
                    map.put(paramName, paramValue);
                }
            }
        }
        map.remove("sign");
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>()
        {
            public int compare(Entry<String, String> o1, Entry<String, String> o2)
            {
                return o1.getKey().compareTo(o2.getKey());
            }

        });
        String requestString = "";
        for (Map.Entry<String, String> mapping : list)
        {
            requestString = requestString + mapping.getKey() + "=" + mapping.getValue() + "&";
        }
        requestString = requestString + "apisalt=" + apisaltKey;
        if (ToolsClass.getMd5(requestString).equals(sign))
        {
            return true;
        }
        return false;
    }

    /**
     * 
     * <功能详细描述> 效验登陆token
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean validateLoginToken(HttpServletRequest request)
    {
        Object token = request.getParameter("token");
        String key = PORTAL_LOGIN_TOKEN_KEY + token;
        if (null == token)
        {
            return false;
        }
        String cacheLoginToken = cacheUtils.getStringRedisVal(key);
        if (token.equals(cacheLoginToken))
        {
            return true;
        }
        return false;

    }

    /**
     * 
     * <功能详细描述> 判断是否忽略的方法
     * @param request
     * @return true是登陆方法 false不是
     * @see [类、类#方法、类#成员]
     */
    public boolean isIgnoreMethod(HttpServletRequest request)
    {
        String uri = request.getRequestURI();
        List<String> meathodName = cacheUtils.getListRedisVal(TOKEN_IGNORE_METHOD_KEY);
        return ToolsClass.containstr(uri, meathodName);

    }

}
