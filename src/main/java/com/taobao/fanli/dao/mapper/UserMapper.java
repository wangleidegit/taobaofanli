package com.taobao.fanli.dao.mapper;

import com.taobao.fanli.dao.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Tao on 2018/4/19.
 */
public interface UserMapper {

    User getOne(@Param("id") Integer id);

    void insert(User user);

    void insertDynamic(User user);

    void updateDynamic(User user);

    User selectByAccount(@Param("account") String account);
}
