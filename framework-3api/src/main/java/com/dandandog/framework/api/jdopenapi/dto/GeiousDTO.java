package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */
@Data
public class GeiousDTO {
    private String pin;
    private BigDecimal penaltySumAmt;
    private BigDecimal creditLimit;
    private BigDecimal debtSumAmt;
    private BigDecimal remainLimit;

}
