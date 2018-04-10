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
import java.util.HashMap;
import java.util.Map;

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
    private TaobaoApiService taobaoApiService;

    /**
     * 解析插入操作
     * @param content
     * @return
     * @throws ApiException
     */
    public Map<String, Integer> parseTpwd(String content) throws ApiException {
        String oldTaobaoPassword = parseSearchContent(content);
        Integer id = insert(content, oldTaobaoPassword);
        taobaoApiService.dealData(id, oldTaobaoPassword);
        Map<String, Integer> map = new HashMap<>();
        map.put("id", id);
        return map;
    }

    private String parseSearchContent(String content){
        int count = content.indexOf("￥");
        int count2 = content.lastIndexOf("￥");
        String oldTaobaoPassword = content.substring(count, count2+1);
        return oldTaobaoPassword;
    }

    private Integer insert(String content, String oldTaobaoPassword){
        Taobao taobao = new Taobao();
        taobao.setId(null);
        taobao.setSearchContent(content);
        taobao.setOldTaobaoPassword(oldTaobaoPassword);
        taobao.setState((byte) 0);
        taobao.setCreateAt(new Date());
        taobao.setUpdateAt(taobao.getCreateAt());
        taobaoMapper.insertDynamic(taobao);
        return taobao.getId();
    }

    /**
     * 更新操作
     * @param id
     * @param newTaobaoPassword
     */
    public void update(Integer id, String newTaobaoPassword) {
        Taobao taobao = new Taobao();
        taobao.setId(id);
        taobao.setNewTaobaoPassword(newTaobaoPassword);
        taobao.setState((byte) 3);
        taobaoMapper.updateDynamic(taobao);
    }

    /**
     * 查询一个根据id
     * @param id
     * @return
     */
    public Taobao queryOne(Integer id) {
        Taobao taobao = taobaoMapper.getOne(id, (byte) 3);
        return taobao;
    }

    @Transactional
    public Taobao queryState() {
        taobaoMapper.update();
        Taobao taobao = taobaoMapper.queryOne((byte) 2);
        return taobao;
    }
}
