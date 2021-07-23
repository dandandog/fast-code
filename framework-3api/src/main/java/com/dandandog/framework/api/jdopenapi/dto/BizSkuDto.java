package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 17:10
 */
@Data
public class BizSkuDto {
    private String skuId;
    private Integer num;
    private Integer category;
    private BigDecimal price;
    private String name;
    private BigDecimal tax;
    private BigDecimal taxPrice;
    private BigDecimal nakedPrice;
    private Integer type;
    private String oid;
}
