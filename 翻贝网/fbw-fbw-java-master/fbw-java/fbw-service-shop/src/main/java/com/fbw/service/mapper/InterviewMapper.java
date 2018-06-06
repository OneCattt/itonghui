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
     * <功能详细描述>根据cityId查询探店
     * @param param1:cityId param2:page
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<InterviewParentEntity> getInterviewByCityId(int param1, int param2);

    /**
     * 
     * <功能详细描述>根据code查询探店
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<InterviewParentEntity> getInterviewByCode(String code);

    /**
     * 
     * <功能详细描述>根据interviewId查询探店
     * @param interviewId
     * @return
     * @see [类、类#方法、类#成员]
     */
    InterviewParentEntity getInterviewByInterviewId(int interviewId);
}
