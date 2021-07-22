package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 18:00
 */
@Data
public class ApiProductGetSkuByPageDTO {

    private int pageCount;

    private List<Long> skuIds;

}
