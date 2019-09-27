package com.miaosha.service.impl;

import com.miaosha.bo.ItemBo;
import com.miaosha.bo.PromoBo;
import com.miaosha.dao.ItemMapper;
import com.miaosha.dao.ItemStockMapper;
import com.miaosha.entity.Item;
import com.miaosha.entity.ItemStock;
import com.miaosha.result.BusinessException;
import com.miaosha.result.Message;
import com.miaosha.service.ItemService;
import com.miaosha.service.PromoService;
import com.miaosha.validator.ValidateImpl;
import com.miaosha.validator.ValidationResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: miaosha
 * @description: item实现类
 * @author: cxy
 * @create: 2019-09-23 15:24
 */
@Service
public class ItemServiceImpl implements ItemService {
  @Autowired
  private ItemMapper itemMapper;
  @Autowired
  private ItemStockMapper itemStockMapper;
  @Autowired
  private ValidateImpl validateimpl;
  @Autowired
  private PromoService promoService;
  //创建添加商品
  @Override
  @Transactional
  public ItemBo creatItem(ItemBo itemBo) throws BusinessException {
    ValidationResult validate = validateimpl.validate(itemBo);
    //判断传递的参数是否合法,是否符合ItemBo的要求
    if(validate.isHasError()){
      throw new BusinessException(Message.PARAMETER_VALIDATION_ERROR,validate.getErrMsg());
    }
    //准备保存如数据库
    Item item = new Item();
    BeanUtils.copyProperties(itemBo,item);
    //商品信息插入商品表中
    try {
      itemMapper.insertSelective(item);
    } catch (Exception e) {
      e.printStackTrace();
    }
    //商品库存插入商品库存表中
    ItemStock itemStock = new ItemStock();
    itemBo.setId(item.getId());
    itemStock.setItemId(itemBo.getId());
    itemStock.setStock(itemBo.getStock());
    try {
      itemStockMapper.insertSelective(itemStock);
    }catch (Exception e){
      e.printStackTrace();
    }

    //返回创建完成的对象
    ItemBo itemInfomation = this.getItemInfomation(itemBo.getId());

    return itemInfomation;
  }

  //获取商品列表
  @Override
  public List<ItemBo> getItemList() {

    //查询出商品的信息
    List<Item> items = itemMapper.selectItemList();

    if(items==null){
      System.out.println("sn");
    }
    //通过java8的stream聚合操作,整合库存并转化内容
    List<ItemBo> collect = items.stream().map(i -> {
      ItemStock itemStock = itemStockMapper.selectByItemId(i.getId());
      ItemBo itemBo = new ItemBo();

        BeanUtils.copyProperties(i, itemBo);

      itemBo.setStock(itemStock.getStock());
      System.out.println(itemBo);
      return itemBo;
    }).collect(Collectors.toList());
    return collect;
  }
//商品下单时,相应的减少库存
  @Transactional
  @Override
  public Boolean decreaseStock(Integer itemid, Integer amount) throws BusinessException {
    int i = itemStockMapper.decreaseStock(itemid, amount);
    if(i>0){
      //更新库存成功,购买数量小于等于库存
      return true;
    }
    return null;
  }

  //获取商品详情
  @Override
  public ItemBo getItemInfomation(Integer itemId) throws BusinessException {
    Item item = itemMapper.selectByPrimaryKey(itemId);
    if(item==null){
      throw new BusinessException(Message.PARAMETER_VALIDATION_ERROR,"商品不存在");
    }
    //操作获取库存数量
    ItemStock itemStock = itemStockMapper.selectByItemId(itemId);
    ItemBo itemBo = new ItemBo();
    BeanUtils.copyProperties(item,itemBo);
    itemBo.setStock(itemStock.getStock());
    //获取商品的秒杀相关的信息
    PromoBo promoBo = promoService.selectByItemId(itemBo.getId());
    if(promoBo!=null&&promoBo.getStatus().intValue()!=3){
      itemBo.setPromoBo(promoBo);
    }
    return itemBo;
  }
}
