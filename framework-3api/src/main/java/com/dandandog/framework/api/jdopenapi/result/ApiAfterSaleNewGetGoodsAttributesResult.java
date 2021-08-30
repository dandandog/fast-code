package com.dandandog.framework.api.jdopenapi.result;

import com.dandandog.framework.api.jdopenapi.dto.ApiAfterSaleNewGetGoodsAttributesDTO;
import com.dandandog.framework.api.jdopenapi.entity.AbstractApiResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ApiAfterSaleNewGetGoodsAttributesResult extends AbstractApiResponse {
    private List<ApiAfterSaleNewGetGoodsAttributesDTO> result;
}
