package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiProductGetPageNumResult;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 15:43
 */
public class ApiProductGetPageNumParam extends AbstractAPIRequest<ApiProductGetPageNumResult> {

    private String queryExts;

    @Override
    public String getUrl() {
        return "/api/product/getPageNum";
    }
}
