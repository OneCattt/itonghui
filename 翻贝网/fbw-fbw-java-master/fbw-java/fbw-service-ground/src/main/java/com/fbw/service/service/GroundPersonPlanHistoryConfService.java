package com.fbw.service.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fbw.service.entity.ground.GroundPersonPlanHistoryConfEntity;
import com.fbw.service.entity.ground.GroundPlanConfEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.mapper.GroundPersonPlanHistoryConfEntityMapper;
import com.fbw.service.mapper.GroundPlanConfEntityMapper;

@Service
public class GroundPersonPlanHistoryConfService
{
    @Autowired
    private GroundPersonPlanHistoryConfEntityMapper groundPersonPlanHistoryConfEntityMapper;

    @Autowired
    private GroundPlanConfEntityMapper groundPlanConfEntityMapper;

    /**
     * 通过salesmanID查询两个最近该用户地推方案 <功能详细描述>
     * @param salesmanId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<GroundPersonPlanHistoryConfEntity> selectBySalesmanId(String salesmanId)
    {
        return groundPersonPlanHistoryConfEntityMapper.selectBySalesmanId(salesmanId);
    }

    /**
     * 存储用户所选择地推方案 <功能详细描述>
     * @param groundPersonPlanHistoryConfEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int saveGroundPersonPlan(GroundPersonPlanHistoryConfEntity groundPersonPlanHistoryConfEntity)
    {
        return groundPersonPlanHistoryConfEntityMapper.saveGroundPersonPlan(groundPersonPlanHistoryConfEntity);
    }

    /**
     * 通过salesmanId查询最新地推方案详细信息 <功能详细描述>
     * @param salesmanId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public GroundPersonPlanHistoryConfEntity queryGroundPersonPlanHistoryBySalesId(String salesmanId)
    {
        return groundPersonPlanHistoryConfEntityMapper.queryGroundPersonPlanHistoryBySalesId(salesmanId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = InnerException.class)
    public boolean changeSalesmanGroundPlan(String salesmanId, String groundId) throws InnerException
    {
        // 更新当前最新方案截止时间
        GroundPersonPlanHistoryConfEntity groundPersonPlanHistory = groundPersonPlanHistoryConfEntityMapper
                .queryGroundPersonPlanHistoryBySalesId(salesmanId);
        if (null == groundPersonPlanHistory)
        {
            return false;
        }
        int flag = groundPersonPlanHistoryConfEntityMapper.updateLatestInvalidDate(groundPersonPlanHistory.getId());
        if (1 != flag)
        {
            throw new InnerException("插入失败");
        }
        // 通过地推方案id查询地推方案信息
        GroundPlanConfEntity groundPlanConfEntity = groundPlanConfEntityMapper
                .selectByGroundId(Integer.parseInt(groundId));
        if (null == groundPlanConfEntity)
        {
            return false;
        }
        // 插入当前地推人员最新地推方案
        GroundPersonPlanHistoryConfEntity groundPersonPlanHistoryConfEntity = new GroundPersonPlanHistoryConfEntity();
        groundPersonPlanHistoryConfEntity.setSalesmanId(salesmanId);
        groundPersonPlanHistoryConfEntity.setGroundId(groundId);
        groundPersonPlanHistoryConfEntity.setBalanceDescribe(groundPlanConfEntity.getBalanceDescribe());
        groundPersonPlanHistoryConfEntity.setGroundName(groundPlanConfEntity.getGroundName());
        groundPersonPlanHistoryConfEntity.setValidGroundDescribe(groundPlanConfEntity.getValidateGroundDefine());
        groundPersonPlanHistoryConfEntity.setGroundBalanceDescribe(groundPlanConfEntity.getGroundBalanceDescribe());
        int flag1 = groundPersonPlanHistoryConfEntityMapper.saveGroundPersonPlan(groundPersonPlanHistoryConfEntity);
        if (1 != flag1)
        {
            throw new InnerException("插入失败");
        }
        return true;

    }

    /**
     * 更新地推收入 <功能详细描述>
     * @param id
     * @param groundFee
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean updateGroundFee(Integer id, BigDecimal groundFee)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("groundFee", groundFee);
        int flag = groundPersonPlanHistoryConfEntityMapper.updateGroundFee(map);
        if (flag == 1)
        {
            return true;
        }
        return false;
    }

}
