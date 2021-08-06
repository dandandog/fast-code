package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiProductGetSkuGiftResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/8/6 16:36
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiProductGetSkuGiftParam extends AbstractAPIRequest<ApiProductGetSkuGiftResult> {

    private Long skuId;

    private Integer province;

    private Integer city;

    private Integer county;

    private Integer town;

    @Override
    public String getUrl() {
        return "/api/product/getSkuGift";
    }
}
