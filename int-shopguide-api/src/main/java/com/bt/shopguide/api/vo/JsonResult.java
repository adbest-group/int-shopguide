package com.bt.shopguide.api.vo;

import java.io.Serializable;

/**
 * Created by caiting on 2017/9/29.
 */
public class JsonResult implements Serializable {
    private int code = 0;
    private String msg = "";
    private Serializable result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Serializable getResult() {
        return result;
    }

    public void setResult(Serializable result) {
        this.result = result;
    }
}
