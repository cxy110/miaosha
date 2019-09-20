package com.miaosha.service.impl;

import com.miaosha.bo.UserBO;
import com.miaosha.dao.UserInfoMapper;
import com.miaosha.dao.UserPasswordMapper;
import com.miaosha.entity.UserInfo;
import com.miaosha.entity.UserPassword;
import com.miaosha.result.BusinessException;
import com.miaosha.result.Message;
import com.miaosha.service.UserServicr;
import com.miaosha.validator.ValidateImpl;
import com.miaosha.validator.ValidationResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.beans.Transient;
import java.sql.SQLException;

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
  @Autowired
  private ValidateImpl validateImpl;

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

  @Override
  @Transactional(rollbackFor =RuntimeException.class)
  public void insertUser(UserBO userBO) throws BusinessException {
    if(userBO==null){
      throw new BusinessException(Message.PARAMETER_VALIDATION_ERROR);
    }


    //验证填入信息是否存在
    /*if(StringUtils.isEmpty(userBO.getEncrptPassword())||StringUtils.isEmpty(userBO.getName())||
     StringUtils.isEmpty(userBO.getPhone())||userBO.getAge()==null||userBO.getGender()==null){
      throw new BusinessException(Message.PARAMETER_VALIDATION_ERROR,"参数不合法");
    }*/
    //通过这种validator校验数据,可以通过通用
    ValidationResult validate = validateImpl.validate(userBO);
    if(validate.isHasError()){

      throw new BusinessException(Message.PARAMETER_VALIDATION_ERROR,validate.getErrMsg());
    }

    UserInfo userInfo = new UserInfo();
    BeanUtils.copyProperties(userBO,userInfo);
    try {
    mapper.insertSelective(userInfo);
    }catch (DuplicateKeyException ex){
      throw new BusinessException(Message.PARAMETER_VALIDATION_ERROR,"手机号已重复");
    }
    UserPassword userPassword = new UserPassword();
    userPassword.setUserId(userInfo.getId());
    userPassword.setEncrptPassword(userBO.getEncrptPassword());
    passwordMapper.insertSelective(userPassword);






    }

  @Override
  public UserBO validateLogin(String telphone, String password) throws BusinessException {
    //通过手机获取用户信息
    UserInfo userInfo = mapper.selectByTelphone(telphone);
    if(userInfo==null){
      throw new BusinessException(Message.USER_LOGIN_ERROE);
    }
    UserPassword userPassword = passwordMapper.selectPasswordByUserID(userInfo.getId());
 //验证用户密码是否合法
    if(!StringUtils.equals(userPassword.getEncrptPassword(),DigestUtils.md5DigestAsHex(password.getBytes()))){
      throw new BusinessException(Message.USER_LOGIN_ERROE);
    }
    System.out.println(password);
    System.out.println("-------");
    System.out.println(DigestUtils.md5DigestAsHex(password.getBytes()));
    UserBO userBO = new UserBO();
    userBO.setEncrptPassword(userPassword.getEncrptPassword());
    userBO.setRegisterMode(userInfo.getRegisterMode());
    userBO.setPhone(userInfo.getPhone());
    userBO.setName(userInfo.getName());
    userBO.setGender(userInfo.getGender());
    userBO.setAge(userInfo.getAge());
    userBO.setThirdPartyId(userInfo.getThirdPartyId());
    userBO.setId(userInfo.getId());
    return userBO;
  }


}
