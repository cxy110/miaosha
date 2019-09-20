package com.miaosha.controller;

import com.alibaba.fastjson.JSONObject;
import com.miaosha.result.BusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: miaosha
 * @description: 全局异常处理类
 * @author: cxy
 * @create: 2019-09-17 10:03
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
/*
在这里@ExceptionHandler后面的异常类,表明这个方法处理的是,这个级别的异常类或其之类
* */
  @ExceptionHandler(Exception.class)
  public JSONObject doException(Exception e){
    JSONObject jsonObject = new JSONObject();
    if(e instanceof BusinessException){
      BusinessException businessException=(BusinessException) e;

      jsonObject.put("code",businessException.getErrorCode());
      jsonObject.put("reason",businessException.getDescribe());

    }
    return jsonObject;
  }
  @ExceptionHandler(RuntimeException.class)
  public JSONObject GETException(Exception e){
    JSONObject jsonObject = new JSONObject();

      jsonObject.put("code","10101");
      jsonObject.put("reason","存入失败");


    return jsonObject;
  }
}
