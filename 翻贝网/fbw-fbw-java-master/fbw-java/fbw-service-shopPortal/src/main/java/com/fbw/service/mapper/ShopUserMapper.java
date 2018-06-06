package com.fbw.service.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.fbw.service.entity.portal.ShopUserEntity;

/**
 * 
 * <功能详细描述>shop用户mapper
 * @author FBW0115
 * @version [版本号, 2017年9月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface ShopUserMapper
{
    /**
     * 
     * <功能详细描述>查询用户是否存在
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Select("select count(1) from t_portal_shop_user where mobile = #{mobile} and status = 1")
    int getShopUserByMobile(String mobile);

    /**
     * 
     * <功能详细描述>校验密码
     * @param mobile
     * @see [类、类#方法、类#成员]
     */
    @Select("select count(1) from t_portal_shop_user where mobile = #{param1} and password = #{param2} and status = 1")
    int checkShopUserPwd(String param1, String param2);

    /**
     * 
     * <功能详细描述>查询用户信息
     * @param mobile
     * @see [类、类#方法、类#成员]
     */
    @Select("select user_id as userId,mobile,user_name as userName,password,status from t_portal_shop_user where mobile = #{mobile} and status = 1")
    ShopUserEntity getShopUserInfoByMobile(String mobile);

    /**
     * 
     * <功能详细描述>更新密码
     * @param mobile
     * @param password
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Update("update t_portal_shop_user set password = #{param2} where mobile = #{param1}")
    int updateShopUserPwd(String param1, String param2);

    /**
     * 
     * <功能详细描述>插入用户
     * @param entity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Insert("insert into t_portal_shop_user(mobile,user_name,status) values(#{mobile},#{userName},1)")
    int insertShopUser(ShopUserEntity entity);

    @Select("select last_insert_id()")
    int getLastInsertId();
}
