package com.fbw.service.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class ReqUtils
{
    /**
     * @param request request
     * @throws UnsupportedEncodingException
     */
    public static void setCharacterEncoding(HttpServletRequest request) throws UnsupportedEncodingException
    {
        request.setCharacterEncoding("UTF-8");
    }

    /**
     * @param request
     * @param paramName 参数名称
     * @return 从request获取参数对应字符串值
     */
    public static String getString(HttpServletRequest request, String paramName)
    {
        String value = request.getParameter(paramName);
        return value != null ? value : "";
    }

    /**
     * @param request
     * @param paramName 参数名称
     * @return 从request获取属性对应字符串值
     */
    public static String getStrAtt(HttpServletRequest request, String paramName)
    {
        String value = (String) request.getAttribute(paramName);
        return value != null ? value : "";
    }

    /**
     * @param request
     * @param paramName 参数名称
     * @return 从request获取参数对应数组值
     */
    public static String[] getArray(HttpServletRequest request, String paramName)
    {
        return request.getParameterValues(paramName);
    }

    /**
     * @param request
     * @param paramName 参数名称
     * @return 从request获取参数对应Byte值
     */
    public static byte getByte(HttpServletRequest request, String paramName)
    {
        String value = request.getParameter(paramName);
        if (value == null || value.length() == 0)
            return 0;
        else
            return Byte.parseByte(value);
    }

    /**
     * @param request
     * @param paramName 参数名称
     * @return 从request获取参数对应整型值
     */
    public static int getInt(HttpServletRequest request, String paramName)
    {
        String value = request.getParameter(paramName);
        if (value == null || value.length() == 0)
            return 0;
        else
        {
            try
            {
                return Integer.parseInt(value);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return 0;
            }
        }
    }

    /**
     * 获取date <功能详细描述>
     * @param request
     * @param paraName
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Date getDate(HttpServletRequest request, String paraName)
    {
        String tempStr = request.getParameter(paraName);
        if (StringUtils.isBlank(tempStr))
        {
            return null;
        }
        try
        {
            return new SimpleDateFormat("yyyy-MM-dd").parse(tempStr);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * @param request
     * @param paramName 参数名称
     * @return 从request获取参数对应整型值
     */
    public static int getIntAtt(HttpServletRequest request, String paramName)
    {
        String value = request.getAttribute(paramName).toString();
        if (value == null || value.length() == 0)
            return 0;
        else
        {
            try
            {
                return Integer.parseInt(value);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return 0;
            }
        }
    }

    /**
     * @param request
     * @param paramName 参数名称
     * @return 从request获取参数对应长整型值
     */
    public static long getLong(HttpServletRequest request, String paramName)
    {
        String value = request.getParameter(paramName);
        if (value == null || value.length() == 0)
            return 0L;
        else
        {
            try
            {
                return Long.parseLong(value);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return 0L;
            }
        }

    }

    /**
     * @param request
     * @param paramName 参数名称
     * @return 从request获取参数对应短整型值
     */
    public static short getShort(HttpServletRequest request, String paramName)
    {
        String value = request.getParameter(paramName);
        if (value == null || value.length() == 0)
            return 0;
        else
            return Short.parseShort(value);
    }

    /**
     * @param request
     * @param paramName 参数名称
     * @return 从request获取参数对应布尔值
     */
    public static boolean getBoolean(HttpServletRequest request, String paramName)
    {
        String value = request.getParameter(paramName);
        if (value == null || value.length() == 0)
            return false;
        else
            return Boolean.valueOf(value).booleanValue();
    }
}
