package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 14:48
 */
@Data
public class ApiAreaAddressDto {

    private Integer provinceId;

    private String province;

    private Integer cityId;

    private String city;

    private Integer countyId;

    private String county;

    private Integer townId;

    private String town;

    private Integer nationId;

    private String nation;


}
