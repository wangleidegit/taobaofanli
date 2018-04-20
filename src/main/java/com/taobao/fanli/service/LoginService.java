package com.taobao.fanli.service;

import com.taobao.fanli.dao.mapper.UserMapper;
import com.taobao.fanli.dao.model.User;
import com.taobao.fanli.postbean.RegisterBean;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Tao on 2018/4/19.
 */
@Service
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger("LoginService");

    @Resource
    private UserMapper userMapper;

    public Integer register(RegisterBean registerBean) {
        User user = new User();
        user.setAccount(registerBean.getAccount());
        user.setPassword(DigestUtils.md5Hex(registerBean.getPassword()));
        user.setMobile(registerBean.getMobile());
        user.setRealName(registerBean.getRealName());
        user.setCreateAt(new Date());
        user.setUpdateAt(user.getCreateAt());
        userMapper.insertDynamic(user);
        return user.getId();
    }
}
