package com.dandandog.framework.task.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/5/14 9:58
 */
@Data
public class TaskJobResult<T> implements Serializable {

    /**
     * 成功值
     */
    private static final int SUCCESS_CODE = 0;
    /**
     * 失败值
     */
    private static final int ERROR_CODE = 1;
    /**
     * 状态码
     */
    private int code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;


    private TaskJobResult(int code) {
        this.code = code;
    }

    private TaskJobResult(int code, T data) {
        this.code = code;
        this.data = data;
    }

    private TaskJobResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private TaskJobResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static <T> TaskJobResult<T> success() {
        return new TaskJobResult<T>(SUCCESS_CODE, "success");
    }

    public static <T> TaskJobResult<T> success(String msg) {
        return new TaskJobResult<T>(SUCCESS_CODE, msg);
    }

    public static <T> TaskJobResult<T> success(T data) {
        return new TaskJobResult<T>(SUCCESS_CODE, data);
    }

    public static <T> TaskJobResult<T> success(String msg, T data) {
        return new TaskJobResult<T>(SUCCESS_CODE, msg, data);
    }

    public static <T> TaskJobResult<T> error() {
        return new TaskJobResult<T>(ERROR_CODE, "error");
    }

    public static <T> TaskJobResult<T> error(String msg) {
        return new TaskJobResult<T>(ERROR_CODE, msg);
    }

    public static <T> TaskJobResult<T> error(int code, String msg) {
        return new TaskJobResult<T>(code, msg);
    }

}
