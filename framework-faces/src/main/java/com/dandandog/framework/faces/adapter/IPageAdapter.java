package com.dandandog.framework.faces.adapter;

import cn.hutool.db.PageResult;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import java.util.List;
import java.util.Map;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/3/4 10:35
 */
public abstract class IPageAdapter<T> {


    public Object getKey() {
        return this.getClass();
    }

    /**
     * @param current  当前页
     * @param pageSize 页面大小
     * @param sortBy   排序条件
     * @param filterBy 过滤条件
     * @return 分页结果
     */
    public abstract PageResult<T> queryPage(int current, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy);

    public List<T> resultPage(PageResult<T> result) {
        return result;
    }


    /**
     * @param id 唯一标识
     * @return vo
     */
    public T queryOne(String id) {
        throw new UnsupportedOperationException("getRowData(String rowKey) must be implemented");
    }

}
