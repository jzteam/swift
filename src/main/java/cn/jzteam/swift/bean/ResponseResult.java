package cn.jzteam.swift.bean;

import cn.jzteam.swift.enums.IEnumBizError;

import java.io.Serializable;

public class ResponseResult<T> implements Serializable {
    private int code = 0;
    private String msg = "";
    private T data;

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getData() {
        return this.data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return "ResponseResult(code=" + this.getCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ")";
    }

    public ResponseResult() {
    }

    private ResponseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseResult build(IEnumBizError error){
        return new ResponseResult(error.getCode(),error.getMessage(),null);
    }

    public static ResponseResult build(int code, String msg){
        return new ResponseResult(code,msg,null);
    }

    public static <T>  ResponseResult build(int code, String msg, T data){
        return new ResponseResult(code,msg,data);
    }
}