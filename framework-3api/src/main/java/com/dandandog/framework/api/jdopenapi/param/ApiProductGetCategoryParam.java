package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiProductGetCategoryResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/29 16:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiProductGetCategoryParam extends AbstractAPIRequest<ApiProductGetCategoryResult> {

    private Long cid;


    @Override
    public String getUrl() {
        return "/api/product/getCategory";
    }

}
