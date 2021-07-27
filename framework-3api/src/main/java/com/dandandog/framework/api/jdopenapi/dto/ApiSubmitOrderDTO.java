package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 14:48
 */
@Data
public class ApiSubmitOrderDTO {

    private Long jdOrderId;
    private BigDecimal freight;
    private BigDecimal orderPrice;
    private BigDecimal orderNakedPrice;
    private BigDecimal orderTaxPrice;
    private List<BizSkuDTO> sku;


}
