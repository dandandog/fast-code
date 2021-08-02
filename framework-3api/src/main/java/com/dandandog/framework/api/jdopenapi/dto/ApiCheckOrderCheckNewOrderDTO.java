package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 14:48
 */
@Data
public class ApiCheckOrderCheckNewOrderDTO {

    private Integer total;

    private String totalPage;

    private Integer curPage;

    private List<NewOrderDTO> orders;

}
