package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiCheckOrderCheckNewOrderResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiCheckOrderCheckNewOrderParam extends AbstractAPIRequest<ApiCheckOrderCheckNewOrderResult> {

    private String date;

    private Integer pageNo;

    private Integer pageSize;

    private Long jdOrderIdIndex;

    private String endDate;

    @Override
    public String getUrl() {
        return "/api/checkOrder/checkNewOrder";
    }
}
