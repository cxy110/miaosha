package com.miaosha.bo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @program: miaosha
 * @description: 商品信息bo(模型)表
 * @author: cxy
 * @create: 2019-09-23 11:43
 */
public class ItemBo {
  private Integer id;
  //商品名称
  @NotBlank(message = "商品名称不能为空")
  private String title;
  //商品价格
  @NotNull(message = "价格必须填写")
  @Min(value = 0, message = "商品价格必须大于0")
  private BigDecimal price;
  //商品库存
  @NotNull(message = "库存必须填写")
  private  Integer stock;
  //商品描述
  @NotNull(message = "商品描述必须有")
  private String description;
  //商品销量
  private  Integer sales;
  //商品描述图片的url
  @NotNull(message = "商品图片信息必须有")
  private  String imgUrl;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getSales() {
    return sales;
  }

  public void setSales(Integer sales) {
    this.sales = sales;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }
}
