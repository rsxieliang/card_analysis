package com.card.cardanalysis.entity;

public class JsonResult {

    private String code;
    private String msg;

    private Object data;


    public JsonResult() {
    }

    public JsonResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(Object data) {
        this.code="200";
        this.msg="成功";
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
