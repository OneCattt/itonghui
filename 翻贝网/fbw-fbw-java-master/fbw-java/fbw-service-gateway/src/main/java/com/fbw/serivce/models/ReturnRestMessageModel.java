package com.fbw.serivce.models;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;

public class ReturnRestMessageModel
{
    public static String failedMessage(String message, String errorCode)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", "failed");
        map.put("errorCode", errorCode);
        map.put("message", message);
        JSONArray jsonArray = JSONArray.fromObject(map);
        return jsonArray.toString().substring(1, jsonArray.toString().length() - 1);
    }
}
