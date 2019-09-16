package com.miaosha.controller;

import com.alibaba.fastjson.JSONObject;
import com.miaosha.bo.UserBO;
import com.miaosha.service.impl.UserServiceImpl;
import com.miaosha.tool.result.JsonResult;
import com.miaosha.tool.result.Message;
import com.miaosha.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
  @GetMapping("/get")
  public JSONObject getUser(@RequestParam(value = "用户ID",required = false,defaultValue = "1") Integer userID){
    Message success = Message.SUCCESS;
    UserBO userBO = userService.selectByPrimaryKey(userID);
    if(userBO==null){
      return null;
    }
    UserVO userVO = new UserVO();
    BeanUtils.copyProperties(userBO,userVO);
    return JsonResult.getResult(userVO,success,"查询用户信息");

  }
}
