package com.fbw.service.business.user.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.business.user.service.UserRedPacketSevice;
import com.fbw.service.entity.portal.ErrorMsgConstant;
import com.fbw.service.entity.user.UserRedPacketBusinEntity;
import com.fbw.service.util.ReqUtils;

@RestController
public class UserRedPacketController extends BaseController
{
    @Autowired
    private UserRedPacketSevice userRedPacketBusinService;

    /**
     * 获取用户可用红包列表<功能详细描述>
     * @param begin
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/userRedPacket/selectRedPacketListById", method = RequestMethod.GET)
    public Map<String, Object> selectRedPacketListById(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        int userId = ReqUtils.getInt(request, "userId");
        int begin = ReqUtils.getInt(request, "begin") * 20;
        List<UserRedPacketBusinEntity> userRedPacket = null;
        try
        {
            userRedPacket = userRedPacketBusinService.selectRedPacketListById(userId, begin);
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":user selectRedPacketListById:" + "获取用户红包列表失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_USER_REDPACKET_LIST_ERROR);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("userRedPacket", userRedPacket);
        return successData(data);
    }

    /**
     * 获取当前用户已过期、已使用红包列表<功能详细描述>
     * @param begin
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/userRedPacket/selectUsedRedPacketListById", method = RequestMethod.GET)
    public Map<String, Object> selectUsedRedPacketListById(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        int userId = ReqUtils.getInt(request, "userId");
        int begin = ReqUtils.getInt(request, "begin") * 20;
        List<UserRedPacketBusinEntity> userRedPacket = null;
        try
        {
            userRedPacket = userRedPacketBusinService.selectUsedRedPacketListById(userId, begin);
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":user selectUsedRedPacketListById:" + "获取历史红包列表失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_USER_USED_REDPACKET_LIST_ERROR);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("userRedPacket", userRedPacket);
        return successData(data);
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(UserRedPacketController.class, errorMsg);

    }

}
