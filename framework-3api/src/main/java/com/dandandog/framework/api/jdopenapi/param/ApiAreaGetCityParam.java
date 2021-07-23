package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiAreaGetCityResult;
import com.dandandog.framework.api.jdopenapi.result.ApiAreaGetProvinceResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiAreaGetCityParam extends AbstractAPIRequest<ApiAreaGetCityResult> {

    private String id;

    @Override
    public String getUrl() {
        return "/api/area/getCity";
    }
}
