package com.dandandog.framework.core.entity;

import com.dandandog.framework.common.config.constant.FastCodeConstant;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

@Data
@Accessors(chain = true)
@ApiModel("查询参数对象")
public class QueryParams<T> implements Serializable {

    @ApiModelProperty(value = "页码,默认为1", example = "1")
    private long page = FastCodeConstant.PAGE_DEFAULT_INDEX;

    @ApiModelProperty(value = "页大小,默认为10", example = "10")
    private long size = FastCodeConstant.PAGE_DEFAULT_SIZE;

    @ApiModelProperty(value = "搜索字符串", example = "")
    private String keyword;

    @ApiModelProperty("排序")
    private Map<String, Boolean> pageSorts = Maps.newConcurrentMap();

    @ApiModelProperty("过滤条件")
    private T filter;

    public QueryParams() {
    }

    public QueryParams(int page, int limit) {
        this.page = page;
        this.size = limit;
    }


}
