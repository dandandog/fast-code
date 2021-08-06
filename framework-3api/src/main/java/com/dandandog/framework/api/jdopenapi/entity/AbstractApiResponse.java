package com.dandandog.framework.api.jdopenapi.entity;

import com.dandandog.framework.api.jdopenapi.dto.HitResultDTO;
import com.dandandog.framework.api.jdopenapi.dto.ResultDTO;
import lombok.Data;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 12:05
 */
@Data
public abstract class AbstractApiResponse {

    private boolean success;

    private String resultCode;

    private String resultMessage;

    public abstract <T> T getResult();
}
