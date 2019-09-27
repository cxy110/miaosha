package com.miaosha.service.impl;

import com.miaosha.bo.PromoBo;
import com.miaosha.dao.PromoMapper;
import com.miaosha.entity.Promo;
import com.miaosha.service.PromoService;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: miaosha
 * @description: 秒杀业务层实现类
 * @author: cxy
 * @create: 2019-09-27 10:04
 */
@Service
public class PrompServiceImpl implements PromoService {
  @Autowired
  private PromoMapper promoMapper;
  @Override
  public PromoBo selectByItemId(Integer itemId) {
    Promo promo = promoMapper.selectByItemId(itemId);

    if(promo==null){
      return null;
    }
    PromoBo promoBo = conventPromotoBO(promo);
    if(promoBo.getStartDate().isAfterNow()){
      //秒杀活动还未开始
      promoBo.setStatus(1);
    }else if(promoBo.getEndDate().isBeforeNow()){
      //秒杀活动已结束
      promoBo.setStatus(3);
    }else {
      promoBo.setStatus(2);
    }
    return promoBo;
  }
  private PromoBo conventPromotoBO(Promo promo){
    PromoBo promoBo = new PromoBo();
    BeanUtils.copyProperties(promo,promoBo);
    promoBo.setStartDate(new DateTime(promo.getStartDate()));
    promoBo.setEndDate(new DateTime(promo.getEndDate()));
    return promoBo;
  }
}
