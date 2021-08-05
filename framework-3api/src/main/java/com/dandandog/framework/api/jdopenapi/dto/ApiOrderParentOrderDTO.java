package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 14:48
 */
@Data
public class ApiOrderParentOrderDTO {

    private Long jdOrderId;
    private BigDecimal freight;
    private OrderDetailsSkuDTO[] sku;
    private BigDecimal orderPrice;
    private BigDecimal orderNakedPrice;
    private BigDecimal orderTaxPrice;

}
