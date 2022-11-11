package com.wanghui.common.utils;

import java.util.HashMap;

//结果类
public class R {
    private Boolean flag;     //标志

    private Integer code;       //状态码

    private String message;     //消息

    private HashMap<String,Object> data;        //数据

    public R(){

    }
    public R put(String key,Object value){
        //存放数据
        HashMap<String,Object> d = new HashMap<>();
        d.put(key,value);
        this.setData(d);      //设置数据
        return this;
    }
    public static R error(){
        R result = new R();      //每次都创建一个新的result
        result.setFlag(false);          //设置标签
        result.setCode(ResultCodeEnum.ERROR.getCode());      //设置状态码
        result.setMessage(ResultCodeEnum.ERROR.getMsg());    //设置消息
        return result;

    }
    public static R ok(){
        R result = new R();      //每次都创建一个新的result
        result.setFlag(true);          //设置标签
        result.setCode(ResultCodeEnum.SUCCESS.getCode());      //设置状态码
        result.setMessage(ResultCodeEnum.SUCCESS.getMsg());    //设置消息
        return result;
    }
    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
}
