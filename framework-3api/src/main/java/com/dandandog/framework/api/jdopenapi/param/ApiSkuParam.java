package com.dandandog.framework.api.jdopenapi.param;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 16:18
 */
@Data
public class ApiSkuParam {
    private String skuId;
    private Integer num;
    private BigDecimal price;
    private Boolean bNeedGift;
    private List<ApiYanBaoParam> yanbao;
}
