package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiAreaGetCityResult;
import com.dandandog.framework.api.jdopenapi.result.ApiAreaGetJdAddressFromAddressResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.checkerframework.checker.units.qual.A;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiAreaGetJdAddressFromAddressParam extends AbstractAPIRequest<ApiAreaGetJdAddressFromAddressResult> {

    private String address;

    @Override
    public String getUrl() {
        return "/api/area/getJDAddressFromAddress";
    }
}
