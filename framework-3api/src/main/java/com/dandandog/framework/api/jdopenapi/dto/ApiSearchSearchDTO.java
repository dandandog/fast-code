package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/22 10:52
 */
@Data
public class ApiSearchSearchDTO {

    private Integer resultCount;

    private Integer pageCount;

    private Integer pageSize;

    private Integer pageIndex;

    private List<BrandDTO> brandAggregate;

    private List<CategoryDTO> categoryAggregate;

    private List<HitResultDTO> hitResult;


}
