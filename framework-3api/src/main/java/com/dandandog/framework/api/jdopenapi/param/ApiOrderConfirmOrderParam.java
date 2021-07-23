package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiOrderConfirmOrderResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiOrderConfirmOrderParam extends AbstractAPIRequest<ApiOrderConfirmOrderResult> {

    private Long jdOrderId;

    private String poNo;

    @Override
    public String getUrl() {
        return "/api/order/confirmOrder";
    }
}
