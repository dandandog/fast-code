package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/23 11:02
 */
@Data
public class ApiProductGetSimilarSkuDTO {


    private Integer dim;
    private String saleName;
    private List<SaleAttrDTO> saleAttrList;


}
