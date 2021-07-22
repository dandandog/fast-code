package com.dandandog.framework.api.jdopenapi.result;

import com.dandandog.framework.api.jdopenapi.dto.ApiSearchSearchDTO;
import com.dandandog.framework.api.jdopenapi.entity.AbstractApiResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/22 10:44
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ApiSearchSearchResult extends AbstractApiResponse {

    private ApiSearchSearchDTO result;

}
