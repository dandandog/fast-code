package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiProductGetPageNumResult;
import com.dandandog.framework.api.jdopenapi.result.ApiProductGetSimilarSkuResult;
import lombok.Data;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/23 11:00
 */
@Data
public class ApiProductGetSimilarSkuParam extends AbstractAPIRequest<ApiProductGetSimilarSkuResult> {

    private Long skuId;

    @Override
    public String getUrl() {
        return "/api/product/getSimilarSku";
    }
}
