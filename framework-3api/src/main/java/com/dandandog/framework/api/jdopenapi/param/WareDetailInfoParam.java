package com.dandandog.framework.api.jdopenapi.param;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */
@Data
public class WareDetailInfoParam {
    private Long wareId;
    private Long mainWareId;
    private String wareName;
    private Integer wareNum;
    private String wareDescribe;
    private BigDecimal payPrice;
    private Integer wareType;
}
