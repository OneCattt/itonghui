package com.fbw.service.config.cache;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.fbw.service.contents.CacheKeyConstant;
import com.fbw.service.entity.DictEntity;
import com.fbw.service.enums.CacheKeyEnums;
import com.fbw.service.mapper.DictMapper;
import com.fbw.service.util.GetCacheUtil;

/**
 * 
 * <功能详细描述> 启动时候初始化缓存信息
 * @author JACK HUANG
 * @version [版本号, 2017年8月16日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
@Order(1)
public class InitCacheMsg implements CommandLineRunner
{

    @Autowired
    private DictMapper dictMapper;

    @Autowired
    private GetCacheUtil cacheUtil;

    @Override
    public void run(String... args) throws Exception
    {
        // TODO Auto-generated method stub
        DictCache();
    }

    /**
     * 
     * <功能详细描述> 字典表缓存
     * @see [类、类#方法、类#成员]
     */
    private void DictCache()
    {
        Stream.of(CacheKeyConstant.PORTAL_TEST_MOBILES_KEY, CacheKeyConstant.PORTAL_BLACK_MOBILES_KEY).forEach(key -> {
            List<String> dictValueList = cacheUtil.getListRedisVal(CacheKeyEnums.getMsg(key));
            if (dictValueList.isEmpty())
            {
                dictValueList = dictMapper.getDictValue(new DictEntity(key, "0"));
                cacheUtil.setListRedisVal(CacheKeyEnums.getMsg(key), dictValueList);
            }
        });
    }

}
