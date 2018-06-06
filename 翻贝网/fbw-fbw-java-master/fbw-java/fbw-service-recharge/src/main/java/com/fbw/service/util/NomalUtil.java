package com.fbw.service.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Value;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

    @Value("euKEEL9w")
    private static String passwordsalt;

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
     * <功能详细描述> 金额的格式效验
     * @param money
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isMoneyFormat(String money)
    {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(money);
        if (!isNum.matches())
        {
            return false;
        }
        return true;

    }

    /**
     * 
     * <功能详细描述> list转String
     * @param list
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getXmlFromMap(List<Map.Entry<String, Object>> list)
    {
        String xml = "<xml>";
        for (Map.Entry<String, Object> mapping : list)
        {
            if (isNumeric(mapping.getValue().toString()))
            {
                xml = xml + "<" + mapping.getKey() + ">" + mapping.getValue() + "</" + mapping.getKey() + ">";
            }
            else
            {
                xml = xml + "<" + mapping.getKey() + "><![CDATA[" + mapping.getValue() + "]]></" + mapping.getKey()
                        + ">";
            }
        }
        xml = xml + "</xml>";
        System.out.println(xml);
        return xml;
    }

    /**
     * 
     * <功能详细描述> map装List
     * @param unifiedorder
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static List<Map.Entry<String, Object>> mapSortToList(Map<String, Object> unifiedorder)
    {
        List<Map.Entry<String, Object>> list = new ArrayList<Map.Entry<String, Object>>(unifiedorder.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Object>>()
        {
            public int compare(Entry<String, Object> o1, Entry<String, Object> o2)
            {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        return list;
    }

    /**
     * 
     * <功能详细描述> 判断是否是数字
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    private static boolean isNumeric(String str)
    {
        for (int i = 0; i < str.length(); i++)
        {
            if (!Character.isDigit(str.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * <功能详细描述> xml转map
     * @param xmlString
     * @return
     * @throws InnerException
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @see [类、类#方法、类#成员]
     */
    public static Map<String, Object> getMapFromXML(String xmlString) throws InnerException

    {
        if (NomalUtil.isNull(xmlString))
        {
            throw new InnerException(ErrorMsgConstant.RECHARGE_WECHAT_REQ_PARM_ERROR,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_WECHAT_REQ_PARM_ERROR));
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Node node;
        Map<String, Object> map = new HashMap<String, Object>();
        int i = 0;
        try
        {
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream is = getStringStream(xmlString);
            Document document = builder.parse(is);
            NodeList allNodes = document.getFirstChild().getChildNodes();
            while (i < allNodes.getLength())
            {
                node = allNodes.item(i);
                if (node instanceof Element)
                {
                    map.put(node.getNodeName(), node.getTextContent());
                }
                i++;
            }
        }
        catch (ParserConfigurationException | IOException | SAXException e)
        {
            throw new InnerException(ErrorMsgConstant.RECHARGE_XML_CONVERT_MAP_ERROR,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_XML_CONVERT_MAP_ERROR));
        }

        return map;
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
     * 
     * <功能详细描述> 获取String转InputStream类型
     * @param sInputString
     * @return
     * @see [类、类#方法、类#成员]
     */
    private static InputStream getStringStream(String sInputString)
    {
        ByteArrayInputStream tInputStringStream = null;
        if (sInputString != null && !sInputString.trim().equals(""))
        {
            tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
        }
        return tInputStringStream;
    }

    /**
     * 
     * <功能详细描述> 获取MD5加密
     * @param plainText
     * @param mode
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getMd5(String plainText, int mode)
    {
        try
        {
            if (mode == 1)
            {
                plainText = plainText + passwordsalt;
            }
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++)
            {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            // 32位加密
            return buf.toString();
            // 16位的加密
            // return buf.toString().substring(8, 24);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
