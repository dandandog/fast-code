package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 14:48
 */

@Data
public class ApiAfterSaleNewGetGoodsAttributesDTO {

    private Long wareId;

    private Integer wareNum;

    private List<Integer> customerExpect;

    private List<Integer> pickupWareType;


}
