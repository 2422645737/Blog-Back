package com.wanghui.common.utils;

public enum ResultCodeEnum {
    ERROR(101,"失败"),
    SUCCESS(100,"成功");
    private final Integer code;
    private final String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
