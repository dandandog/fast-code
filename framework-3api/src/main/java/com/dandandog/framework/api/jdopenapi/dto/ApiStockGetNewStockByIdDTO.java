package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 14:48
 */
@Data
public class ApiStockGetNewStockByIdDTO {

    private String skuId;

    private String areaId;

    private BigDecimal stockStateId;

    private String stockStateDesc;

    private Integer remainNum;

}
