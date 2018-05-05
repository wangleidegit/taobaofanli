package com.taobao.fanli.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taobao.fanli.dao.mapper.UserMapper;
import com.taobao.fanli.dao.model.User;
import com.taobao.fanli.postbean.RegisterBean;
import com.taobao.fanli.utils.CookieUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Tao on 2018/4/19.
 */
@Service
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger("LoginService");

    private static final String ticket = "ticket";

    private static final ObjectMapper objectMapper = new ObjectMapper();

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

    public User login(String account, String password) throws Exception {
        User user = userMapper.selectByAccount(account);
        if(user == null){
            throw new Exception("没有此用户");
        }
        if(!user.getPassword().equals(DigestUtils.md5Hex(password))){
            throw new Exception("密码不正确");
        }
        return user;
    }

    public User getUserByCookie(HttpServletRequest request){
        String cookieByName = CookieUtil.getCookieByName(request, ticket);
        try {
            User user = objectMapper.readValue(cookieByName, User.class);
            return user;
        } catch (IOException e) {
            logger.error("用户信息还原对象失败");
            return null;
        }
    }

    public void setUserByCookie(User user, HttpServletResponse response){

        String userStr = null;
        try {
            userStr = objectMapper.writeValueAsString(user);
            CookieUtil.setCookie(response, ticket, userStr, "/");
        } catch (JsonProcessingException e) {
            logger.error("用户信息解析字符串失败");
        }
    }
}
