package com.cn.controller.webservice;

import java.io.Serializable;

/**
 * User: zhongrf
 * Date: 2018/3/22 11:26
 * Description:
 */
public class WebBaseInfo implements Serializable {
    protected String syscode;
    protected String token;

    public String getSyscode() {
        return syscode;
    }

    public void setSyscode(String syscode) {
        this.syscode = syscode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}