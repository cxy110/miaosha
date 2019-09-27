package com.miaosha.bo;

import javax.swing.*;
import java.math.BigDecimal;

/**
 * @program: miaosha
 * @description: 订单的业务模型
 * @author: cxy
 * @create: 2019-09-25 15:20
 */
public class OrderBO {
  //订单id,是以订单生成的时间为依据.如|:2019092500001
  private String id;
  //用户id
  private Integer userId;
  //商品id
  private Integer itemId;
  //商品购买时的单价
  private BigDecimal orderPrice;
  //购买的商品数量
  private Integer amount;
  //商品购买的总价
  private BigDecimal orderAmount;
  //秒杀活动id
  private Integer promoId;

  public Integer getPromoId() {
    return promoId;
  }

  public void setPromoId(Integer promoId) {
    this.promoId = promoId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

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
}
