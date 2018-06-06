package com.fbw.service.base;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fbw.service.contents.WeChatRechargeConstant;
import com.fbw.service.dao.RechargeDao;
import com.fbw.service.entity.recharge.RechargeEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.CommonFeignClient;
import com.fbw.service.mapper.RechargeMapper;
import com.fbw.service.util.NomalUtil;

/**
 * 
 * <一句话功能简述> 服务层基类 <功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月7日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class BaseService implements BaseLogger
{

    @Autowired
    CommonFeignClient commonFeignClient;

    @Autowired
    RechargeDao rechargeDao;

    @Autowired
    RechargeMapper rechargeMapper;

    /**
     * 
     * <功能详细描述> 生成微信签名
     * @param map
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    protected String getWeChatSign(Map<String, Object> map) throws InnerException
    {
        List<Map.Entry<String, Object>> list = NomalUtil.mapSortToList(map);
        String requestString = "";
        for (Map.Entry<String, Object> mapping : list)
        {
            requestString = requestString + mapping.getKey() + "=" + mapping.getValue() + "&";
        }
        requestString = requestString + "key=" + WeChatRechargeConstant.RECHARGE_WECHAT_ENCRYPT_SIGN;
        String sign = NomalUtil.getMd5(requestString, 0).toUpperCase();
        System.out.println("encryptionSign：" + sign);
        return sign;
    }

    /**
     * 
     * <功能详细描述>更新充值业务
     * @param rechargeEntity
     * @return
     * @throws InnerException
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = InnerException.class)
    protected synchronized boolean updateRechargeBusin(RechargeEntity rechargeEntity) throws InnerException
    {
        boolean flag = false;
        // 更新充值订单状态和更新用户余额
        if (rechargeDao.updateRechargeOrderStatus(rechargeEntity) && rechargeDao.updateUserBalanceInfo(
                rechargeEntity.getMobilePhone(), new BigDecimal(rechargeEntity.getDoubleMoney())))
        {
            return flag = true;
        }
        return flag;

    }

    /**
     * <一句话功能简述> define error level log <功能详细描述>
     * @param errorMsg
     * @see [类、类#方法、类#成员]
     */
    public abstract void getErrorLog(String errorMsg);

}
