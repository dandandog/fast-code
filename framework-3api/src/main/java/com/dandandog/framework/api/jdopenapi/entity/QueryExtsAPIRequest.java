package com.dandandog.framework.api.jdopenapi.entity;

import lombok.Data;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/8/6 17:55
 */
@Data
public abstract class QueryExtsAPIRequest<TResponse> extends AbstractAPIRequest<TResponse> {

    protected String queryExts;
}
