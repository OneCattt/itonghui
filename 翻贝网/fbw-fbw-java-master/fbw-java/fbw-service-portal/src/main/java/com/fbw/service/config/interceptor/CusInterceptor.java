package com.fbw.service.config.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fbw.service.contents.CacheKeyConstant;
import com.fbw.service.entity.portal.TrackEntity;
import com.fbw.service.enums.CacheKeyEnums;
import com.fbw.service.repository.TrackLogRepository;
import com.fbw.service.util.GetCacheUtil;
import com.fbw.service.util.NomalUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
    TrackLogRepository trackLogRepository;

    @Autowired
    GetCacheUtil cacheUtils;

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
        // 插入追踪日志
        insertTrackLog(request, response);
        // 获取用户信息
        getUserInfo(request);
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

    /**
     * <功能详细描述>设置追踪ID
     * @param request
     * @see [类、类#方法、类#成员]
     */
    private String getTrackId(HttpServletRequest request)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(TRACK_ID_FORMAT);
        String trackId = new StringBuffer().append(sdf.format(new Date())).append(NomalUtil.getRandomString(4))
                .toString();
        request.setAttribute("trackId", trackId);
        return trackId;
    }

    /**
     * 
     * <功能详细描述> 获取用户信息
     * @param request
     * @see [类、类#方法、类#成员]
     */
    private void getUserInfo(HttpServletRequest request)
    {
        Object token = request.getParameter("token");
        String key = CacheKeyEnums.getMsg(CacheKeyConstant.PORTAL_LOGIN_TOKEN_KEY) + token;
        String cacheLoginToken = cacheUtils.getStringRedisVal(key);
        if (!NomalUtil.isNullOrEmpty(cacheLoginToken))
        {
            String[] userInfoArray = cacheLoginToken.split(",");
            request.setAttribute("userId", userInfoArray[0]);
            request.setAttribute("mobile", userInfoArray[1]);
        }
    }

    /**
     * 
     * <功能详细描述> 插入追踪日志
     * @param request
     * @param response
     * @see [类、类#方法、类#成员]
     */
    private void insertTrackLog(HttpServletRequest request, HttpServletResponse response)
    {
        if (null == response.getHeader("name"))
        {
            Observable.create(new ObservableOnSubscribe<TrackEntity>()
            {

                @Override
                public void subscribe(ObservableEmitter<TrackEntity> e) throws Exception
                {

                    TrackEntity trackEntity = new TrackEntity();
                    trackEntity.setTrackId(getTrackId(request));
                    trackEntity.setReqParm(NomalUtil.getRequestParm(request));
                    trackEntity.setReqMethod(request.getRequestURI().toString());
                    e.onNext(trackEntity);
                }
            }).observeOn(Schedulers.io()).subscribe(new Consumer<TrackEntity>()
            {

                @Override
                public void accept(TrackEntity t) throws Exception
                {
                    trackLogRepository.saveAndFlush(t);
                }
            });
        }
    }
}
