package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiStockGetNewStockByIdResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiStockGetNewStockByIdParam extends AbstractAPIRequest<ApiStockGetNewStockByIdResult> {

    private String skuNums;

    private String area;

    @Override
    public String getUrl() {
        return "/api/stock/getNewStockById";
    }
}
