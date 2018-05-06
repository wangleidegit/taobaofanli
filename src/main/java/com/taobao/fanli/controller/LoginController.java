package com.taobao.fanli.controller;

import com.taobao.fanli.common.RestResponse;
import com.taobao.fanli.dao.model.User;
import com.taobao.fanli.postbean.RegisterBean;
import com.taobao.fanli.service.LoginService;
import com.taobao.fanli.utils.CookieUtil;
import com.taobao.fanli.vo.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Tao on 2018/4/19.
 */
@RestController
@RequestMapping(value = "/taobao/sso")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger("LoginController");

    @Resource
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public RestResponse loginController(
            @RequestParam(value = "account") String account,
            @RequestParam(value = "password") String password,
            HttpServletRequest request, HttpServletResponse response
    ){
        Login login = loginService.getUserByCookie(request);
        if(login != null){
            return new RestResponse(login);
        }
        try {
            login = loginService.login(account, password);
            loginService.setUserByCookie(login, response);
            return new RestResponse(login);
        } catch (Exception e) {
            return new RestResponse(e.getMessage(), 113);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public RestResponse registerController(
            @Valid RegisterBean registerBean, BindingResult result
    ){
        if(result.hasErrors()){
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError allError : allErrors){
                return new RestResponse(allError.getDefaultMessage(), 80001);
            }
        }
        Integer userId = loginService.register(registerBean);
        return new RestResponse(userId);
    }
}
