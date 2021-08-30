package com.dandandog.framework.api.jdopenapi.param;

import lombok.Data;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */
@Data
public class AfsApplyInfoItemParam {

    private Integer customerExpect;
    private WareDescInfoParam wareDescInfo;
    private WareDetailInfoParam wareDetailInfo;
}
