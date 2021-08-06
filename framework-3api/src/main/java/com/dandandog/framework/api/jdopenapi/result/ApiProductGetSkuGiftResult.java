package com.dandandog.framework.api.jdopenapi.result;

import com.dandandog.framework.api.jdopenapi.dto.ApiProductGetSkuGiftDTO;
import com.dandandog.framework.api.jdopenapi.entity.AbstractApiResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/8/6 16:37
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiProductGetSkuGiftResult extends AbstractApiResponse {

    private ApiProductGetSkuGiftDTO result;

}
