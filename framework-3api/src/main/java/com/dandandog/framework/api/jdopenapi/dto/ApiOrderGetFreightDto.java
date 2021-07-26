package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 14:48
 */
@Data
public class ApiOrderGetFreightDto {

    private BigDecimal freight;

    private BigDecimal baseFreight;

    private BigDecimal remoteRegionFreight;

    private String remoteSku;

    private BigDecimal conFreight;


}
