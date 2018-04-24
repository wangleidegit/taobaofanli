package com.taobao.fanli.dao.mapper;

import com.taobao.fanli.dao.model.Taobao;
import org.apache.ibatis.annotations.Param;

/**
 * @author:xiaolei
 * @date:2018/4/5
 */
public interface TaobaoMapper {

    Taobao getOne(@Param("id") Integer id, @Param("state") Byte state);

    Taobao getByIdAndState(@Param("id") Integer id);

    void insert(Taobao taobao);

    void insertDynamic(Taobao taobao);

    int update(@Param("id") Integer id);

    void updateDynamic(Taobao taobao);

    Taobao queryOne(@Param("state") Byte state);

    Taobao queryByOtp(@Param("oldTaobaoPassword") String oldTaobaoPassword);
}
