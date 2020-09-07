package com.dandandog.framework.core.config.pagination;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
@ApiModel("查询参数结果")
public class QueryResult {

    @ApiModelProperty(value = "页码")
    private long page;

    @ApiModelProperty(value = "页行数")
    private long size;

    @ApiModelProperty("总行数")
    private long totalPage;

    @ApiModelProperty("总页数")
    private long totalSize;

    @ApiModelProperty("数据列表")
    private Collection<?> list;

}
