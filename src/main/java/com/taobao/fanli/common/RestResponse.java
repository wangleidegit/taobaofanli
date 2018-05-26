package com.taobao.fanli.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by dayu on 11/18/15.
 */

@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class RestResponse<T> {
    private int errno = 0;

    private String error;

    private T body;

    public RestResponse(String msg, int errno) {
        this.errno = errno;
        this.error = msg;
    }

    public RestResponse(T body, int errno) {
        this.errno = errno;
        this.body = body;
    }

    public RestResponse(int errno, String error, T body) {
        this.errno = errno;
        this.error = error;
        this.body = body;
    }

    public RestResponse(T body) {
        this.body = body;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }


}
