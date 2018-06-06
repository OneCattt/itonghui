package com.fbw.service.mq;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fbw.service.base.BaseMq;
import com.fbw.service.business.ground.service.RegisterService;
import com.fbw.service.entity.ground.RegisterEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.GroundFeignClient;
import com.fbw.service.feign.UserFeignClient;
import com.fbw.service.util.CommonUtil;
import com.fbw.service.util.GetCacheUtil;
import com.fbw.service.util.JsonUtil;

/**
 * 
 * <功能详细描述> 地推登陆消费者MQ
 * @author JACK HUANG
 * @version [版本号, 2017年9月8日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class GroundLoginConsumerMq extends BaseMq
{
    @Autowired
    RegisterService registerService;

    @Autowired
    CommonUtil commonUtil;

    @Autowired
    GetCacheUtil cacheUtil;

    @Autowired
    UserFeignClient userFeignClient;

    @Autowired
    GroundFeignClient groundFeignClient;

    /**
     * 有效地推注册状态
     */
    final String VAILD_GROUND_REGISTER = "0";

    /**
     * 无效地推注册状态
     */
    final String INVAILD_GROUND_REGISTER = "1";

    @Override
    @JmsListener(destination = "protal-update-login-ground-queue", containerFactory = "jmsListenerContainerQueue")
    public void receiveQueue(String text) throws InnerException
    {
        Map<String, Object> map = JsonUtil.convertMapByJson(text);
        // 构造登陆地推实体类
        RegisterEntity registerEntity = registerService.buildLoginGroundEntity(map);
        String mobile = registerEntity.getMobile();
        // 是否是地推绑定用户
        if (registerService.isGroundBindingUser(mobile))
        {
            // 是否是无效的地推用户
            if (commonUtil.isGroundInvaildRegisterUser(mobile, registerEntity.getCityId(),
                    registerEntity.getSameDeviceFlag()))
            {
                // 更新无效个人注册地推信息
                groundFeignClient.updateInVaildPersonalRegGroundInfo(registerEntity);
            }
            else
            {
                // 更新有效个人注册地推信息
                groundFeignClient.updateVaildPersonalRegGroundInfo(registerEntity);
            }

        }
        else
        {
            // 邀请码渠道
            if ("0".equals(registerEntity.getInviteFlag()))
            {
                // 判断是否是无效的邀请码渠道注册地推用户
                if (commonUtil.isInVaildGroundRegUserWithInviteCode(mobile, registerEntity.getCityId(),
                        registerEntity.getSameDeviceFlag()))
                {
                    // 更新邀请码渠道无效的地推注册用户
                    groundFeignClient.updateInVaildGroundRegUserWithInviteCode(registerEntity);
                }
                else
                {
                    // 更新邀请码渠道有效的地推注册用户
                    groundFeignClient.updateInVaildGroundRegUserWithInviteCode(registerEntity);
                }
            }
            else
            {
                // 保存地推登陆时间
                commonUtil.saveGroundLoginDate(mobile);
            }
        }

    }

    @Override
    public void receiveTopic(String text)
    {
        // TODO Auto-generated method stub

    }

}
