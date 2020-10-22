package com.dandandog.framework.common.model;

import cn.hutool.core.util.EnumUtil;

/**
 * @author JohnnyLiu
 */
public enum ApiErrorCode implements IErrorCode {
    /**
     * 操作成功
     **/
    SUCCESS(20000, "操作成功"),


    /**
     * 没有令牌
     **/
    NOT_TOKEN(20300, "没有令牌"),


    /**
     * 操作失败
     **/
    FAILED(50000, "操作失败"),

    /**
     * 登录失败
     **/
    LOGIN_EXCEPTION(40000, "登录失败"),

    /**
     * 非法访问
     **/
    UNAUTHORIZED(40100, "非法访问"),

    /**
     * 没有登入
     **/
    NOT_LOGIN(40200, "用户未登入"),

    /**
     * 没有权限
     **/
    NOT_PERMISSION(40300, "没有权限"),
    /**
     * 你请求的资源不存在
     **/
    NOT_FOUND(40400, "你请求的资源不存在"),

    /**
     * 刷新token
     **/
    TOKEN_REFRESH(40600, "刷新token"),

    /**
     * 操作失败
     **/
    UNKNOWN(50000, "未知错误异常"),

    /**
     * 系统异常
     **/
    SYSTEM_EXCEPTION(50001, "系统异常"),
    /**
     * 请求参数校验异常
     **/
    PARAMETER_EXCEPTION(50002, "请求参数校验异常"),
    /**
     * 请求参数解析异常
     **/
    PARAMETER_PARSE_EXCEPTION(50003, "请求参数解析异常"),
    /**
     * HTTP内容类型异常
     **/
    HTTP_MEDIA_TYPE_EXCEPTION(50004, "HTTP内容类型异常"),
    /**
     * 系统处理异常
     **/
    SPRING_BOOT_PLUS_EXCEPTION(51000, "系统处理异常"),
    /**
     * 业务处理异常
     **/
    BUSINESS_EXCEPTION(51010, "业务处理异常"),
    /**
     * 数据库处理异常
     **/
    DAO_EXCEPTION(51020, "数据库处理异常"),
    /**
     * 验证码校验异常
     **/
    VERIFICATION_CODE_EXCEPTION(51030, "验证码校验异常"),
    /**
     * 登录授权异常
     **/
    AUTHENTICATION_EXCEPTION(51040, "登录授权异常"),
    /**
     * 没有访问权限
     **/
    UNAUTHENTICATED_EXCEPTION(51050, "没有访问权限"),
    /**
     * 没有访问权限
     **/
    UNAUTHORIZED_EXCEPTION(51060, "没有访问权限"),
    /**
     * JWT Token解析异常
     **/
    JWT_DECODE_EXCEPTION(51070, "Token解析异常"),

    /**
     * JWT Token解析异常
     **/
    TOKEN_EXCEPTION(51070, "Token异常"),

    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION(51080, "METHOD NOT SUPPORTED"),

    ;

    private final long code;
    private final String msg;

    ApiErrorCode(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ApiErrorCode getApiCode(int code) {
        return EnumUtil.likeValueOf(ApiErrorCode.class, code);
    }

    public long getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }


}
