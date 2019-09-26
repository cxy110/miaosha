package com.miaosha.result;
/*
通用的自定义输出码类型
* */
public enum Message {
  //30000开头为交易性异常
  STOCK_NOT_ENOUGH("30001","库存不足"),
  SUCCESS("2000","成功"),
  FAILURE("100","失败"),
 USER_NOT_EXIST("10001","用户不存在"),
  LOGININ_SUCCESS("20010","登录成功"),
  //通用错误码,具体描述可以由开发人员设置,对应方法就是下面的setDefaultDescribe方法
  PARAMETER_VALIDATION_ERROR("00001","参数不合法"),
  USER_NOT_EXITS("20001","用户不存在"),
  USER_LOGIN_ERROE("20002","用户手机号密码错误");
  private String code;
  private  String defaultDescribe;
  private Message(String code,String defaultDescribe){
    this.code=code;
    this.defaultDescribe=defaultDescribe;
  }

  public String getCode() {
    return code;
  }

  public String getDefaultDescribe() {
    return defaultDescribe;
  }
  public Message setDefaultDescribe(String describe){
    this.defaultDescribe=describe;
    return this;
  }
}
