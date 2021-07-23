package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 14:48
 */
@Data
public class ApiAreaCheckDto {
    private boolean success;
    private Integer resultCode;
    private Integer addressId;
    private String message;

}
