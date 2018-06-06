package com.fbw.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.fbw.service.entity.DictEntity;

/**
 * 
 * <一句话功能简述> 字典表Mapper <功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface DictMapper
{

    /**
     * <一句话功能简述> 查询字典值 <功能详细描述>
     * @param dictEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Select("select dictValue from t_portal_dictionary_conf where dictKey = #{dictKey} and status = #{status} and now() BETWEEN createdate and enddate")
    List<String> getDictValue(DictEntity dictEntity);
}