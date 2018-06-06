package com.fbw.service.business.user.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fbw.service.entity.common.StsEntity;

/**
 * Oss上传图片service层 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年10月21日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class OssService
{
    private final static String URL = "http://leichu.wang:9150/console/oss/get";

    private final static String REQUEST_METHOD = "GET";

    /**
     * 上传图片 <功能详细描述>
     * @return
     * @throws IOException
     * @throws JSONException
     * @see [类、类#方法、类#成员]
     */
    public StsEntity uploadImg() throws JSONException, IOException
    {
        URL url = new URL(URL);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(10000);
        conn.setRequestMethod(REQUEST_METHOD);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        // 输出返回结果
        InputStream input = conn.getInputStream();

        int resLen = 0;

        byte[] res = new byte[1024];

        StringBuilder sb = new StringBuilder();
        while ((resLen = input.read(res)) != -1)
        {
            sb.append(new String(res, 0, resLen));
        }

        String jsonStr = "[" + sb.toString() + "]";

        // String转换成JSON
        JSONArray jsonArray = new JSONArray(jsonStr);
        StsEntity stsEntity = new StsEntity();
        for (int i = 0; i < jsonArray.length(); i++)
        {

            JSONObject jsonObject = new JSONObject(jsonArray.getString(i));
            stsEntity.setAccessKeySecret((String) jsonObject.get("AccessKeySecret"));
            stsEntity.setAccessKeyId((String) jsonObject.get("AccessKeyId"));
            stsEntity.setSecurityToken((String) jsonObject.get("SecurityToken"));
        }
        return stsEntity;

    }

}
