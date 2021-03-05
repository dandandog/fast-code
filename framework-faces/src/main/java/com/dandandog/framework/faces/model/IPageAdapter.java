package com.dandandog.framework.faces.model;

import cn.hutool.db.PageResult;
import com.dandandog.framework.common.model.IEntity;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import java.util.Collection;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/3/4 10:35
 */
public abstract class IPageAdapter<T> {


    /**
     * @param current  当前页
     * @param pageSize 页面大小
     * @param sorts    排序条件
     * @param filters  过滤条件
     * @return 分页结果
     */
    protected abstract PageResult<T> queryPage(int current, int pageSize, Collection<SortMeta> sorts, Collection<FilterMeta> filters);


    /**
     * @param id 唯一标识
     * @return vo
     */
    protected T queryOne(String id) {
        throw new UnsupportedOperationException("getRowData(String rowKey) must be implemented");
    }
}
