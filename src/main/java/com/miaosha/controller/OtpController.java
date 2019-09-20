package com.miaosha.controller;

import com.alibaba.fastjson.JSONObject;
import com.miaosha.result.BusinessException;
import com.miaosha.result.CommonReturnType;
import com.miaosha.result.JsonResult;
import com.miaosha.result.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Random;

/**
 * @program: miaosha
 * @description: 模拟手机发送短信码
 * @author: cxy
 * @create: 2019-09-17 14:01
 */
@RestController
public class OtpController {
  @Autowired
  HttpServletRequest httpServletRequest;

  /*@RequestMapping(value = "/user/getOTP",method = RequestMethod.POST,consumes="application/x-www-form-urlencoded")
  public JSONObject getOtp(@RequestParam(value = "telphone")String telphone){
    //1.按照一定规则生成otp验证码
    Random random = new Random();
    int i = random.nextInt(99999);
    i+=1000;
    String s = String.valueOf(i);
    //2.将otp验证码和手机号关联(在企业级开发中是保存在redis中,redis中有map的数据结构,刷新验证码时,旧的被覆盖,而且还可以定时)
    //这里我们简化,放在session中
  httpServletRequest.getSession().setAttribute("telphone",s);
//3.将otp验证码通过短信通道发送给用户
    System.out.println("telphone:"+telphone+";otpcode"+s);
    return JsonResult.getResult(s, Message.SUCCESS,"获取otp验证码");
  }*/
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Object handerException(HttpServletRequest request,Exception e){
    HashMap<String, Object> objectObjectHashMap = new HashMap<>();
    if(e instanceof BusinessException) {
      BusinessException businessException = (BusinessException) e;
      objectObjectHashMap.put("errorcode", businessException.getErrorCode());
      objectObjectHashMap.put("errorMsg", businessException.getDescribe());
    }
    CommonReturnType commonReturnType = new CommonReturnType();
    commonReturnType.setData(objectObjectHashMap);
    commonReturnType.setStatus("fail");

    return commonReturnType;
  }
}
