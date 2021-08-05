package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 14:48
 */
@Data
public class ApiOrderChildOrderDTO {

    private Integer pOrder;
    private Integer orderState;
    private Long jdOrderId;
    private Integer state;
    private Integer submitState;
    private Integer type;
    private BigDecimal freight;
    private OrderDetailsSkuDTO[] sku;
    private BigDecimal orderPrice;
    private BigDecimal orderNakedPrice;
    private BigDecimal orderTaxPrice;

}
