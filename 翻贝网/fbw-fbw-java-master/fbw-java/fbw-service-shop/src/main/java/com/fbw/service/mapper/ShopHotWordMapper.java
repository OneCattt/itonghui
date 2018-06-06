package com.fbw.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.shop.ShopHotWordEntity;

/**
 * 
 * <功能详细描述>热词mapper
 * @author FBW0115
 * @version [版本号, 2017年8月30日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface ShopHotWordMapper
{

    /**
     * 
     * <功能详细描述>根据城市id查询热词
     * @param cityid
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopHotWordEntity> getHotWordsBycityId(int cityId);

}