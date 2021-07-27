package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 14:48
 */
@Data
public class BalanceDataDTO {

    private Long id;

    private Integer accountType;

    private BigDecimal amount;

    private String pin;

    private String orderId;

    private Integer tradeType;

    private String tradeTypeName;

    private String createdDate;

    private Date notePub;

    private Long tradeNo;


}
