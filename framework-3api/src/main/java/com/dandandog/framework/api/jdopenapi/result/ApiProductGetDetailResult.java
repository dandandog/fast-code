package com.dandandog.framework.api.jdopenapi.result;

import com.dandandog.framework.api.jdopenapi.dto.ApiProductGetDetailDTO;
import com.dandandog.framework.api.jdopenapi.entity.AbstractApiResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 18:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiProductGetDetailResult extends AbstractApiResponse {

    ApiProductGetDetailDTO result;

}
