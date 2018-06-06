package com.fbw.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.shop.InterviewParentEntity;

/**
 * 
 * <功能详细描述>探店mapper
 * @author FBW0115
 * @version [版本号, 2017年8月17日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface InterviewMapper
{
    /**
     * 
     * <功能详细描述>查询所有的探店
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<InterviewParentEntity> getInterviewParentAll();

    /**
     * 
     * <功能详细描述>更新探店浏览量
     * @param entity
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateInterviewBrowseNum(InterviewParentEntity entity);

}
