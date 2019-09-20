package com.miaosha.result;

/**
 * @program: miaosha
 * @description: 抛出的异常类
 * @author: cxy
 * @create: 2019-09-16 17:05
 */
public class BusinessException extends Exception  {
  private Message message;
  //直接接受Message中的异常,构造业务异常
  public BusinessException(Message message){
    super();
    this.message=message;
  }
  //通过传递自定义异常信息,构造自定义的业务异常
  public  BusinessException(Message message,String errorMsg){
    super();
    this.message=message;
    this.message.setDefaultDescribe(errorMsg);
  }


  public String getErrorCode() {
    return this.message.getCode();
  }


  public String getDescribe() {
    return this.message.getDefaultDescribe();
  }


  public Message setDescribe(String errorMsg) {
    this.message.setDefaultDescribe(errorMsg);
    return this.message;
  }
}
