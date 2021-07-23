package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiPriceGetSellPriceResult;
import com.dandandog.framework.api.jdopenapi.result.ApiProductCheckResult;
import lombok.Data;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/23 9:31
 */
@Data
public class ApiProductCheckParam extends AbstractAPIRequest<ApiProductCheckResult> {

    private String skuIds;

    private String queryExts;

    @Override
    public String getUrl() {
        return "/api/product/check";
    }
}
