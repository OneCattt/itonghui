package com.fbw.service.config.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fbw.service.feign.CommonFeignClient;

/**
 * 
 * <一句话功能简述> 自定义拦截器 <功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月7日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CusInterceptor extends HandlerInterceptorAdapter
{

    private final String TRACK_ID_FORMAT = "yyyyMMddHHmmss";

    @Autowired
    CommonFeignClient commonFeignClient;

    @Override
    /**
     * 请求前执行此方法
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat(TRACK_ID_FORMAT);
        String trackId = new StringBuffer().append(sdf.format(new Date())).append(commonFeignClient.getrandomstring(4))
                .toString();
        request.setAttribute("trackId", trackId);
        return true;
    }

    @Override
    /**
     * 请求完成后执行此方法
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception
    {
    }

}
