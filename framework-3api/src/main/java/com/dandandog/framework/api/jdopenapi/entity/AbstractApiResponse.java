package com.dandandog.framework.api.jdopenapi.entity;

import lombok.Data;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 12:05
 */
@Data
public class AbstractApiResponse {

    private boolean success;

    private String resultCode;

    private String resultMessage;


}
