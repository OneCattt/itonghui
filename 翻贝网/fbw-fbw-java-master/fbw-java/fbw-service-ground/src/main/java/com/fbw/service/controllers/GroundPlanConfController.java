package com.fbw.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.entity.ground.GroundPlanConfEntity;
import com.fbw.service.service.GrounPlanConfService;

@RestController
@RequestMapping("/GroundPlanConf")
public class GroundPlanConfController extends BaseController
{
    @Autowired
    private GrounPlanConfService grounPlanConfService;

    @RequestMapping("/getGroundNameByCityId")
    public List<GroundPlanConfEntity> getGroundNameByCityId(String cityId)
    {
        return grounPlanConfService.selectByCityId(cityId);

    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        // TODO Auto-generated method stub

    }

}
