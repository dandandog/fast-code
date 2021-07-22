package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiPriceGetSellPriceResult;
import com.dandandog.framework.api.jdopenapi.result.ApiProductGetDetailResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/22 16:38
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiPriceGetSellPriceParam extends AbstractAPIRequest<ApiPriceGetSellPriceResult> {


    private String sku;

    private String queryExts;


    @Override
    public String getUrl() {
        return "/api/price/getSellPrice";
    }
}
