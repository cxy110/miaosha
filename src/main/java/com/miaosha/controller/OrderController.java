package com.miaosha.controller;

import com.alibaba.fastjson.JSONObject;
import com.miaosha.bo.OrderBO;
import com.miaosha.bo.UserBO;
import com.miaosha.result.BusinessException;
import com.miaosha.result.JsonResult;
import com.miaosha.result.Message;
import com.miaosha.service.OrderService;
import com.miaosha.vo.OrderVO;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @program: miaosha
 * @description: 订单的controller层
 * @author: cxy
 * @create: 2019-09-25 17:57
 */
@RestController
@RequestMapping("/order")
public class OrderController {
  @Autowired
  private OrderService orderService;
  @Autowired
  private HttpServletRequest httpServletRequest;
  @PostMapping("/creatOrder")
  public JSONObject creatOrder(Integer itemId,Integer amount,@RequestParam(name="promoId",required = false) Integer promoId) throws BusinessException {
    Boolean islogin =(Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
    if(islogin==null||!islogin.booleanValue()) {
      throw new BusinessException(Message.USER_LOGIN_ERROE, "请先登录,再下单");
    }
      UserBO userBO =(UserBO) httpServletRequest.getSession().getAttribute("LOGIN_USER");

    OrderBO orderBO = orderService.creatOrder(userBO.getId(), itemId, amount,promoId);
    OrderVO orderVO = new OrderVO();
    BeanUtils.copyProperties(orderBO,orderVO);
    return JsonResult.getResult(orderVO, Message.SUCCESS);
  }
}
