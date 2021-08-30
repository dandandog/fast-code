package com.dandandog.framework.api.jdopenapi.param;

import lombok.Data;

import java.util.List;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */
@Data
public class AfterSaleNewGetGoodsAttributesParam {

    private Long orderId;
    private String customerPin;
    private List<Long> wareIds;
}
