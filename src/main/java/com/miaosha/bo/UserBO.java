package com.miaosha.bo;

/**
 * @program: miaosha
 * @description: 用户领域模型
 * @author: cxy
 * @create: 2019-09-16 10:28
 */
public class UserBO {
  private Integer id;
  private String name;
  private Integer gender;
  private Integer age;
  private String phone;
  private String registerMode;
  private String thirdPartyId;
  private String encrptPassword;

  public String getEncrptPassword() {
    return encrptPassword;
  }

  public void setEncrptPassword(String encrptPassword) {
    this.encrptPassword = encrptPassword;
  }

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

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
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

  public String getRegisterMode() {
    return registerMode;
  }

  public void setRegisterMode(String registerMode) {
    this.registerMode = registerMode;
  }

  public String getThirdPartyId() {
    return thirdPartyId;
  }

  public void setThirdPartyId(String thirdPartyId) {
    this.thirdPartyId = thirdPartyId;
  }

  @Override
  public String toString() {
    return "UserBO{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", gender=" + gender +
        ", age=" + age +
        ", phone='" + phone + '\'' +
        ", registerMode='" + registerMode + '\'' +
        ", thirdPartyId='" + thirdPartyId + '\'' +
        ", encrptPassword='" + encrptPassword + '\'' +
        '}';
  }
}
