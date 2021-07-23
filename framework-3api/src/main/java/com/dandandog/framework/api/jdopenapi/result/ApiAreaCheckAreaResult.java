package com.dandandog.framework.api.jdopenapi.result;

import com.dandandog.framework.api.jdopenapi.dto.ApiAreaCheckDto;
import com.dandandog.framework.api.jdopenapi.entity.AbstractApiResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ApiAreaCheckAreaResult extends AbstractApiResponse {
    private ApiAreaCheckDto result;
}
