package com.miaosha.service;

import com.miaosha.bo.ItemBo;
import com.miaosha.result.BusinessException;

import java.util.List;

public interface ItemService {
  ItemBo creatItem(ItemBo itemBo) throws BusinessException;
  List<ItemBo> getItemList();
  ItemBo getItemInfomation(Integer itemId) throws BusinessException;
  Boolean decreaseStock(Integer itemid,Integer amount)throws BusinessException;
}
