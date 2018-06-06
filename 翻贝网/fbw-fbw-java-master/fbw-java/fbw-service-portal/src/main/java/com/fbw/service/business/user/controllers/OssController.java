package com.fbw.service.business.user.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Oss上传图片Controller
 * <功能详细描述>
 * @author  jiangruliang
 * @version  [版本号, 2017年10月21日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */

import com.fbw.service.base.BaseController;
import com.fbw.service.business.user.service.OssService;
import com.fbw.service.entity.common.StsEntity;
import com.fbw.service.entity.portal.ErrorMsgConstant;

@RestController
public class OssController extends BaseController
{
    @Autowired
    private OssService ossService;

    @RequestMapping("/v3/Oss/uploadImg")
    public Map<String, Object> uploadImg(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        StsEntity stsEntity = new StsEntity();
        try
        {
            stsEntity = ossService.uploadImg();
        }
        catch (IOException e)
        {
            getErrorLog(trackId + ":Oss uploadImg:" + "获取oss失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_OSS_FAIL);
        }
        catch (JSONException e)
        {
            getErrorLog(trackId + ":Oss uploadImg:" + "获取oss失失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_OSS_FAIL);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("stsEntity", stsEntity);
        return successData(data);

    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        // TODO Auto-generated method stub

    }

}
