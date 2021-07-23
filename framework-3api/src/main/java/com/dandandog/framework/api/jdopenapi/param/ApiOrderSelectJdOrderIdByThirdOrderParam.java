package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiOrderSelectJdOrderIdByThirdOrderResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiOrderSelectJdOrderIdByThirdOrderParam extends AbstractAPIRequest<ApiOrderSelectJdOrderIdByThirdOrderResult> {

    private String thirdOrder;

    @Override
    public String getUrl() {
        return "/api/order/selectJdOrderIdByThirdOrder";
    }
}
