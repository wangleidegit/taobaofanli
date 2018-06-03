package com.taobao.fanli.vo;

/**
 * @author:xiaolei
 * @date:2018/6/3
 */
public class OrderInfo {

    private Integer id;
    private String money;
    private String orderNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
