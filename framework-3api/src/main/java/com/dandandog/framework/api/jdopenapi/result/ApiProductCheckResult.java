package com.dandandog.framework.api.jdopenapi.result;

import com.dandandog.framework.api.jdopenapi.dto.ApiProductCheckDTO;
import com.dandandog.framework.api.jdopenapi.entity.AbstractApiResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/23 9:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ApiProductCheckResult extends AbstractApiResponse {

    private ApiProductCheckDTO[] result;
}
