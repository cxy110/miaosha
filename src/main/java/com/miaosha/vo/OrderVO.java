package com.miaosha.vo;

import java.math.BigDecimal;
import java.time.Period;

/**
 * @program: miaosha
 * @description: 订单的vo对象
 * @author: cxy
 * @create: 2019-09-26 09:14
 */
public class OrderVO {
  private Integer userId;
  private Integer itemId;
  private BigDecimal orderPrice;
  private Integer amount;
  private  BigDecimal orderAmount;
  private String id;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getItemId() {
    return itemId;
  }

  public void setItemId(Integer itemId) {
    this.itemId = itemId;
  }

  public BigDecimal getOrderPrice() {
    return orderPrice;
  }

  public void setOrderPrice(BigDecimal orderPrice) {
    this.orderPrice = orderPrice;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public BigDecimal getOrderAmount() {
    return orderAmount;
  }

  public void setOrderAmount(BigDecimal orderAmount) {
    this.orderAmount = orderAmount;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
