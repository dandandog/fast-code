package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.entity.QueryExtsAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiProductGetPageNumResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 15:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiProductGetPageNumParam extends QueryExtsAPIRequest<ApiProductGetPageNumResult> {

    @Override
    public String getUrl() {
        return "/api/product/getPageNum";
    }
}
