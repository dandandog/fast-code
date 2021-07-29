package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 14:48
 */
@Data
public class WaybillCodeDTO {

    private Long orderId;

    private Long parentId;

    private String carrier;

    private String deliveryOrderId;

}
