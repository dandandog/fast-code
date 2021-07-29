package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 17:10
 */
@Data
public class OrderDetailsPayDetailsDTO {
    private String flag;
    private String paymentType;
    private BigDecimal payMoney;
}
