package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiSearchSearchResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/7/22 10:43
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiSearchSearchParam extends AbstractAPIRequest<ApiSearchSearchResult> {

    private String Keyword;

    private String catId;

    private Integer pageIndex;

    private Integer pageSize;

    private String Min;

    private String Max;

    private String Brands;

    private String cid1;

    private String cid2;

    private String sortType;

    private String priceCol;

    private String extAttrCol;

    private boolean mergeSku;

    @Override
    public String getUrl() {
        return "/api/search/search";
    }
}
