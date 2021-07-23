package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiAreaCheckAreaResult;
import com.dandandog.framework.api.jdopenapi.result.ApiAreaGetCityResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiAreaCheckAreaParam extends AbstractAPIRequest<ApiAreaCheckAreaResult> {

    private Integer provinceId;

    private Integer cityId;

    private Integer countyId;

    private Integer townId;

    @Override
    public String getUrl() {
        return "/api/area/checkArea";
    }
}
