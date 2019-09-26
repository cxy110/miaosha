package com.miaosha.result;

import com.alibaba.fastjson.JSONObject;

/**
 * @program: miaosha
 * @description: 返回的数据及结果
 * @author: cxy
 * @create: 2019-09-16 14:25
 */
public class JsonResult {
  public  JsonResult(){}
  public static JSONObject getResult(Object data, Message msg, String Describe){
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("data",data);
    jsonObject.put("describe",Describe+msg.getDefaultDescribe());
    jsonObject.put("message",msg);
    jsonObject.put("code",msg.getCode());
    return jsonObject;

  }
  public static JSONObject getResult(Object data, Message msg){
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("data",data);
    jsonObject.put("message",msg);
    jsonObject.put("code",msg.getCode());
    return jsonObject;

  }
  public static JSONObject getResult( Message msg, String Describe){
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("describe",Describe+msg.getDefaultDescribe());
    jsonObject.put("message",msg);
    jsonObject.put("code",msg.getCode());
    System.out.println(jsonObject);
    return jsonObject;

  }
  public static JSONObject getResult( Message msg){
    JSONObject jsonObject = new JSONObject();

    jsonObject.put("describe",msg.getDefaultDescribe());
    jsonObject.put("message",msg);
    jsonObject.put("code",msg.getCode());
    return jsonObject;

  }
}
