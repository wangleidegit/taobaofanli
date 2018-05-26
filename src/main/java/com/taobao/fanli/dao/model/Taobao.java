package com.taobao.fanli.dao.model;

import java.util.Date;

/**
 * @author:xiaolei
 * @date:2018/4/5
 */
public class Taobao {

    private Integer id;
    private String searchContent;
    private String oldTaobaoPassword;
    private String goodsLink;
    private String newTaobaoPassword;
    private Byte state;
    private String title;
    private String content;
    private Integer price;
    private String picUrl;
    private String nativeUrl;
    private String thumbPicUrl;
    private Date createAt;
    private Date updateAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldTaobaoPassword() {
        return oldTaobaoPassword;
    }

    public void setOldTaobaoPassword(String oldTaobaoPassword) {
        this.oldTaobaoPassword = oldTaobaoPassword;
    }

    public String getGoodsLink() {
        return goodsLink;
    }

    public void setGoodsLink(String goodsLink) {
        this.goodsLink = goodsLink;
    }

    public String getNewTaobaoPassword() {
        return newTaobaoPassword;
    }

    public void setNewTaobaoPassword(String newTaobaoPassword) {
        this.newTaobaoPassword = newTaobaoPassword;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getNativeUrl() {
        return nativeUrl;
    }

    public void setNativeUrl(String nativeUrl) {
        this.nativeUrl = nativeUrl;
    }

    public String getThumbPicUrl() {
        return thumbPicUrl;
    }

    public void setThumbPicUrl(String thumbPicUrl) {
        this.thumbPicUrl = thumbPicUrl;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
