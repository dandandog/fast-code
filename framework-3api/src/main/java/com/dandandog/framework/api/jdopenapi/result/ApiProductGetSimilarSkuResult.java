package com.dandandog.framework.api.jdopenapi.result;

import com.dandandog.framework.api.jdopenapi.dto.ApiProductGetSimilarSkuDTO;
import com.dandandog.framework.api.jdopenapi.entity.AbstractApiResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/23 11:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiProductGetSimilarSkuResult extends AbstractApiResponse {

    private ApiProductGetSimilarSkuDTO[] result;

}
