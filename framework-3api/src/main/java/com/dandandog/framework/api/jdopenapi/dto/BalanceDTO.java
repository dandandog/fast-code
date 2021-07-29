package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: StephenZhang
 * @date: 2021-07-28 11:16
 */
@Data
public class BalanceDTO {
    private String pin;
    private BigDecimal remainLimit;
}
