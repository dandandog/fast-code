package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/22 16:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ApiPriceGetSellPriceDTO extends ResultDTO {

    private Long skuId;

    private BigDecimal jdPrice;

    private BigDecimal price;

    private BigDecimal marketPrice;

    private BigDecimal tax;

    private BigDecimal taxPrice;

    private BigDecimal nakedPrice;
}
