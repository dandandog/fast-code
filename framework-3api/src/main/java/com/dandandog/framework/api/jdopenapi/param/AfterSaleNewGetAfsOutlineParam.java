package com.dandandog.framework.api.jdopenapi.param;

import lombok.Data;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */
@Data
public class AfterSaleNewGetAfsOutlineParam {

    private String customerPin;
    private Long orderId;
    private String thirdApplyId;
    private Integer applyPageNo;
    private Integer applyPageSize;
    private Long wareId;
    private Integer warePageNo;
    private Integer warePageSize;


}
