package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 18:05
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiProductGetDetailDTO extends ResultDTO {
    private String brandName;

    private String imagePath;

    private String weight;

    private String upc;

    private String wareQD;

    private String saleUnit;

    private String productArea;

    private String param;

    private String name;

    private Integer state;

    private Integer sku;

    private String category;

    private String introduction;


}
