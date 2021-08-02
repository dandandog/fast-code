package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 14:48
 */
@Data
public class NewOrderDTO {

    private Long jdOrderId;

    private Integer state;

    private Integer hangUpState;

    private Integer invoiceState;

    private BigDecimal orderPrice;

    private String time;

}
