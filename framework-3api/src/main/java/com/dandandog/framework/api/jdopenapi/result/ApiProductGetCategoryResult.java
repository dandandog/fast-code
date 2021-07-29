package com.dandandog.framework.api.jdopenapi.result;

import com.dandandog.framework.api.jdopenapi.dto.ApiProductGetCategoryDTO;
import com.dandandog.framework.api.jdopenapi.entity.AbstractApiResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/29 16:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiProductGetCategoryResult extends AbstractApiResponse {

    ApiProductGetCategoryDTO[] result;

}
