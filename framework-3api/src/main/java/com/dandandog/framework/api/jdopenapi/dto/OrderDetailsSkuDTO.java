package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 17:10
 */
@Data
public class OrderDetailsSkuDTO {
    private String name;
    private Long skuId;
    private Integer num;
    private Integer category;
    private BigDecimal price;
    private BigDecimal tax;
    private Integer oid;
    private Integer type;
    private Integer splitFreight;
    private Integer taxPrice;
    private Integer nakedPrice;


}
