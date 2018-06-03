package com.taobao.fanli.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taobao.fanli.dao.mapper.UserMapper;
import com.taobao.fanli.dao.model.User;
import com.taobao.fanli.postbean.RegisterBean;
import com.taobao.fanli.utils.CookieUtil;
import com.taobao.fanli.vo.Login;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    //注册
//    public Integer register(RegisterBean registerBean) {
//        User user = new User();
//        user.setAccount(registerBean.getAccount());
//        user.setPassword(DigestUtils.md5Hex(registerBean.getPassword()));
//        if (registerBean.getMobile() != null) {
//            user.setMobile(registerBean.getMobile());
//        }
//        if (registerBean.getRealName() != null) {
//            user.setRealName(registerBean.getRealName());
//        }
//        user.setCreateAt(new Date());
//        user.setUpdateAt(user.getCreateAt());
//        userMapper.insertDynamic(user);
//        return user.getId();
//    }

    public Login login(String mobile) throws Exception {
        User user = userMapper.selectByMobile(mobile);
        if (user == null) {
            user = new User();
            user.setId(null);
            user.setMobile(mobile);
            user.setCreateAt(new Date());
            user.setUpdateAt(user.getCreateAt());
            userMapper.insertDynamic(user);
        }

        return getLogin(user);
    }

    private Login getLogin(User user) {
        Login login = new Login();
        login.setMobile(user.getMobile());
        login.setId(user.getId());
        login.setOpenid(user.getOpenid());
        return login;
    }

    public Login getUserByCookie(HttpServletRequest request) {
        String id = CookieUtil.getCookieByName(request, ticket);
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        User user = userMapper.getOne(Integer.valueOf(id));
        Login login = getLogin(user);
        return login;
    }

    public void setUserByCookie(Login login, HttpServletResponse response) {
        CookieUtil.setCookie(response, ticket, String.valueOf(login.getId()), "");
    }
}
