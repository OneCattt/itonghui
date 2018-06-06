package com.fbw.service.util;

import java.math.BigDecimal;
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
     * <功能详细描述> 判断A是否大于等于B
     * @param a
     * @param b
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean AgreaterThanB(BigDecimal a, BigDecimal b)
    {
        int compare = a.compareTo(b);
        if (compare == 1 || compare == 0)
        {
            return true;
        }
        return false;

    }

}
