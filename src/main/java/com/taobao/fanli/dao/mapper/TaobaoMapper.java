package com.taobao.fanli.dao.mapper;

import com.taobao.fanli.dao.model.Taobao;
import org.apache.ibatis.annotations.Param;

/**
 * @author:xiaolei
 * @date:2018/4/5
 */
public interface TaobaoMapper {

    Taobao getOne(Integer id);

    void insert(Taobao taobao);

    void update(@Param("id") Integer id,@Param("state") Byte state);

    Taobao queryOne(@Param("state") Byte state);
}
