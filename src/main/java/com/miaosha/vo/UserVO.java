package com.miaosha.vo;

/**
 * @program: miaosha
 * @description: 用户输出对象
 * @author: cxy
 * @create: 2019-09-16 10:47
 */
public class UserVO {
  private Integer id;
  private String name;
  private Integer age;
  private String phone;
  private Integer gender;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }
}
