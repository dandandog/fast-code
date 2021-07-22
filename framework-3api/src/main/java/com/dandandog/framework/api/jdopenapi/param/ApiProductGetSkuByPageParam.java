package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiProductGetSkuByPageResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/21 17:59
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiProductGetSkuByPageParam extends AbstractAPIRequest<ApiProductGetSkuByPageResult> {

    private String pageNum;

    private int pageNo;

    @Override
    public String getUrl() {
        return "/api/product/getSkuByPage";
    }
}
