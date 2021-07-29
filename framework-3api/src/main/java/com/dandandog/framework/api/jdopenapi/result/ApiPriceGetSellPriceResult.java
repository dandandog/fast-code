package com.dandandog.framework.api.jdopenapi.result;

import com.dandandog.framework.api.jdopenapi.dto.ApiPriceGetSellPriceDTO;
import com.dandandog.framework.api.jdopenapi.entity.AbstractApiResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/22 16:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ApiPriceGetSellPriceResult extends AbstractApiResponse {

    private ApiPriceGetSellPriceDTO[] result;

}
