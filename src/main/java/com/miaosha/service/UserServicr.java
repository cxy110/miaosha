package com.miaosha.service;

import com.miaosha.bo.UserBO;
import com.miaosha.entity.UserInfo;
import com.miaosha.result.BusinessException;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;

/**
 * @program: miaosha
 * @description: 用户服务service层
 * @author: cxy
 * @create: 2019-09-16 10:52
 */
public interface UserServicr {
  public UserBO selectByPrimaryKey(Integer userID);
  void insertUser(UserBO userBO) throws BusinessException;
 UserBO validateLogin(String telphone,String password) throws BusinessException;
}
