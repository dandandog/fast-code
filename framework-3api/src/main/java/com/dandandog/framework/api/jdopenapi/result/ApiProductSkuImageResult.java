package com.dandandog.framework.api.jdopenapi.result;

import com.dandandog.framework.api.jdopenapi.dto.ApiProductSkuImageDTO;
import com.dandandog.framework.api.jdopenapi.entity.AbstractApiResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/23 14:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiProductSkuImageResult extends AbstractApiResponse {

    private Map<String, ApiProductSkuImageDTO[]> result;


}
