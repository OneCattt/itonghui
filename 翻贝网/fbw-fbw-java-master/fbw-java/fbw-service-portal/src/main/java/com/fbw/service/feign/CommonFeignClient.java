package com.fbw.service.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fbw.service.config.feign.FeignDisableHystrixConfiguration;
import com.fbw.service.entity.common.CityIntentionAgentEntity;
import com.fbw.service.entity.common.CityBusinEntity;

/**
 * 
 * <功能详细描述> 公共套件的Fegin的客户端
 * @author JACK HUANG
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@FeignClient(name = "fbw-service-common", configuration = FeignDisableHystrixConfiguration.class)
public interface CommonFeignClient
{
    @RequestMapping(value = "/user/getcode?mobile={mobile}", method = RequestMethod.GET)
    public String getusercode(@PathVariable("mobile") String mobile);

    @RequestMapping(value = "/getrandomstring?length={length}", method = RequestMethod.GET)
    public String getrandomstring(@PathVariable("length") int length);

    @RequestMapping(value = "/getmd5?string={string}", method = RequestMethod.GET)
    public String getmd5(@PathVariable("string") String string);

    @RequestMapping(value = "/getsaltmd5?string={string}", method = RequestMethod.GET)
    public String getsaltmd5(@PathVariable("string") String string);

    /**
     * 
     * <功能详细描述>获取城市list
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/city/getCityList", method = RequestMethod.GET)
    List<CityBusinEntity> getCityList();

    /**
     * 
     * <功能详细描述>根据城市id获取城市信息
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/city/getCityInfoByCityId", method = RequestMethod.GET)
    CityBusinEntity getCityInfoByCityId(@RequestParam("cityId") int cityId);

    /**
     * 
     * <功能详细描述>保存城市代理申请
     * @param entity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/city/saveCityAgent", method = RequestMethod.POST)
    int saveCityAgent(@RequestBody CityIntentionAgentEntity entity);
}

/**
 * 
 * <一句话功能简述> CommonFeignClient的fallback工厂处理方法，增加打印日志功能 <功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年6月30日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
/*
 * @Component class CommonFeignClientFallback implements
 * FallbackFactory<CommonFeignClient> {
 * 
 * private static final Logger LOGGER =
 * LoggerFactory.getLogger(CommonFeignClientFallback.class);
 * 
 * @Override public CommonFeignClient create(Throwable cause) { return new
 * CommonFeignClient() {
 * 
 * @Override public String getusercode(String mobile) { // TODO Auto-generated
 * method stub CommonFeignClientFallback.LOGGER.info("getusercode fail reason:",
 * cause); return "{\"status\": \"success\",\"message\":\"获取用户编码失败\"}"; }
 * 
 * @Override public String getrandomstring(int length) { // TODO Auto-generated
 * method stub
 * CommonFeignClientFallback.LOGGER.info("getrandomstring fail reason:", cause);
 * return "123"; }
 * 
 * @Override public String getmd5(String string) { // TODO Auto-generated method
 * stub CommonFeignClientFallback.LOGGER.info("getmd5 fail reason:", cause);
 * return "123"; }
 * 
 * @Override public String getsaltmd5(String string) { // TODO Auto-generated
 * method stub CommonFeignClientFallback.LOGGER.info("getsaltmd5 fail reason:",
 * cause); return "121"; }
 * 
 * };
 */
/*
 * }
 */
/// *}*/
