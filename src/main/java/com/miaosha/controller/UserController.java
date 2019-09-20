package com.miaosha.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.miaosha.bo.UserBO;
import com.miaosha.entity.UserInfo;
import com.miaosha.entity.UserPassword;
import com.miaosha.result.BusinessException;
import com.miaosha.result.CommonReturnType;
import com.miaosha.service.impl.UserServiceImpl;
import com.miaosha.result.JsonResult;
import com.miaosha.result.Message;
import com.miaosha.vo.UserVO;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;


import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @program: miaosha
 * @description: 用户相关
 * @author: cxy
 * @create: 2019-09-16 10:07
 */
@RestController
@RequestMapping("/user")

public class UserController {
  @Autowired
  private UserServiceImpl userService;
  @Autowired
  HttpServletRequest httpServletRequest;
  @PostMapping(value = "/login",consumes="application/x-www-form-urlencoded")
  public JSONObject login(@RequestParam(name = "telphone") String telphone,@RequestParam(name = "password") String password) throws BusinessException {
    //入参校验
    if(StringUtils.isEmpty(telphone)||StringUtils.isEmpty(password)){
      throw  new BusinessException(Message.PARAMETER_VALIDATION_ERROR,"手机号或密码不能为空");
    }
    //登录校验
    UserBO userBO = userService.validateLogin(telphone, password);
    //将登陆凭证\加入到用户登录成功的session中
    this.httpServletRequest.getSession().setAttribute("IS_LOGIN",true);
    this.httpServletRequest.getSession().setAttribute("LOGIN_USER",userBO);

    return JsonResult.getResult(Message.SUCCESS,"用户登录");
  }

  @RequestMapping(value = "/getOTP",method = RequestMethod.POST,consumes="application/x-www-form-urlencoded")
  public JSONObject getOtp(@RequestParam(value = "telphone")String telphone){
    //1.按照一定规则生成otp验证码
    Random random = new Random();
    int i = random.nextInt(99999);
    i+=1000;
    String s = String.valueOf(i);
    //2.将otp验证码和手机号关联(在企业级开发中是保存在redis中,redis中有map的数据结构,刷新验证码时,旧的被覆盖,而且还可以定时)
    //这里我们简化,放在session中
    httpServletRequest.getSession().setAttribute("telphone",s);
//3.将otp验证码通过短信通道发送给用户
    System.out.println("telphone:"+telphone+";otpcode"+s);
    return JsonResult.getResult(s, Message.SUCCESS,"获取otp验证码");
  }
  @GetMapping("/get")
  public JSONObject getUser(@RequestParam(value = "userID",required = false,defaultValue = "1") Integer userID)throws BusinessException{
    Message success = Message.SUCCESS;
    UserBO userBO = userService.selectByPrimaryKey(userID);
    if(userBO==null){
      throw new  BusinessException(Message.PARAMETER_VALIDATION_ERROR,"用户不存在,亲");
    }
    UserVO userVO = new UserVO();
    BeanUtils.copyProperties(userBO,userVO);
    success.setDefaultDescribe("你好啊");
   return JsonResult.getResult(userVO,success,"查询用户信息");

  }

  @PostMapping(value = "/register", consumes="application/x-www-form-urlencoded")

  public JSONObject register(@RequestParam(name="username")String username,@RequestParam(name ="phone") String phone,
                            @RequestParam("denger") Integer denger,@RequestParam(name="tpoCode") String tpoCode,
                             @RequestParam(name="age") Integer age,String password) throws BusinessException {

    String attribute = (String)this.httpServletRequest.getSession().getAttribute("telphone");
    if(!StringUtils.equals(attribute,tpoCode)){
      throw new BusinessException(Message.PARAMETER_VALIDATION_ERROR,"短信验证码无效");
    }
    UserBO userBO = new UserBO();
    userBO.setAge(age);
    userBO.setGender(denger);
    userBO.setName(username);
    userBO.setPhone(phone);
    userBO.setEncrptPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
    userBO.setRegisterMode("BYphone");
    userService.insertUser(userBO);
 return JsonResult.getResult(Message.SUCCESS,"注册成功");
  }
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Object handerException(HttpServletRequest request,Exception e){
    HashMap<String, Object> objectObjectHashMap = new HashMap<>();
    if(e instanceof BusinessException) {
      BusinessException businessException = (BusinessException) e;
      objectObjectHashMap.put("errorcode", businessException.getErrorCode());
      objectObjectHashMap.put("errorMsg", businessException.getDescribe());
    }
    CommonReturnType commonReturnType = new CommonReturnType();
    commonReturnType.setData(objectObjectHashMap);
    commonReturnType.setStatus("fail");

    return commonReturnType;
  }

}
