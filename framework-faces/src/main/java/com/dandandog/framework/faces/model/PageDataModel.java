package com.dandandog.framework.faces.model;

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
        private final static Map<Integer, Object> DATA_MODEL_MAP = new ConcurrentHashMap<>();

        private static <T extends IEntity> PageDataModel<T> get(Integer key) {
            return (PageDataModel) DATA_MODEL_MAP.get(key);
        }

        private static boolean containsKey(Integer key) {
            return DATA_MODEL_MAP.containsKey(key);
        }

        public static <T extends IEntity> void put(Integer key, PageDataModel<T> pageVoModel) {
            DATA_MODEL_MAP.put(key, pageVoModel);
        }

    }

    public static <T extends IEntity> PageDataModel<T> getInstance(IPageAdapter<T> adapter) {
        if (!InnerDataModel.containsKey(adapter.hashCode())) {
            InnerDataModel.put(adapter.hashCode(), new PageDataModel<>());
        }
        PageDataModel<T> pageDataModel = InnerDataModel.get(adapter.hashCode());
        pageDataModel.setAdapter(adapter);
        return pageDataModel;
    }

    @Override
    public List<T> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        PageResult<T> result = adapter.queryPage(getPage(first, pageSize), pageSize, sortBy, filterBy);
        this.setRowCount(result.getTotal());
        return result;
    }

    private int getPage(int first, int pageSize) {
        int pageNum = (pageSize > 0) ? (first / pageSize) : 0;
        return pageNum + 1;
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
