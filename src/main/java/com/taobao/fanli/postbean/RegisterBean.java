package com.taobao.fanli.postbean;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Tao on 2018/4/19.
 */
public class RegisterBean {

    @NotEmpty(message = "账号不能为空")
    private String account;
    @NotEmpty(message = "密码不能为空")
    private String password;
    private String mobile;
    private String realName;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
