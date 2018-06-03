package com.taobao.fanli.controller;

import com.taobao.fanli.common.RestResponse;
import com.taobao.fanli.service.LoginService;
import com.taobao.fanli.service.OrderService;
import com.taobao.fanli.vo.Login;
import com.taobao.fanli.vo.OrderInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author:xiaolei
 * @date:2018/6/2
 */
@RestController
@RequestMapping(value = "/taobao/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Resource
    private OrderService orderService;
    @Resource
    private LoginService loginService;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public ModelAndView upload(){
        return new ModelAndView("upload");
    }

    @GetMapping("/info")
    public RestResponse orderInfo(
            @RequestParam(value = "orderNumber") String orderNumber,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        Login login = loginService.getUserByCookie(request);
        if(login == null){
            return new RestResponse("用户没有登录", 500);
        }
        OrderInfo orderInfo = orderService.selectOrderInfo(orderNumber);
        return new RestResponse(orderInfo);
    }

    @PostMapping("/upload")
    public RestResponse fileUpload(
            @RequestParam("file") MultipartFile multipartFile,
            HttpServletRequest request) {
        if (multipartFile.isEmpty()) {
            return new RestResponse("文件为空", 110);
        }
        String fileType = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        if (fileType.equals(".xlsx") || fileType.equals(".xls")) {
            String base = request.getSession().getServletContext().getRealPath("/") + "attachments" + File.separator + UUID.randomUUID().toString().replace("-","");//设置文件上传位置
            File file = new File(base);
            if (!file.exists()) {
                file.mkdirs();
            }
            try {
                String path = base + File.separator + multipartFile.getOriginalFilename();
                multipartFile.transferTo(new File(path));
                //上传成功后读取Excel表格里面的数据
                orderService.insertOrderData(path);
                file.delete();
            } catch (Exception e) {
                return new RestResponse("excel数据导入格式不正确", 110);
            }
            return new RestResponse("success");
        } else {
            return new RestResponse("文件不是excel", 110);
        }
    }
}
