package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/22 16:48
 */
@Data
public class ApiPriceGetSellPriceDto {

    private Long skuId;

    private BigDecimal jdPrice;

    private BigDecimal price;

    private BigDecimal marketPrice;

    private BigDecimal tax;

    private BigDecimal taxPrice;

    private BigDecimal nakedPrice;
}
