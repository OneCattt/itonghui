package com.fbw.service.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fbw.service.entity.user.UserCommentBusinEntity;

/**
 * 
 * <功能详细描述> 用户套件的feign客户端
 * @author JACK HUANG
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@FeignClient(name = "fbw-service-user")
public interface UserFeignClient
{
    /**
     * 
     * <功能详细描述>获取当前店铺最近最好的评价内容
     * @param mobile 手机号
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userComment/getOneRecentShopComment?shopId={shopId}", method = RequestMethod.GET)
    public UserCommentBusinEntity getOneRecentShopComment(@PathVariable("shopId") Integer shopId);

}
