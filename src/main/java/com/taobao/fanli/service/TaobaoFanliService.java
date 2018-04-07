package com.taobao.fanli.service;


import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.WirelessShareTpwdQueryRequest;
import com.taobao.api.response.WirelessShareTpwdQueryResponse;
import com.taobao.fanli.common.RestResponse;
import com.taobao.fanli.config.TaobaoConfig;
import com.taobao.fanli.dao.mapper.TaobaoMapper;
import com.taobao.fanli.dao.model.Taobao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author:xiaolei
 * @date:2018/4/5
 */
@Service
public class TaobaoFanliService {
    private static final Logger logger = LoggerFactory.getLogger("TaobaoFanliService");

    @Resource
    private TaobaoMapper taobaoMapper;
    @Resource
    private TaobaoConfig taobaoConfig;

    public String parseTpwd(String content) throws ApiException {
        WirelessShareTpwdQueryResponse result = method(content);
        insert(content, result);
        return "success";
    }

    public void insert(String content, WirelessShareTpwdQueryResponse result){
        Taobao taobao = new Taobao();
        taobao.setId(null);
        taobao.setOldTaobaoPassword(content);
        taobao.setContent(result.getContent());
        taobao.setGoodsLink(result.getUrl());
        taobao.setNativeUrl(result.getNativeUrl());
        taobao.setPicUrl(result.getPicUrl());
        if(!StringUtils.isEmpty(result.getPrice())){
            taobao.setPrice(Integer.valueOf(result.getPrice()));
        }
        taobao.setState((byte) 1);
        taobao.setCreateAt(new Date());
        taobao.setThumbPicUrl(result.getThumbPicUrl());
        taobao.setTitle(result.getTitle());
        taobaoMapper.insert(taobao);
    }

    private WirelessShareTpwdQueryResponse method(String content) throws ApiException {
        String url = taobaoConfig.getUrl();
        String appkey = taobaoConfig.getAppkey();
        String secret = taobaoConfig.getSecret();
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        WirelessShareTpwdQueryRequest req = new WirelessShareTpwdQueryRequest();
        req.setPasswordContent(content);
        WirelessShareTpwdQueryResponse response = client.execute(req);
        return response;
    }

    public void update(Integer id, Byte state) {
        taobaoMapper.update(id, state);
    }

    @Transactional
    public Taobao queryOne(Byte state) {
        Taobao taobao = taobaoMapper.queryOne(state);
        if(taobao.getState() == 1){
            update(taobao.getId(), (byte) 2);
        }
        return taobao;
    }
}
