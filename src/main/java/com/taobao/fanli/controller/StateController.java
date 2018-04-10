package com.taobao.fanli.controller;

import com.taobao.fanli.common.RestResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:xiaolei
 * @date:2018/4/10
 */
@RestController
@RequestMapping(value = "/state")
public class StateController {

    @RequestMapping(value = "/ping" , method = RequestMethod.GET)
    public String ping(){
        return "pong";
    }
}
