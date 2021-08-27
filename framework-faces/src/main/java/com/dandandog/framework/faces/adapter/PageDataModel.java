package com.dandandog.framework.faces.adapter;

import cn.hutool.db.PageResult;
import com.dandandog.framework.common.model.IEntity;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author JohnnyLiu
 */
@Slf4j
public class PageDataModel<T extends IEntity> extends LazyDataModel<T> {

    @Setter
    private IPageAdapter<T> adapter;

    private static class InnerDataModel {
        private final static Map<Object, PageDataModel<?>> DATA_MODEL_MAP = new ConcurrentHashMap<>();

        private static PageDataModel<?> get(Object key) {
            return DATA_MODEL_MAP.get(key);
        }

        private static boolean containsKey(Object key) {
            return DATA_MODEL_MAP.containsKey(key);
        }

        public static void put(Object key, PageDataModel<?> pageVoModel) {
            DATA_MODEL_MAP.put(key, pageVoModel);
        }

    }

    public static <T extends IEntity> PageDataModel<T> getInstance(IPageAdapter<T> adapter) {
        if (!InnerDataModel.containsKey(adapter.getKey())) {
            InnerDataModel.put(adapter.getKey(), new PageDataModel<>());
        }
        PageDataModel<T> pageDataModel = (PageDataModel<T>) InnerDataModel.get(adapter.getClass());
        pageDataModel.setAdapter(adapter);
        return pageDataModel;
    }

    private int getPage(int first, int pageSize) {
        int pageNum = (pageSize > 0) ? (first / pageSize) : 0;
        return pageNum + 1;
    }

    @Override
    public List<T> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        PageResult<T> result = adapter.queryPage(getPage(first, pageSize), pageSize, sortBy, filterBy);
        this.setRowCount(result.getTotal());
        return adapter.resultPage(result);
    }

    @Override
    public T getRowData(String rowKey) {
        return adapter.queryOne(rowKey);
    }

    @Override
    public String getRowKey(T object) {
        return object.getId();
    }


}
