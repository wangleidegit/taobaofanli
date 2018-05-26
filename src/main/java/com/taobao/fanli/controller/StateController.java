package com.taobao.fanli.controller;

import com.taobao.fanli.common.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/ping" , method = RequestMethod.GET)
    public String ping(){
        return "pong";
    }

    @RequestMapping("writelog")
    public Object writeLog()
    {
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        return "OK";
    }
}
