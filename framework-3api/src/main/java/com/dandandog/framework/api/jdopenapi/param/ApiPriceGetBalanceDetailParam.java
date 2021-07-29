package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiPriceGetBalanceDetailResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiPriceGetBalanceDetailParam extends AbstractAPIRequest<ApiPriceGetBalanceDetailResult> {

    private Integer pageNum;

    private Integer pageSize;

    private Integer orderId;

    private String startDate;

    private String endDate;

    @Override
    public String getUrl() {
        return "/api/price/getBalanceDetail";
    }
}
