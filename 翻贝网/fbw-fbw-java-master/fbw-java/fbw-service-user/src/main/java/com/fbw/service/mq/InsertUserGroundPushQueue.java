package com.fbw.service.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fbw.service.base.BaseMq;
import com.fbw.service.entity.user.UserGroundInfoBusinEntity;
import com.fbw.service.services.UserInfoBusinService;
import com.fbw.service.util.JsonUtil;

@Component
public class InsertUserGroundPushQueue extends BaseMq
{

    @Autowired
    private UserInfoBusinService userInfoService;

    /**
     * 获取更新用户设备信息队列
     * @param text
     */
    @Override
    @JmsListener(destination = "portal-insert-userGroundPush-queue", containerFactory = "jmsListenerContainerQueue")
    public void receiveQueue(String text)
    {
        UserGroundInfoBusinEntity userGroundPushBusinEntity = (UserGroundInfoBusinEntity) JsonUtil
                .JSONToObj(text.toString(), UserGroundInfoBusinEntity.class);
        try
        {
            userInfoService.insertUserGround(userGroundPushBusinEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("消费：" + text);

    }

    @Override
    public void receiveTopic(String text)
    {

    }

}
