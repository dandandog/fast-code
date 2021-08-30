package com.dandandog.framework.api.jdopenapi.param;

import lombok.Data;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */
@Data
public class AfsReturnWareInfoParam {
    private Integer returnWareType;
    private Integer returnWareProvince;
    private Integer returnWareCity;
    private Integer returnWareCountry;
    private Integer returnWareVillage;
    private String returnWareAddress;

}
