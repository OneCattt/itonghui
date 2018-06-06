package com.fbw.service.serivces;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fbw.service.contents.CommonConstant;
import com.fbw.service.util.NomalUtil;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

@Service
public class SendSmsMessageService
{

    /**
     * 阿里大于短信key
     */
    @Value("23433886")
    private String ALIDAYU_APPKEY;

    /**
     * 阿里大于短信密匙
     */
    @Value("d74fcc6ad43662ea3d1f30557ebd3a99")
    private String ALIDAYU_SECRETKEY;

    /**
     * 短信发送类型 默认值
     */
    private final String SMSTYPE = "normal";

    /**
     * 短信签名
     */
    private final String SMSFREESIGNNAME = "翻贝网";

    /**
     * 审核通过的来电显示号码
     */
    final String SHOW_NUM = "95213176";

    /**
     * 语音通知url
     */
    final String VOICE_URL = "http://audio.253.com/";

    /**
     * 语音通知账号名
     */
    final String VOICE_ACCOUNT_NAME = "YC2113861";

    /**
     * 语音通知账号KEY值
     */
    final String VOICE_ACCOUNT_KEY = "dc3230099de0cba84d573cb8ddd0874a";

    /**
     * 语音通知账号密码
     */
    final String VOICE_ACCOUNT_PASSWORD = "x6dDV7ftbZa805";

    /**
     * <一句话功能简述>
     * 
     * <功能详细描述>
     * @param mobile 手机号
     * @param smsEntity 短信内容
     * @param smsmodel 短信模板
     * @return
     * @throws ApiException
     * @see [类、类#方法、类#成员]
     */
    public void SendCode(String mobile, String smsmodel, String smsParam) throws ApiException
    {
        TaobaoClient client = new DefaultTaobaoClient(CommonConstant.COMMON_AILIYU_SMS_ADDRESS, ALIDAYU_APPKEY,
                ALIDAYU_SECRETKEY);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("");
        req.setSmsType(SMSTYPE);
        req.setSmsFreeSignName(SMSFREESIGNNAME);
        req.setSmsParamString(smsParam);
        req.setRecNum(mobile);
        req.setSmsTemplateCode(smsmodel);
        AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    /**
     * 
     * <功能详细描述> 发送语音验证码
     * @param mobile 手机号
     * @param smsCode 短信验证码
     * @see [类、类#方法、类#成员]
     */
    public void sendVoiceCode(String mobile, String smsCode)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date()); // 当前时间戳
        String content = NomalUtil.MD5(VOICE_ACCOUNT_KEY + mobile + VOICE_ACCOUNT_PASSWORD + timestamp);
        JSONObject param = new JSONObject();
        JSONObject data = new JSONObject();
        // 必填内容
        data.put("organization", VOICE_ACCOUNT_NAME);
        data.put("phonenum", mobile);
        data.put("content", content);
        data.put("vfcode", "" + smsCode); // 验证码
        data.put("shownum", "95213176");// 审核通过的来电显示号码
        // 选填内容
        data.put("timestamp", timestamp);
        data.put("uniqueid", null);
        data.put("ringtime", null);
        data.put("maxtalktime", null);
        data.put("playnum", null);
        data.put("ivrfileid", null);

        param.put("voiceinfo", data);
        try
        {
            String rs = NomalUtil.sendVoiceMsg(VOICE_URL, param.toString());
            System.out.println("响应值:\n" + rs);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
