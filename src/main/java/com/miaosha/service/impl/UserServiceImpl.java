package com.miaosha.service.impl;

import com.miaosha.bo.UserBO;
import com.miaosha.dao.UserInfoMapper;
import com.miaosha.dao.UserPasswordMapper;
import com.miaosha.entity.UserInfo;
import com.miaosha.entity.UserPassword;
import com.miaosha.service.UserServicr;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: miaosha
 * @description: 用户服务service实现类
 * @author: cxy
 * @create: 2019-09-16 10:55
 */
@Service
public class UserServiceImpl implements UserServicr {
  @Autowired
  private UserInfoMapper mapper;
  @Autowired
  private UserPasswordMapper passwordMapper;

  @Override
  public UserBO selectByPrimaryKey(Integer userID) {
    UserInfo userInfo = mapper.selectByPrimaryKey(userID);
    if(userInfo==null){
      return null;
    }
    UserBO userBO = new UserBO();
    BeanUtils.copyProperties(userInfo,userBO);
    UserPassword userPassword = passwordMapper.selectPasswordByUserID(userID);
    userBO.setEncrptPassword(userPassword.getEncrptPassword());
    return userBO;

  }
}
