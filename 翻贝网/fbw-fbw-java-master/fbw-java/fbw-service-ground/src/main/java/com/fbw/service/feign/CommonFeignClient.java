package com.fbw.service.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fbw.service.config.feign.FeignDisableHystrixConfiguration;
import com.fbw.service.exception.InnerException;

@FeignClient(name = "fbw-service-common", configuration = FeignDisableHystrixConfiguration.class)
public interface CommonFeignClient
{
    @RequestMapping(value = "/user/getcode?mobile={mobile}", method = RequestMethod.GET)
    public String getusercode(@PathVariable("mobile") String mobile);

    @RequestMapping(value = "/getrandomstring?length={length}", method = RequestMethod.GET)
    public String getrandomstring(@PathVariable("length") int length);

    @RequestMapping(value = "/getmd5?string={string}", method = RequestMethod.GET)
    public String getmd5(@PathVariable("string") String string) throws InnerException;

    @RequestMapping(value = "/getsaltmd5?string={string}", method = RequestMethod.GET)
    public String getsaltmd5(@PathVariable("string") String string);
}
