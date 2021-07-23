package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/23 14:55
 */
@Data
public class ApiProductSkuImageDTO {

    private Long id;

    private Long skuId;

    private String path;

    private Date created;

    private Date modified;

    private Integer yn;

    private Integer isPrimary;

    private Integer orderSort;

    private Integer position;

    private Integer type;

    private String features;

}
