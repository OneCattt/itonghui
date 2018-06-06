package com.fbw.service.entity.common;

import java.io.Serializable;

public class CommonRsEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String errorCode;

    private String errorMessage;

    private boolean operationFlag;

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public boolean isOperationFlag()
    {
        return operationFlag;
    }

    public void setOperationFlag(boolean operationFlag)
    {
        this.operationFlag = operationFlag;
    }



}
