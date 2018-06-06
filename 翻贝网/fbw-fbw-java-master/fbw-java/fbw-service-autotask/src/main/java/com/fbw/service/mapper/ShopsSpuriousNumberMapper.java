package com.fbw.service.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * <功能详细描述>商家虚假数据mapper
 * @author FBW0115
 * @version [版本号, 2017年10月11日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface ShopsSpuriousNumberMapper
{

    int getShopsSpuriousCollectNum(int shopId);
}
