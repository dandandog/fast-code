package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/29 16:36
 */
@Data
public class ApiProductGetCategoryDTO {

    private Integer catId;

    private Integer parentId;

    private String name;

    private Integer catClass;

    private Integer state;


}
