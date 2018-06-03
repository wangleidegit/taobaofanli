package com.taobao.fanli.service;

import com.taobao.fanli.controller.OrderController;
import com.taobao.fanli.dao.mapper.OrderMapper;
import com.taobao.fanli.dao.model.Order;
import com.taobao.fanli.utils.ExcelUtils;
import com.taobao.fanli.vo.OrderInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author:xiaolei
 * @date:2018/6/2
 */
@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Resource
    private OrderMapper orderMapper;

    public void insertOrderData(String path) throws IOException {
        List<List<Object>> lists = ExcelUtils.readExcel(new File(path));
        lists.remove(0);
        for (List<Object> list : lists){
            insertOne(list);
        }
    }

    public void insertOne(List<Object> list){
        Order order = new Order();
        order.setId(null);
        String createAt = (String) list.get(0);
        if(!StringUtils.isEmpty(createAt)){
            order.setCreateAt(createAt);
        }
        String clickAt = (String) list.get(1);
        if(!StringUtils.isEmpty(clickAt)){
            order.setClickAt(clickAt);
        }
        String goodsInfo = (String) list.get(2);
        if(!StringUtils.isEmpty(goodsInfo)){
            order.setGoodsInfo(goodsInfo);
        }
        String goodsId = (String) list.get(3);
        if(!StringUtils.isEmpty(goodsId)){
            order.setGoodsId(goodsId);
        }
        String manager = (String) list.get(4);
        if(!StringUtils.isEmpty(manager)){
            order.setManager(manager);
        }
        String shop = (String) list.get(5);
        if(!StringUtils.isEmpty(shop)){
            order.setShop(shop);
        }
        String goodsNumber = (String) list.get(6);
        if(!StringUtils.isEmpty(goodsNumber)){
            order.setGoodsNumber(goodsNumber);
        }
        String goodsPrice = (String) list.get(7);
        if(!StringUtils.isEmpty(goodsPrice)){
            order.setGoodsPrice(goodsPrice);
        }
        String orderState = (String) list.get(8);
        if(!StringUtils.isEmpty(orderState)){
            order.setOrderState(orderState);
        }
        String orderType = (String) list.get(9);
        if(!StringUtils.isEmpty(orderType)){
            order.setOrderType(orderType);
        }
        String incomeRatio = (String) list.get(10);
        if(!StringUtils.isEmpty(incomeRatio)){
            order.setIncomeRatio(incomeRatio);
        }
        String divideRatio = (String) list.get(11);
        if(!StringUtils.isEmpty(divideRatio)){
            order.setDivideRatio(divideRatio);
        }
        String pay = (String) list.get(12);
        if(!StringUtils.isEmpty(pay)){
            order.setPay(pay);
        }
        String effect = (String) list.get(13);
        if(!StringUtils.isEmpty(effect)){
            order.setEffect(effect);
        }
        String finishPay = (String) list.get(14);
        if(!StringUtils.isEmpty(finishPay)){
            order.setFinishPay(finishPay);
        }
        String forecastIncome = (String) list.get(15);
        if(!StringUtils.isEmpty(forecastIncome)){
            order.setForecastIncome(forecastIncome);
        }
        String finishTime = (String) list.get(16);
        if(!StringUtils.isEmpty(finishTime)){
            order.setFinishTime(finishTime);
        }
        String moneyRatio = (String) list.get(17);
        if(!StringUtils.isEmpty(moneyRatio)){
            order.setMoneyRatio(moneyRatio);
        }
        String money = (String) list.get(18);
        if(!StringUtils.isEmpty(money)){
            order.setMoney(money);
        }
        String subsidyRatio = (String) list.get(19);
        if(!StringUtils.isEmpty(subsidyRatio)){
            order.setSubsidyRatio(subsidyRatio);
        }
        String subsidyMoney = (String) list.get(20);
        if(!StringUtils.isEmpty(subsidyMoney)){
            order.setSubsidyMoney(subsidyMoney);
        }
        String subsidyType = (String) list.get(21);
        if(!StringUtils.isEmpty(subsidyType)){
            order.setSubsidyType(subsidyType);
        }
        String plantform = (String) list.get(22);
        if(!StringUtils.isEmpty(plantform)){
            order.setPlantform(plantform);
        }
        String thirdFrom = (String) list.get(23);
        if(!StringUtils.isEmpty(thirdFrom)){
            order.setThirdFrom(thirdFrom);
        }
        String orderNumber = (String) list.get(24);
        if(!StringUtils.isEmpty(orderNumber)){
            order.setOrderNumber(orderNumber);
        }
        String typeName = (String) list.get(25);
        if(!StringUtils.isEmpty(typeName)){
            order.setTypeName(typeName);
        }
        String mediaId = (String) list.get(26);
        if(!StringUtils.isEmpty(mediaId)){
            order.setMediaId(mediaId);
        }
        String mediaName = (String) list.get(27);
        if(!StringUtils.isEmpty(mediaName)){
            order.setMediaName(mediaName);
        }
        String adId = (String) list.get(28);
        if(!StringUtils.isEmpty(adId)){
            order.setAdId(adId);
        }
        String adName = (String) list.get(29);
        if(!StringUtils.isEmpty(adName)){
            order.setAdName(adName);
        }
        try {
            orderMapper.insertSelective(order);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("插入失败:",e);
        }
    }

    public OrderInfo selectOrderInfo(String orderNumber) {
        Order order = orderMapper.selectOrderInfo(orderNumber);
        if(order != null){
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setId(order.getId());
            orderInfo.setMoney(order.getMoney());
            orderInfo.setOrderNumber(order.getOrderNumber());
            return orderInfo;
        }
        return null;
    }
}
