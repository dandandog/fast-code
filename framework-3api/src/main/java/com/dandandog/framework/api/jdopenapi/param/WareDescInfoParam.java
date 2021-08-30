package com.dandandog.framework.api.jdopenapi.param;

import lombok.Data;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */
@Data
public class WareDescInfoParam {
    private Boolean isNeedDetectionReport;
    private Boolean lossPreventionTagFlag;
    private Boolean isHasPackage;
    private Integer packageDesc;
    private String questionDesc;
    private String questionPic;
}
