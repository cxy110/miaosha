package com.miaosha.result;

public interface CommonError {
  public String getErrorCode();
  public String getDescribe();
  public CommonError setDescribe(String errorMsg);
}
