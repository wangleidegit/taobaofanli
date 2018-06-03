package com.taobao.fanli.config;

import com.taobao.fanli.common.RestResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MultipartException.class)
    public RestResponse handleError1(MultipartException e) {
        return new RestResponse("超出文件大小", 500);
    }
}