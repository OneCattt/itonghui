package com.fbw.service.exception;

/**
 * 
 * <一句话功能简述> 内部异常定义 <功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月5日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class InnerException extends Exception
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    /**
     * 错误编码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 消息是否为属性文件中的Key
     */
    private boolean propertiesKey = true;

    /**
     * @param message:信息描述 <默认构造函数>
     */
    public InnerException(String message)
    {
        super();
        this.message = message;
    }

    /**
     * 构造一个基本异常.
     *
     * @param errorCode 错误编码
     * @param message 信息描述
     */
    public InnerException(String errorCode, String message)
    {
        this(errorCode, message, true);
    }

    /**
     * 构造一个基本异常.
     *
     * @param errorCode 错误编码
     * @param message 信息描述
     */
    public InnerException(String errorCode, String message, Throwable cause)
    {
        this(errorCode, message, cause, true);
    }

    /**
     * @param errorCode 错误编码
     * @param message 信息描述
     * @param propertiesKey 消息是否为属性文件中的Key <默认构造函数>
     */
    public InnerException(String errorCode, String message, boolean propertiesKey)
    {
        super();
        this.setMessage(message);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }

    /**
     * 构造一个基本异常.
     *
     * @param errorCode 错误编码
     * @param message 信息描述
     */
    public InnerException(String errorCode, String message, Throwable cause, boolean propertiesKey)
    {
        super(cause);
        this.setMessage(message);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }

    /**
     * 构造一个基本异常.
     *
     * @param message 信息描述
     * @param cause 根异常类（可以存入任何异常）
     */
    public InnerException(String message, Throwable cause)
    {
        super(cause);
        this.setMessage(message);
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public boolean isPropertiesKey()
    {
        return propertiesKey;
    }

    public void setPropertiesKey(boolean propertiesKey)
    {
        this.propertiesKey = propertiesKey;
    }

}
