package com.dandandog.framework.api.jdopenapi.result;

import com.dandandog.framework.api.jdopenapi.entity.AbstractApiResponse;
import com.dandandog.framework.api.jdopenapi.dto.ApiProductGetPageNumDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 15:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiProductGetPageNumResult extends AbstractApiResponse {

    private ApiProductGetPageNumDTO[] result;

}
