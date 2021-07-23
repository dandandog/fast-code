package com.dandandog.framework.api.jdopenapi.result;

import com.dandandog.framework.api.jdopenapi.dto.ApiAreaAddressDto;
import com.dandandog.framework.api.jdopenapi.entity.AbstractApiResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiAreaGetJdAddressFromAddressResult extends AbstractApiResponse {
    private ApiAreaAddressDto result;
}
