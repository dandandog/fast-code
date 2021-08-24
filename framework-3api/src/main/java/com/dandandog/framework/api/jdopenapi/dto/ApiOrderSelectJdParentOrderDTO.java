package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 京东订单包含子订单
 * @Author: StephenZhang
 * @date: 2021-07-23 14:48
 */
@Data
public class ApiOrderSelectJdParentOrderDTO {

    private ApiOrderParentOrderDTO pOrder;
    private ApiOrderChildOrderDTO[] cOrder;
    private Integer orderState;
    private Integer submitState;
    private Integer type;
    private Integer orderType;
    private LocalDateTime createOrderTime;
    private LocalDateTime finishTime;
    private Integer jdOrderState;
    private String address;
    private String name;
    private String mobile;

}
