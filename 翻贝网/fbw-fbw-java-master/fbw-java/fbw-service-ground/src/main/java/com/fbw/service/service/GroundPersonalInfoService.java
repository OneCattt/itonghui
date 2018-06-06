package com.fbw.service.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fbw.service.entity.ground.GroundPersonPlanHistoryConfEntity;
import com.fbw.service.entity.ground.GroundPersonalInfoBusinEntity;
import com.fbw.service.entity.ground.GroundPlanConfEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.CommonFeignClient;
import com.fbw.service.feign.UserFeignClient;
import com.fbw.service.mapper.GroundPersonPlanHistoryConfEntityMapper;
import com.fbw.service.mapper.GroundPersonalInfoBusinMapper;
import com.fbw.service.mapper.GroundPlanConfEntityMapper;

@Service
public class GroundPersonalInfoService
{
    @Autowired
    private GroundPersonalInfoBusinMapper groundPersonalInfoBusinMapper;

    @Autowired
    private GroundPersonPlanHistoryConfEntityMapper groundPersonPlanHistoryConfEntityMapper;

    @Autowired
    private GroundPlanConfEntityMapper groundPlanConfEntityMapper;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private CommonFeignClient commonFeignClient;

    public List<GroundPersonalInfoBusinEntity> selectByCityId(Map<String, Object> map)
    {
        return groundPersonalInfoBusinMapper.selectByCityId(map);
    }

    public int saveGroundPersonalInfo(GroundPersonalInfoBusinEntity groundPersonalInfoBusinEntity)
    {
        return groundPersonalInfoBusinMapper.saveGroundPersonalInfo(groundPersonalInfoBusinEntity);
    }

    public int updatePersonalGroundStatus(String salesmanId, String mobile)
    {
        return groundPersonalInfoBusinMapper.updatePersonalGroundStatus(salesmanId, mobile);
    }

    public int updatePersonalGroundQrCode(String salesmanId, String salesmanQrCode)
    {
        return groundPersonalInfoBusinMapper.updatePersonalGroundQrCode(salesmanId, salesmanQrCode);
    }

    /**
     * 判断手机号是否存在或者是否已是地推人员 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int checkMobile(String mobile)
    {
        int status = 0;
        // 判断手机号是否是注册用户
        boolean flag = userFeignClient.selectIsExistMobile(mobile);
        if (flag == false)
        {
            status = -1;
            return status;
        }
        // 判断手机号是否已是地推用户
        int flag1 = groundPersonalInfoBusinMapper.selectIsExistMobile(mobile);
        if (flag1 > 0)
        {
            status = -2;
            return status;
        }
        return status;
    }

    /**
     * 新建个人地推 <功能详细描述>
     * @param cityId
     * @param name
     * @param mobile
     * @param remark
     * @param groundId
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public int saveGroundPersonal(String cityId, String name, String mobile, String remark, String groundId)
            throws InnerException
    {
        GroundPersonalInfoBusinEntity groundPersonalInfoBusinEntity = new GroundPersonalInfoBusinEntity();
        groundPersonalInfoBusinEntity.setCityId(cityId);
        groundPersonalInfoBusinEntity.setSalesmanName(name);
        groundPersonalInfoBusinEntity.setSalesmanMobile(mobile);
        groundPersonalInfoBusinEntity.setGroundRemark(remark);
        String salesmanId = commonFeignClient.getrandomstring(2) + mobile.substring(8, 11);
        groundPersonalInfoBusinEntity.setSalesmanId(salesmanId);
        int flag2 = groundPersonalInfoBusinMapper.saveGroundPersonalInfo(groundPersonalInfoBusinEntity);
        GroundPersonPlanHistoryConfEntity groundPersonPlanHistoryConfEntity = new GroundPersonPlanHistoryConfEntity();
        GroundPlanConfEntity groundPlanConfEntity = groundPlanConfEntityMapper
                .selectByGroundId(Integer.parseInt(groundId));
        if (null == groundPlanConfEntity)
        {
            throw new InnerException("没有该地推方案");
        }
        groundPersonPlanHistoryConfEntity.setSalesmanId(salesmanId);
        groundPersonPlanHistoryConfEntity.setGroundId(groundId);
        groundPersonPlanHistoryConfEntity.setBalanceDescribe(groundPlanConfEntity.getBalanceDescribe());
        groundPersonPlanHistoryConfEntity.setGroundName(groundPlanConfEntity.getGroundName());
        groundPersonPlanHistoryConfEntity.setValidGroundDescribe(groundPlanConfEntity.getValidateGroundDefine());
        groundPersonPlanHistoryConfEntity.setGroundBalanceDescribe(groundPlanConfEntity.getGroundBalanceDescribe());
        int flag3 = groundPersonPlanHistoryConfEntityMapper.saveGroundPersonPlan(groundPersonPlanHistoryConfEntity);
        if (1 != flag2 || 1 != flag3)
        {
            throw new InnerException("存储失败！");
        }

        return 1;

    }

    public int updatePersonalGroundRegisterInfo(GroundPersonalInfoBusinEntity groundPersonalInfoBusinEntity)
    {
        return groundPersonalInfoBusinMapper.updatePersonalGroundRegisterInfo(groundPersonalInfoBusinEntity);
    }

    public int updatePersonalGroundWithVaildRegAndRegInfo(String salesManId, int registerAmount, int newRegisterAmount)
    {
        return groundPersonalInfoBusinMapper.updatePersonalGroundWithVaildRegAndRegInfo(salesManId, registerAmount,
                newRegisterAmount);
    }

    public GroundPersonalInfoBusinEntity queryPersonalGroundBySalesManId(String salesManId)
    {
        return groundPersonalInfoBusinMapper.queryPersonalGroundBySalesManId(salesManId);
    }

}
