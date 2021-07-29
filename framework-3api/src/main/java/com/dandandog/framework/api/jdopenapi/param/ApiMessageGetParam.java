package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiMessageGetResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiMessageGetParam extends AbstractAPIRequest<ApiMessageGetResult> {

    private String type;

    private String queryExt;

    @Override
    public String getUrl() {
        return "/api/message/get";
    }
}
