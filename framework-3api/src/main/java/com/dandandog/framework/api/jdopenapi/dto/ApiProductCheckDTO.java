package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/23 9:35
 */

@Data
public class ApiProductCheckDTO {

    private Long skuId;

    private String name;

    private Integer saleState;

    private Integer is7ToReturn;

    private Integer isCanVAT;

    private Integer noReasonToReturn;

    private Integer thwa;


}
