package com.fbw.service.service;

import com.fbw.service.exception.InnerException;

/**
 * <功能详细描述>商户信息service接口
 * @author FBW0115
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface TaskService
{

    /**
     * 
     * <功能详细描述>每天凌晨更新商家是否是翻倍日
     * @return
     * @see [类、类#方法、类#成员]
     */
    String updateShopsIsEvent();

    /**
     * 
     * <功能详细描述>每天凌晨更新商家新店状态
     * @see [类、类#方法、类#成员]
     */
    String updateShopsIsNew();

    /**
     * <功能详细描述>每天凌晨更新商家收藏量
     * @return
     * @see [类、类#方法、类#成员]
     */
    String updateShopsCollect();

    /**
     * <功能详细描述>每天凌晨更新商家浏览量
     * @return
     * @see [类、类#方法、类#成员]
     */
    String updateShopsBrowse();

    /**
     * <功能详细描述>每天凌晨更新探店浏览量
     * @return
     * @see [类、类#方法、类#成员]
     */
    String updateInterviewBrowse();

    /**
     * 
     * <功能详细描述>每天更新订单数量
     * @see [类、类#方法、类#成员]
     */
    String updateShopsOrderNum();

    /**
     * 
     * <功能详细描述>每天更新评价信息（评价数量、评价平均分、评价总分）
     * @see [类、类#方法、类#成员]
     */
    String updateShopsComment();

    /**
     * 自动评价功能 <功能详细描述>
     * @param orderNumber
     * @param userId
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    void autoInsertUserComment(String orderNumber, Integer userId) throws InnerException;

}
