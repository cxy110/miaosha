package com.miaosha.bo;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @program: miaosha
 * @description: 秒杀活动的模型
 * @author: cxy
 * @create: 2019-09-26 17:50
 */
public class PromoBo {
  private  Integer id;
  //秒杀活动状态 1为未开始,2为进行中,3为已结束
  private Integer status;
  //秒杀活动的商品名称
 private String promoName;
 //秒杀商品id
  private Integer itemID;
  //秒杀商品的开始时间
  private DateTime startDate;
  //秒杀结束时间
  private DateTime endDate;
  //秒杀商品价格
  private BigDecimal promoItemPrice;

  public Integer getId() {
    return id;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public DateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(DateTime endDate) {
    this.endDate = endDate;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPromoName() {
    return promoName;
  }

  public void setPromoName(String promoName) {
    this.promoName = promoName;
  }

  public Integer getItemID() {
    return itemID;
  }

  public void setItemID(Integer itemID) {
    this.itemID = itemID;
  }

  public DateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(DateTime startDate) {
    this.startDate = startDate;
  }

  public BigDecimal getPromoItemPrice() {
    return promoItemPrice;
  }

  public void setPromoItemPrice(BigDecimal promoItemPrice) {
    this.promoItemPrice = promoItemPrice;
  }
}
