package com.dandandog.framework.api.jdopenapi.entity;

import com.alibaba.ocean.rawsdk.client.APIId;
import lombok.Data;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 14:11
 */
@Data
public abstract class AbstractAPIRequest<TResponse> {

    protected String token;

    public abstract String getUrl();

    public Class<TResponse> getResponseClass() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class) parameterizedType.getActualTypeArguments()[0];
    }
}
