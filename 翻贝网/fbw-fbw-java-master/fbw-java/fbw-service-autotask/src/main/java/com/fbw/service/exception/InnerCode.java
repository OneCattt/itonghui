package com.fbw.service.exception;

/**
 * 
 * <一句话功能简述> 自定义异常编码<功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月5日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum InnerCode
{
    NULL_PONINT("空指针异常", "1001"), ARRAY_INDEX_OUT_OF_BOUNDS("数组越界", "1002");
    /**
     * 异常信息
     */
    private String errorMsg;

    /**
     * 异常编码
     */
    private String errorCode;

    /**
     * 
     * @param errorMsg
     * @param errorCode <默认构造函数>
     */
    private InnerCode(String errorMsg, String errorCode)
    {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    /**
     * <一句话功能简述> 获取异常信息 <功能详细描述>
     * @param errorCode
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String geterrorMsg(String errorCode)
    {
        for (InnerCode c : InnerCode.values())
        {
            if (c.getErrorCode().equals(errorCode))
            {
                return c.errorMsg;
            }
        }
        return null;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

}
