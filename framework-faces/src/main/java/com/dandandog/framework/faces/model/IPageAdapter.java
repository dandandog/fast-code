package com.dandandog.framework.faces.model;

import cn.hutool.db.PageResult;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import java.util.Map;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/3/4 10:35
 */
public abstract class IPageAdapter<T> {


    /**
     * @param current  当前页
     * @param pageSize 页面大小
     * @param sortBy    排序条件
     * @param filterBy  过滤条件
     * @return 分页结果
     */
    public abstract PageResult<T> queryPage(int current, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy);


    /**
     * @param id 唯一标识
     * @return vo
     */
    public T queryOne(String id) {
        throw new UnsupportedOperationException("getRowData(String rowKey) must be implemented");
    }
}
