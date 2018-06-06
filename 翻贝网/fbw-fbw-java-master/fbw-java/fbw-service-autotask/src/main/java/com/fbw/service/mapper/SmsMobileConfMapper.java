package com.fbw.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * <功能详细描述>短信号码mapper
 * @author FBW0115
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface SmsMobileConfMapper
{

    /**
     * 
     * <功能详细描述>根据类型获取接受号码
     * @param type
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<String> getSmsMobilesByType(int type);

}
