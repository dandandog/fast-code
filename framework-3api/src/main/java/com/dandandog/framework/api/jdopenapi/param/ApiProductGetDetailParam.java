package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.entity.QueryExtsAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiProductGetDetailResult;
import com.dandandog.framework.api.jdopenapi.result.ApiProductGetSkuByPageResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 18:04
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiProductGetDetailParam extends QueryExtsAPIRequest<ApiProductGetDetailResult> {

    private String sku;

    @Override
    public String getUrl() {
        return "/api/product/getDetail";
    }
}
