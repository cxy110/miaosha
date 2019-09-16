package com.miaosha.tool.result;

public enum Message {
  SUCCESS("200","成功"),
  FAILURE("100","失败");
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
}
