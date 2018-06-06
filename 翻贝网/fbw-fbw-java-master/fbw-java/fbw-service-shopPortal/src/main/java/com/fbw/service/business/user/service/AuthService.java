package com.fbw.service.business.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fbw.service.business.login.service.LoginService;
import com.fbw.service.business.user.entity.AuthReqEntity;
import com.fbw.service.contents.ErrorMsgConstant;
import com.fbw.service.entity.portal.RolePrivilegeEntity;
import com.fbw.service.entity.portal.ShopUserEntity;
import com.fbw.service.entity.portal.UserRoleEntity;
import com.fbw.service.exception.InnerCode;
import com.fbw.service.exception.InnerException;
import com.fbw.service.mapper.ShopUserMapper;
import com.fbw.service.mapper.ShopUserPrivilegeMapper;
import com.fbw.service.util.NomalUtil;

/**
 * 
 * <功能详细描述>权限service
 * @author FBW0115
 * @version [版本号, 2017年9月13日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class AuthService
{

    @Autowired
    private ShopUserPrivilegeMapper shopUserPrivilegeMapper;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ShopUserMapper shopUserMapper;

    /**
     * 
     * <功能详细描述>添加子账号
     * @param entity
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = InnerException.class)
    public void saveAccount(AuthReqEntity entity) throws InnerException
    {
        // 验证短信验证码
        loginService.checkSmsCode(entity.getSonMobile(), entity.getCode());
        // 查询用户信息
        ShopUserEntity shopUserInfo = shopUserMapper.getShopUserInfoByMobile(entity.getSonMobile());
        if (NomalUtil.isNullOrEmpty(shopUserInfo))
        {
            // 插入用户信息
            shopUserInfo = new ShopUserEntity();
            shopUserInfo.setMobile(entity.getSonMobile());
            shopUserInfo.setUserName(entity.getSonMobile());
            shopUserMapper.insertShopUser(shopUserInfo);
            int userId = shopUserMapper.getLastInsertId();
            shopUserInfo.setUserId(userId);
        }
        // 验证该角色是否存在
        checkUserRole(entity.getShopId(), shopUserInfo.getUserId());
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setShopId(Integer.parseInt(entity.getShopId()));
        userRoleEntity.setShopName(entity.getShopName());
        userRoleEntity.setUserId(shopUserInfo.getUserId());
        userRoleEntity.setRoleType(0);
        // 添加角色
        shopUserPrivilegeMapper.insertUserRole(userRoleEntity);
        int roleId = shopUserPrivilegeMapper.getLastInsertId();
        RolePrivilegeEntity rolePrivilegeEntity = new RolePrivilegeEntity();
        rolePrivilegeEntity.setRoleId(roleId);
        // 添加权限
        auth(rolePrivilegeEntity, entity.getPayCashier());
        auth(rolePrivilegeEntity, entity.getEditEvent());
        auth(rolePrivilegeEntity, entity.getOrderManager());
        auth(rolePrivilegeEntity, entity.getCommentManager());
        auth(rolePrivilegeEntity, entity.getMoneyManager());
        auth(rolePrivilegeEntity, entity.getEventCalendar());
        auth(rolePrivilegeEntity, entity.getGroundManager());
        auth(rolePrivilegeEntity, entity.getExclusiveQR());
        auth(rolePrivilegeEntity, entity.getAdmin());

    }

    /**
     * 
     * <功能详细描述>编辑权限
     * @param entity
     * @see [类、类#方法、类#成员]
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = InnerException.class)
    public void editUserAuth(AuthReqEntity entity)
    {
        shopUserPrivilegeMapper.deleteRokePrivilegeByRoleId(Integer.parseInt(entity.getRoleId()));
        RolePrivilegeEntity rolePrivilegeEntity = new RolePrivilegeEntity();
        rolePrivilegeEntity.setRoleId(Integer.parseInt(entity.getRoleId()));
        auth(rolePrivilegeEntity, entity.getPayCashier());
        auth(rolePrivilegeEntity, entity.getEditEvent());
        auth(rolePrivilegeEntity, entity.getOrderManager());
        auth(rolePrivilegeEntity, entity.getCommentManager());
        auth(rolePrivilegeEntity, entity.getMoneyManager());
        auth(rolePrivilegeEntity, entity.getEventCalendar());
        auth(rolePrivilegeEntity, entity.getGroundManager());
        auth(rolePrivilegeEntity, entity.getExclusiveQR());
        auth(rolePrivilegeEntity, entity.getAdmin());

    }

    /**
     * 
     * <功能详细描述>插入权限
     * @param entity
     * @param privilegeId
     * @see [类、类#方法、类#成员]
     */
    private void auth(RolePrivilegeEntity entity, String privilegeId)
    {
        if (!NomalUtil.isNullOrEmpty(privilegeId))
        {
            entity.setPrivilegeId(Integer.parseInt(privilegeId));
            shopUserPrivilegeMapper.insertRokePrivilege(entity);
        }

    }

    /**
     * 
     * <功能详细描述>校验角色是否存在
     * @param shopId
     * @param userId
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private void checkUserRole(String shopId, Integer userId) throws InnerException
    {
        int num = shopUserPrivilegeMapper.checkUserRole(Integer.parseInt(shopId), userId);
        if (0 != num)
        {
            throw new InnerException(ErrorMsgConstant.USER_ROLE_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.USER_ROLE_FAIL));
        }
    }

}
