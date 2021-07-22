package com.dandandog.framework.api.jdopenapi.result;

import com.dandandog.framework.api.jdopenapi.dto.ApiProductGetSkuByPageDTO;
import com.dandandog.framework.api.jdopenapi.entity.AbstractApiResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 18:00
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiProductGetSkuByPageResult extends AbstractApiResponse {

    private ApiProductGetSkuByPageDTO result;


}
