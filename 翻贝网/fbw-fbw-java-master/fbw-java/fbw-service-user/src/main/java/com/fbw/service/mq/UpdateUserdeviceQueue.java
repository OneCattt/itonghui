package com.fbw.service.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fbw.service.base.BaseMq;
import com.fbw.service.entity.user.UserDeviceInfoEntity;
import com.fbw.service.mappers.UserDeviceInfoEntityMapper;
import com.fbw.service.util.JsonUtil;
import com.fbw.service.util.NomalUtil;

@Component
public class UpdateUserdeviceQueue extends BaseMq
{
    @Autowired
    private UserDeviceInfoEntityMapper userDeviceInfoEntityMapper;

    /**
     * 获取更新用户设备信息队列
     * @param text
     */
    @Override
    @JmsListener(destination = "portal-update-userDevice-queue", containerFactory = "jmsListenerContainerQueue")
    public void receiveQueue(String text)
    {
        UserDeviceInfoEntity userDeviceInfoEntity = (UserDeviceInfoEntity) JsonUtil.JSONToObj(text.toString(),
                UserDeviceInfoEntity.class);
        try
        {
            UserDeviceInfoEntity userDeviceInfo = userDeviceInfoEntityMapper.selectByPrimaryKey(userDeviceInfoEntity);
            if (NomalUtil.isNullOrEmpty(userDeviceInfo))
            {
                userDeviceInfoEntityMapper.insertSelective(userDeviceInfoEntity);
            }
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
