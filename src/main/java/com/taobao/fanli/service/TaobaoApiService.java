package com.taobao.fanli.service;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.WirelessShareTpwdQueryRequest;
import com.taobao.api.response.WirelessShareTpwdQueryResponse;
import com.taobao.fanli.config.TaobaoConfig;
import com.taobao.fanli.dao.mapper.TaobaoMapper;
import com.taobao.fanli.dao.model.Taobao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author:xiaolei
 * @date:2018/4/10
 */
@Service
public class TaobaoApiService {
    private static final Logger logger = LoggerFactory.getLogger("TaobaoApiService");

    @Resource
    private TaobaoConfig taobaoConfig;
    @Resource
    private TaobaoMapper taobaoMapper;

    @Async
    public void dealData(Integer id, String content) throws ApiException {
        String url = taobaoConfig.getUrl();
        String appkey = taobaoConfig.getAppkey();
        String secret = taobaoConfig.getSecret();
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        WirelessShareTpwdQueryRequest req = new WirelessShareTpwdQueryRequest();
        req.setPasswordContent(content);
        WirelessShareTpwdQueryResponse response = client.execute(req);
        updateTaobao(id, response);

    }

    private void updateTaobao(Integer id, WirelessShareTpwdQueryResponse response){
        Taobao taobao = taobaoMapper.getOne(id, null);
        if(taobao != null && response.getSuc()){
            taobao.setTitle(response.getTitle());
            taobao.setThumbPicUrl(response.getThumbPicUrl());
            taobao.setPicUrl(response.getPicUrl());
            taobao.setNativeUrl(response.getNativeUrl());
            taobao.setGoodsLink(response.getUrl());
            if(response.getPrice() != null){
                taobao.setPrice(Integer.valueOf(response.getPrice()));
            }
            taobao.setState((byte) 1);
            taobao.setUpdateAt(new Date());
            taobaoMapper.updateDynamic(taobao);
        }
    }
}
