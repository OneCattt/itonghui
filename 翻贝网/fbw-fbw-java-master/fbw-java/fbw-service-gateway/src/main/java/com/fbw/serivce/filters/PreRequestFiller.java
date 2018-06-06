package com.fbw.serivce.filters;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.fbw.serivce.models.ReturnRestMessageModel;
import com.fbw.serivce.models.ValidateModel;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 
 * <功能详细描述> 请求过滤器
 * @author JACK HUANG
 * @version [版本号, 2017年9月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class PreRequestFiller extends ZuulFilter
{

    @Override
    public String filterType()
    {
        return "pre";
    }

    @Override
    public int filterOrder()
    {
        return 0;
    }

    @Override
    public boolean shouldFilter()
    {
        return true;
    }

    @Autowired
    private ValidateModel validateModel;

    @Override
    public Object run()
    {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if (request.getMethod().equals("POST"))
        {
            ctx.addZuulResponseHeader("Content-Type", "application/json;charset=UTF-8");
            // 效验登陆token
            if (!validateModel.isIgnoreMethod(request) && !validateModel.validateLoginToken(request))
            {
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(600);
                ctx.setResponseBody(ReturnRestMessageModel.failedMessage("token validate fail", "600"));
                return null;
            }
            // 效验验签请求规则
            if (!validateModel.validateRequestRule(request))
            {
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(601);
                ctx.addZuulResponseHeader("Content-Type", "application/json;charset=UTF-8");
                ctx.setResponseBody(ReturnRestMessageModel.failedMessage("sign validate fail", "601"));
                return null;
            }
        }
        return null;
    }

}