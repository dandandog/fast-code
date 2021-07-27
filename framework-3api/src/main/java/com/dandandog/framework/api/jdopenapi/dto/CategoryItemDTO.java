package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/22 15:09
 */
@Data
public class CategoryItemDTO {
    private Integer count;

    private Integer weight;

    private Integer parentId;

    private Integer catId;

    private String field;

    private String name;

}
