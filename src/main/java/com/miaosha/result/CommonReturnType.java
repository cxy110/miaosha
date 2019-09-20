package com.miaosha.result;

/**
 * @program: miaosha
 * @description: 抛出异常的返回前端处理结果
 * @author: cxy
 * @create: 2019-09-16 17:45
 */
public class CommonReturnType {
  private String status;
  private Object data;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}
