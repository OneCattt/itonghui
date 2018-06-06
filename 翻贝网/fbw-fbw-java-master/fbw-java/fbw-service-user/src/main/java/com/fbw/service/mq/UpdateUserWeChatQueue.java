package com.fbw.service.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fbw.service.base.BaseMq;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.services.UserInfoBusinService;
import com.fbw.service.util.JsonUtil;

@Component
public class UpdateUserWeChatQueue extends BaseMq
{

    @Autowired
    private UserInfoBusinService userInfoService;

    /**
     * 获取更新用户设备信息队列
     * @param text
     */
    @Override
    @JmsListener(destination = "portal-update-openId-queue", containerFactory = "jmsListenerContainerQueue")
    public void receiveQueue(String text)
    {
        UserInfoBusinEntity userInfoBusinEntity = (UserInfoBusinEntity) JsonUtil.JSONToObj(text.toString(),
                UserInfoBusinEntity.class);
        try
        {
            userInfoService.updateByMobileSelective(userInfoBusinEntity);
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
