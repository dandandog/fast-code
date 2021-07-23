package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiProductSkuImageResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/23 14:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiProductSkuImageParam extends AbstractAPIRequest<ApiProductSkuImageResult> {

    private String sku;

    @Override
    public String getUrl() {
        return "/api/product/skuImage";
    }
}
