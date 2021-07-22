package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/22 10:53
 */
@Data
public class BrandDTO {


    private List<String> pinyinAggr;

    private List<BrandItemDTO> brandList;



}
