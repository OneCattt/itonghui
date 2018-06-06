package com.fbw.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.fbw.service.entity.portal.RolePrivilegeEntity;
import com.fbw.service.entity.portal.UserRoleEntity;

/**
 * 
 * <功能详细描述>用户权限mapper
 * @author FBW0115
 * @version [版本号, 2017年9月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface ShopUserPrivilegeMapper
{

    /**
     * 
     * <功能详细描述>查询用户角色
     * @param userId
     * @see [类、类#方法、类#成员]
     */
    @Select("select role_id as roleId,shop_id as shopId,shop_name as shopName,user_id as userId,role_type as roleType from t_portal_user_role where user_id = #{userId}")
    List<UserRoleEntity> getUserRoleByUserId(int userId);

    /**
     * 
     * <功能详细描述>
     * @param shopId
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Select("select count(1) from t_portal_user_role where shop_id = #{param1} and user_id = #{param2}")
    int checkUserRole(int param1, int param2);

    /**
     * 
     * <功能详细描述>查询角色权限
     * @param roleId
     * @see [类、类#方法、类#成员]
     */
    @Select("select t.role_id as roleId,t.privilege_id as privilegeId,f.privilege_name as privilegeName,f.privilege_describe as privilegeDescribe from t_portal_role_privilege t,t_portal_privilege_conf f where t.privilege_id = f.privilege_id and t.role_id = #{roleId}")
    List<RolePrivilegeEntity> getRolePrivilegeByRoleId(int roleId);

    /**
     * 
     * <功能详细描述>删除商家角色
     * @param roleId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Delete("delete from t_portal_user_role where role_id = #{roleId}")
    int deleteUserRole(int roleId);

    /**
     * 
     * <功能详细描述>插入商家角色
     * @param entity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Insert("insert into t_portal_user_role(shop_id,shop_name,user_id,role_type) values(#{shopId},#{shopName},#{userId},#{roleType})")
    // @SelectKey(statement = "select last_insert_id()", keyProperty =
    // "role_id", before = false, resultType = int.class)
    int insertUserRole(UserRoleEntity entity);

    @Select("select last_insert_id()")
    int getLastInsertId();

    /**
     * 
     * <功能详细描述>删除角色权限
     * @param roleId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Delete("delete from t_portal_role_privilege where role_id = #{roleId}")
    int deleteRokePrivilegeByRoleId(int roleId);

    /**
     * 
     * <功能详细描述>插入角色权限
     * @param entity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Insert("insert into t_portal_role_privilege(role_id,privilege_id) values(#{roleId},#{privilegeId})")
    int insertRokePrivilege(RolePrivilegeEntity entity);
}
