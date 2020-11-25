package com.dandandog.framework.wx.model;

import com.dandandog.framework.common.model.IError;

/**
 * @author JohnnyLiu
 */
public enum WxErrorCode implements IError {

    /**
     * 没有令牌
     **/
    NOT_TOKEN(20300, "没有令牌"),

    /**
     * JWT Token解析异常
     **/
    JWT_DECODE_EXCEPTION(20300, "Token解析异常"),

    /**
     * Token 失效
     **/
    TOKEN_DISABLED(20300, "Token失效"),

    /**
     * JWT Token超时
     **/
    TOKEN_EXPIRED(20300, "Token超时"),

    /**
     * JWT Token超时
     **/
    WX_SERVICE_ERROR(51080, "微信服务相关错误"),

    ;

    private final Integer code;

    private final String msg;

    WxErrorCode(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
