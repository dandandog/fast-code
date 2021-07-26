package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiOrderGetFreightResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiOrderGetFreightParam extends AbstractAPIRequest<ApiOrderGetFreightResult> {

    private String sku;

    private Integer province;

    private Integer city;

    private Integer county;

    private Integer town;

    private Integer paymentType;

    private String queryExts;

    @Override
    public String getUrl() {
        return "/api/order/getFreight";
    }
}
