package com.wanghui.common.utils;

public enum ResultCodeEnum {
    ERROR(101,"失败"),
    SUCCESS(200,"成功"),


    UNKNOWN_EXCEPTION(10000,"未知异常"),
    VALID_EXCEPTION(10001,"参数校验异常");
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
