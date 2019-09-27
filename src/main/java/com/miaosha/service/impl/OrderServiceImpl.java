package com.miaosha.service.impl;

import com.miaosha.bo.ItemBo;
import com.miaosha.bo.OrderBO;
import com.miaosha.bo.UserBO;
import com.miaosha.dao.ItemMapper;
import com.miaosha.dao.OrderMapper;
import com.miaosha.dao.SequenceMapper;
import com.miaosha.entity.Orders;
import com.miaosha.entity.Sequence;
import com.miaosha.result.BusinessException;
import com.miaosha.result.Message;
import com.miaosha.service.ItemService;
import com.miaosha.service.OrderService;
import com.miaosha.service.UserServicr;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @program: miaosha
 * @description: 订单service实现类
 * @author: cxy
 * @create: 2019-09-25 15:51
 */
@Service
public class OrderServiceImpl implements OrderService {
  @Autowired
  private ItemService itemService;
  @Autowired
  private UserServicr userServicr;
  @Autowired
  private OrderMapper orderMapper;
  @Autowired
  private SequenceMapper sequenceMapper;
  @Autowired
  private ItemMapper itemMapper;

  @Override
  @Transactional
  public OrderBO creatOrder(Integer userId, Integer itemId, Integer amount,Integer promoId) throws BusinessException {
    //1.校验下单状态,商品是否存在,用户是否合法,商品数量是否正确
    ItemBo itemInfomation = itemService.getItemInfomation(itemId);
    if(itemInfomation==null){
      throw new BusinessException(Message.PARAMETER_VALIDATION_ERROR,"商品信息不存在");
    }
    UserBO userBO = userServicr.selectByPrimaryKey(userId);
    if(userBO==null){
      throw new BusinessException(Message.PARAMETER_VALIDATION_ERROR,"用户信息不存在");
    }
    if(amount<=0||amount>99){
      throw new BusinessException(Message.PARAMETER_VALIDATION_ERROR,"数量信息不正确");
    }
    //校验活动信息
    if(promoId!=null){
      //(1)校验对应的活动是否是这个商品的
      if(promoId.intValue()!=itemInfomation.getPromoBo().getId()){
        throw new BusinessException(Message.PARAMETER_VALIDATION_ERROR,"活动信息不对");
      }
      //(2)校验活动是否还在进行
      else if(itemInfomation.getPromoBo().getStatus().intValue()==1){
        throw new  BusinessException(Message.PARAMETER_VALIDATION_ERROR,"活动还没有开始");
      }else if(itemInfomation.getPromoBo().getStatus().intValue()==3){
        throw new BusinessException(Message.PARAMETER_VALIDATION_ERROR,"活动已结束");
      }
    }
    //2下单成功,减库存
    Boolean aBoolean = itemService.decreaseStock(itemId, amount);
    if(!aBoolean){
    throw new BusinessException(Message.STOCK_NOT_ENOUGH);
    }
    //3.商品销量增加
    itemMapper.increaseSale(itemId,amount);
    //4订单入库
    OrderBO orderBO = new OrderBO();
    orderBO.setAmount(amount);
    orderBO.setItemId(itemId);
    orderBO.setPromoId(promoId);
    orderBO.setUserId(userId);
    if(promoId!=null){
      orderBO.setOrderPrice(itemInfomation.getPromoBo().getPromoItemPrice());
    }else {
      orderBO.setOrderPrice(itemInfomation.getPrice());
    }
    orderBO.setOrderAmount(orderBO.getOrderPrice().multiply(new BigDecimal(amount)));
    System.out.println(itemInfomation.getPrice().multiply(new BigDecimal(amount)));
    //生成流水号,
    orderBO.setId(generateOrderNo());
    Orders order = new Orders();
    BeanUtils.copyProperties(orderBO,order);
   orderMapper.insert(order);
    //4.返回前端
    return orderBO;
  }
  //生成订单id
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  protected String generateOrderNo(){
    StringBuilder stringBuilder = new StringBuilder();
    //订单为16位;
    //前8位位日期
    LocalDateTime now=LocalDateTime.now();
    String nowString=now.format(DateTimeFormatter.ISO_DATE).replace("-","");
    stringBuilder.append(nowString);
    //中间6位为自增序列
    int sequence=0;
    Sequence order_info = sequenceMapper.getSequenceByName("order_info");
     //获取序列值
     sequence=order_info.getCurrentValue();
     //开始对原值增加并判断是否到达最大值
     int newValue=order_info.getCurrentValue()+order_info.getStep();
     if(newValue>order_info.getMaxValue()){
       //如果到达最大值,就重新循环
       order_info.setCurrentValue(0);
     }else {
       order_info.setCurrentValue(newValue);
     }
     sequenceMapper.updateByPrimaryKeySelective(order_info);
      //开始拼接
    String s = String.valueOf(sequence);
    for(int i=0;i<6-s.length();i++){
      stringBuilder.append(0);
    }
    stringBuilder.append(s);
    //后两位为分库分表位,暂时写死
    stringBuilder.append("00");
    return stringBuilder.toString();
  }
}
