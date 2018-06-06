package com.fbw.service.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fbw.service.config.feign.FeignDisableHystrixConfiguration;
import com.fbw.service.entity.recharge.RechargeEntity;

/**
 * 
 * <功能详细描述> 充值套件的Fegin的客户端
 * @author JACK HUANG
 * @version [版本号, 2017年8月24日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@FeignClient(name = "fbw-service-recharge", configuration = FeignDisableHystrixConfiguration.class)
public interface RechargeFeignClient
{
    /**
     * 
     * <功能详细描述> 保存充值订单记录
     * @param rechargeEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/recharge/insertRechargeRecord", method = RequestMethod.POST)
    public boolean insertRechargeOrderRecord(@RequestBody RechargeEntity rechargeEntity);

    /**
     * 
     * <功能详细描述> 发送支付宝支付请求
     * @param rechargeNumber
     * @param money
     * @param mobiles
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/recharge/sendAppAiPay?rechargeOrderNumber={rechargeOrderNumber}&money={money}&mobiles={mobiles}", method = RequestMethod.GET)
    public Map<String, Object> sendAppAiPay(@PathVariable("rechargeOrderNumber") String rechargeOrderNumber,
            @PathVariable("money") int money, @PathVariable("mobiles") String mobiles);

    /**
     * 
     * <功能详细描述>发送微信支付请求
     * @param rechargeNumber
     * @param money
     * @param reqestIp
     * @param mobiles
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/recharge/sendAppWechatPay?rechargeOrderNumber={rechargeOrderNumber}&money={money}&reqestIp={reqestIp}&mobiles={mobiles}", method = RequestMethod.GET)
    public Map<String, Object> sendAppWechatPay(@PathVariable("rechargeOrderNumber") String rechargeOrderNumber,
            @PathVariable("money") int money, @PathVariable("reqestIp") String reqestIp,
            @PathVariable("mobiles") String mobiles);

    /**
     * 通过手机号获取90天内充值列表 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/recharge/getRechargeListByUserId?mobile={mobile}")
    public List<Map<String, Object>> getRechargeListByUserId(@PathVariable("mobile") String mobile);

}
