package com.miaosha.controller;

import com.alibaba.fastjson.JSONObject;
import com.miaosha.bo.ItemBo;
import com.miaosha.result.BusinessException;
import com.miaosha.result.JsonResult;
import com.miaosha.result.Message;
import com.miaosha.service.ItemService;
import com.miaosha.vo.ItemVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.Name;
import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: miaosha
 * @description: item的controller层
 * @author: cxy
 * @create: 2019-09-23 15:52
 */
@RestController
@RequestMapping("/item")
public class ItemController {
  @Autowired
  private ItemService itemService;
  //新建商品
  @PostMapping ("/creat")
  public JSONObject creatItem(@RequestBody ItemBo itemBo) throws BusinessException {
    ItemBo itemBo1 = itemService.creatItem(itemBo);
    ItemVO itemVO = new ItemVO();
    BeanUtils.copyProperties(itemBo,itemVO);
    return JsonResult.getResult(itemVO, Message.SUCCESS);

  }
  //获取商品列表
  @CrossOrigin
  @GetMapping("/getList")
  public JSONObject getItemList(){
    List<ItemBo> itemList = itemService.getItemList();
    List<ItemVO> collect = itemList.stream().map(i -> {
      ItemVO itemVO = new ItemVO();
      BeanUtils.copyProperties(i, itemVO);
      return itemVO;
    }).collect(Collectors.toList());

    return JsonResult.getResult(collect,Message.SUCCESS);
  }

  //获取商品详情
  @GetMapping("/getDetail")
  public JSONObject getItemDetail(@RequestParam Integer id) throws BusinessException {
    ItemBo itemInfomation = itemService.getItemInfomation(id);
    ItemVO itemVO = new ItemVO();
    BeanUtils.copyProperties(itemInfomation,itemVO);
    return JsonResult.getResult(itemVO,Message.SUCCESS);
  }
}
