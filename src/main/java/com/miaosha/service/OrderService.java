package com.miaosha.service;

import com.miaosha.bo.OrderBO;
import com.miaosha.result.BusinessException;

public interface OrderService {
  OrderBO creatOrder(Integer userId,Integer itemId,Integer amount) throws BusinessException;
}
