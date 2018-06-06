package com.fbw.service.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.fbw.service.contents.ErrorMsgConstant;
import com.fbw.service.exception.InnerCode;
import com.fbw.service.exception.InnerException;

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
        if (val == null || val.length() <= 0 || val == "")
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
            return obj.equals("");
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

    /**
     * 
     * <功能详细描述>效验手机号格式
     * @param mobile
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public static void checkMobileFormat(String mobile) throws InnerException
    {
        if (isNull(mobile))
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_NULL_PARM_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_NULL_PARM_FAIL));
        }
        // 手机号格式效验
        if (!isMobileNo(mobile))
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_MOBILE_FORMAT_FAIL_MSG,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_MOBILE_FORMAT_FAIL_MSG));
        }
    }

    /**
     * 
     * <功能详细描述> 效验金额格式
     * @param money
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public static void checkMoneyFormat(String money) throws InnerException
    {
        if (isNull(money))
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_NULL_PARM_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_NULL_PARM_FAIL));
        }
        // 金额格式效验
        if (!(isMoneyFormat(money)))
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_MONEY_FORMAT_ERROR,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_MONEY_FORMAT_ERROR));
        }
    }

    /**
     * 判断时间是否在时间段内
     * 
     * @param date 当前时间 yyyy-MM-dd HH:mm:ss
     * @param strDateBegin 开始时间 00:00:00
     * @param strDateEnd 结束时间 00:05:00
     * @return
     */
    public static boolean isInDate(Date date, String strDateBegin, String strDateEnd)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String strDate = sdf.format(date); // 2016-12-16 11:53:54
        // 截取当前时间时分秒 转成整型
        int tempDate = Integer
                .parseInt(strDate.substring(11, 13) + strDate.substring(14, 16) + strDate.substring(17, 19));
        // 截取开始时间时分秒 转成整型
        int tempDateBegin = Integer
                .parseInt(strDateBegin.substring(0, 2) + strDateBegin.substring(3, 5) + strDateBegin.substring(6, 8));
        // 截取结束时间时分秒 转成整型
        int tempDateEnd = Integer
                .parseInt(strDateEnd.substring(0, 2) + strDateEnd.substring(3, 5) + strDateEnd.substring(6, 8));

        if ((tempDate >= tempDateBegin && tempDate <= tempDateEnd))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 
     * <功能详细描述> 解析地推Url信息
     * @param url
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Map<String, String> analysisGroundUrl(String url)
    {
        Map<String, String> data = new HashMap<String, String>();
        String realUrl = StringUtils.substringAfter(url, "qrcode/");
        String[] strs = realUrl.split("/");
        String groundType = strs[0];
        data.put("groundType", groundType);
        if (strs[1].indexOf("shopuserid") != -1)
        {
            String shopAssistantId = StringUtils.substringAfter(strs[1], "?shopuserid=");
            String shopId = StringUtils.substringBefore(strs[1], "?shopuserid=");
            data.put("shopAssistantId", shopAssistantId);
            data.put("shopId", shopId);
        }
        else
        {
            if ("shop".equals(groundType))
            {
                data.put("shopId", strs[1]);
            }
            else
            {
                data.put("salesManId", strs[1]);
            }
        }
        return data;
    }

    /**
     * 
     * <功能详细描述>生成随机码
     * @param length
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getRandomString(int length)
    {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++)
        {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
