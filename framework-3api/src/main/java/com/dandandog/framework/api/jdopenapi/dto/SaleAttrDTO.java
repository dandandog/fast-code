package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/23 11:32
 */
@Data
public class SaleAttrDTO {

    private String imagePath;

    private String saleValue;

    private List<Long> skuIds;

}
