package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.QueryExtsAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiPriceGetSellPriceResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/22 16:38
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiPriceGetSellPriceParam extends QueryExtsAPIRequest<ApiPriceGetSellPriceResult> {

    private String sku;

    @Override
    public String getUrl() {
        return "/api/price/getSellPrice";
    }
}
