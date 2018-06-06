package com.fbw.service.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.user.UserShopFeedbackBusinEntity;

/**
 * 用户商家反馈 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月2日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface UserShopFeedbackBusinEntityMapper
{

    /**
     * 插入用户反馈信息 <功能详细描述>
     * @param record
     * @return
     * @see [类、类#方法、类#成员]
     */
    int insertUserFeedBack(UserShopFeedbackBusinEntity record);

}