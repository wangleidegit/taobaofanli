package com.taobao.fanli.dao.mapper;

import com.taobao.fanli.dao.model.Taobao;
import org.apache.ibatis.annotations.Param;

/**
 * @author:xiaolei
 * @date:2018/4/5
 */
public interface TaobaoMapper {

    Taobao getOne(@Param("id") Integer id, @Param("state") Byte state);

    void insert(Taobao taobao);

    void insertDynamic(Taobao taobao);

    void update();

    void updateDynamic(Taobao taobao);

    Taobao queryOne(@Param("state") Byte state);
}
