package com.miaosha.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: miaosha
 * @description: validator校验结果
 * @author: cxy
 * @create: 2019-09-20 16:13
 */
public class ValidationResult {
  //校验结果是否有错
  private  boolean hasError=false;
  //存放错误信息的map
  private Map<String,String>errorMsgMap=new HashMap<>();

  public boolean isHasError() {
    return hasError;
  }

  public void setHasError(boolean hasError) {
    this.hasError = hasError;
  }

  public Map<String, String> getErrorMsgMap() {
    return errorMsgMap;
  }

  public void setErrorMsgMap(Map<String, String> errorMsgMap) {
    this.errorMsgMap = errorMsgMap;
  }
  //实现通用的通过格式化字符粗信息获取错误结果的msg方法
  public String getErrMsg(){
    return StringUtils.join(errorMsgMap.values().toArray(),",");
  }
}
