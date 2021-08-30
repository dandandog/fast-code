package com.dandandog.framework.api.jdopenapi.param;

import lombok.Data;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */
@Data
public class AfsPickupWareInfoParam {

    private Integer pickwareType;
    private Integer pickWareProvince;
    private Integer pickWareCity;
    private Integer pickWareCounty;
    private Integer pickWareVillage;
    private String pickWareAddress;
    private String reserveDateBegin;
    private String reserveDateEnd;
}
