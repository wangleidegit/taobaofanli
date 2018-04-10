package com.taobao.fanli.controller;

import com.taobao.api.ApiException;
import com.taobao.fanli.common.RestResponse;
import com.taobao.fanli.dao.model.Taobao;
import com.taobao.fanli.service.TaobaoFanliService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author:xiaolei
 * @date:2018/4/5
 */
@RestController
@RequestMapping(value = "/taobao/fanli")
public class TaobaoFanliController {

    private static final Logger logger = LoggerFactory.getLogger("TaobaoFanliController");

    @Resource
    private TaobaoFanliService taobaoFanliService;

    @RequestMapping(value = "/parseTpwd", method = RequestMethod.GET)
    public RestResponse parseTpwd(
            @RequestParam(value = "content") String content
    ) {
        try {
            Map<String, Integer> result = taobaoFanliService.parseTpwd(content);
            return new RestResponse(result);
        } catch (ApiException e) {
            logger.error("taobaofanli parseTpwd error:", e);
            return new RestResponse(e.getErrMsg(), Integer.parseInt(e.getErrCode()));
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public RestResponse update(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "newTaobaoPassword") String newTaobaoPassword
    ) {
        try {
            taobaoFanliService.update(id, newTaobaoPassword);
            return new RestResponse("success");
        } catch (Exception e) {
            logger.error("taobaofanli update error:", e);
            return new RestResponse("更新出错", 110);
        }
    }

    @RequestMapping(value = "/queryOne", method = RequestMethod.GET)
    public RestResponse queryOne(
            @RequestParam(value = "id") Integer id
    ) {
        try {
            Taobao taobao = taobaoFanliService.queryOne(id);
            if(taobao != null){
                return new RestResponse(taobao);
            }else{
                return new RestResponse("数据正在处理中，请稍后", 120);
            }

        } catch (Exception e) {
            logger.error("taobaofanli query error:", e);
            return new RestResponse("更新出错", 110);
        }
    }

    @RequestMapping(value = "/queryState", method = RequestMethod.GET)
    public RestResponse queryState() {
        try {
            Taobao taobao = taobaoFanliService.queryState();
            if(taobao != null){
                return new RestResponse(taobao);
            }else{
                return new RestResponse("无数据处理", 120);
            }
        } catch (Exception e) {
            logger.error("taobaofanli query error:", e);
            return new RestResponse("更新出错", 110);
        }
    }


}
