package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 14:48
 */
@Data
public class ApiPriceGetBalanceDetailDTO {

    private Integer total;

    private Integer pageSize;

    private Integer pageNo;

    private Integer pageCount;

    private BalanceDataDTO[] data;

}
