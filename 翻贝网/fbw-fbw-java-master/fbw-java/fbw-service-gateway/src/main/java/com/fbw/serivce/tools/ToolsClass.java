package com.fbw.serivce.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 
 * <功能详细描述> md5加密
 * @author JACK HUANG
 * @version [版本号, 2017年9月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ToolsClass
{
    public static String getMd5(String plainText)
    {
        try
        {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(plainText.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把没一个byte 做一个与运算 0xff;
            for (byte b : result)
            {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                // System.out.println(str);
                if (str.length() == 1)
                {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            // 标准的md5加密后的结果
            System.out.println("md5:" + buffer.toString());
            return buffer.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 
     * <功能详细描述> list的值是否包含在字符串中
     * @param str
     * @param s
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean containstr(String str, List<String> s)
    {
        boolean flag = false;
        for (String str1 : s)
        {

            if (str.contains(str1))
            {
                return flag = true;
            }
            else
            {
                flag = false;
            }
        }
        return flag;
    }
}
