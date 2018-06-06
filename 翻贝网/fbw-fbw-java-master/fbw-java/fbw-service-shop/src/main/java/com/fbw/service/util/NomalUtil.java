package com.fbw.service.util;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * <一句话功能简述> 常用工具类 <功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class NomalUtil
{

    /**
     * <一句话功能简述> 判断字符串是否为空 <功能详细描述>
     * @param val
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNull(String val)
    {
        if (null == val || val.length() <= 0 || val.equals(""))
        {
            return true;
        }
        ;
        return false;

    }

    /**
     * 空值判断 支持判断对象是否为null，判断字符串是否为空，List和Map是否为空
     * @param obj
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNullOrEmpty(Object obj)
    {
        if (null == obj)
        {
            return true;
        }
        else if (obj instanceof String)
        {
            return obj.equals("") || obj.equals("null");
        }
        else if (obj instanceof List)
        {
            List<?> list = (List<?>) obj;
            return list.isEmpty();
        }
        else if (obj instanceof Map)
        {
            Map<?, ?> map = (Map<?, ?>) obj;
            return map.isEmpty();
        }
        return false;
    }

    /**
     * <一句话功能简述> 效验手机号格式 <功能详细描述>
     * @param mobilePhone
     * @return ture 为手机号， 反之不是
     * @see [类、类#方法、类#成员]
     */
    public static boolean isMobileNo(String mobilePhone)
    {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobilePhone);
        return m.matches();

    }

    /**
     * 
     * <功能详细描述> 金额的格式效验
     * @param money
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isMoneyFormat(String money)
    {
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
        Matcher isNum = pattern.matcher(money);
        if (!isNum.matches())
        {
            return false;
        }
        return true;

    }

}
