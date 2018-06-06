package com.fbw.service.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.user.UserCommentBannerConfEntity;
import com.fbw.service.entity.user.UserCommentBusinEntity;

/**
 * 用户评论mapper <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月2日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface UserCommentBusinEntityMapper
{
    /**
     * 获取当前用户某一个订单的评论 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserCommentBusinEntity getOneUserCommentInfo(String param1);

    /**
     * 获取一个最近的最好评论 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserCommentBusinEntity getOneRecentShopComment(Integer param1);

    /**
     * 获取一个最近的最好评论的全部信息 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserCommentBusinEntity getOneRecentShopCommentMore(Integer param1);

    /**
     * 获取一个店铺评论的总数量 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    int getOneShopCommentNum(Integer param1);

    /**
     * 获取当前店铺所有评论 <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserCommentBusinEntity> getOneShopAllComment(Map<String, Object> map);

    /**
     * 获取当前店铺带图评论的总数量 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    int getOneShopAllPicNum(Integer param1);

    /**
     * 获取当前店铺所有带图片评论 <功能详细描述>
     * @param param1
     * @param param2
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserCommentBusinEntity> getOneShopAllPicComment(Integer param1, Integer param2);

    /**
     * 插入用户评论 <功能详细描述>
     * @param param1
     * @param param2
     * @return
     * @see [类、类#方法、类#成员]
     */
    int insertUserComment(UserCommentBusinEntity userCommentBusinEntity);

    /**
     * 获取当前店铺平均分 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserCommentBusinEntity getOneShopAvgScore(Integer param1);

    /**
     * 获取一个评分下的评论标签 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    String getOneScoreLabel(Integer param1);

    /**
     * 获取评论banner <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserCommentBannerConfEntity> getCommentBanner();

    /**
     * 通过bannerId获取评论banner <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserCommentBannerConfEntity getCommentBannerById(Integer id);

    /**
     * 获取每个评论标签下的数量 <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    int getCommentBannerNumber(Map<String, Object> map);
}