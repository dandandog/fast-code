package com.dandandog.framework.core.cache;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

    @CachePut(value = "entity", key = "#entity.targetClass")
    public boolean save(T entity) {
        return super.save(entity);
    }

    public boolean saveBatch(Collection<T> entityList) {
        return super.saveBatch(entityList);
    }

    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        return super.saveBatch(entityList, batchSize);
    }

    public boolean update(Wrapper<T> updateWrapper) {
        return super.update(updateWrapper);
    }

    public boolean update(T entity, Wrapper<T> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    public boolean updateById(T entity) {
        return super.updateById(entity);
    }

    public boolean updateBatchById(Collection<T> entityList) {
        return super.updateBatchById(entityList);
    }

    public boolean updateBatchById(Collection<T> entityList, int batchSize) {
        return super.updateBatchById(entityList, batchSize);
    }

    @CachePut(value = "entity", key = "#entity.targetClass")
    public boolean saveOrUpdate(T entity) {
        return super.saveOrUpdate(entity);
    }

    public boolean saveOrUpdateBatch(Collection<T> entityList) {
        return super.saveOrUpdateBatch(entityList);
    }

    public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
        return super.saveOrUpdateBatch(entityList, batchSize);
    }

    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    public boolean removeByMap(Map<String, Object> columnMap) {
        return super.removeByMap(columnMap);
    }

    public boolean remove(Wrapper<T> queryWrapper) {
        return super.remove(queryWrapper);
    }

    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    public T getById(Serializable id) {
        return super.getById(id);
    }

    public List<T> listByIds(Collection<? extends Serializable> idList) {
        return super.listByIds(idList);
    }

    public List<T> listByMap(Map<String, Object> columnMap) {
        return super.listByMap(columnMap);
    }

    public T getOne(Wrapper<T> queryWrapper) {
        return super.getOne(queryWrapper);
    }

    public T getOne(Wrapper<T> queryWrapper, boolean throwEx) {
        return super.getOne(queryWrapper, throwEx);
    }

    public Map<String, Object> getMap(Wrapper<T> queryWrapper) {
        return super.getMap(queryWrapper);
    }

    public <V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper) {
        return super.getObj(queryWrapper, mapper);
    }

    public int count() {
        return super.count();
    }

    public int count(Wrapper<T> queryWrapper) {
        return super.count(queryWrapper);
    }

    public List<T> list(Wrapper<T> queryWrapper) {
        return super.list(queryWrapper);
    }

    @Cacheable(value = "list")
    public List<T> list() {
        return super.list();
    }

    @Cacheable(value = "page")
    public <E extends IPage<T>> E page(E page, Wrapper<T> queryWrapper) {
        return super.page(page, queryWrapper);
    }

    public <E extends IPage<T>> E page(E page) {
        return super.page(page);
    }

    public List<Map<String, Object>> listMaps(Wrapper<T> queryWrapper) {
        return super.listMaps(queryWrapper);
    }

    public List<Map<String, Object>> listMaps() {
        return super.listMaps();
    }

    public List<Object> listObjs() {
        return super.listObjs();
    }

    public <V> List<V> listObjs(Function<? super Object, V> mapper) {
        return super.listObjs(mapper);
    }

    public List<Object> listObjs(Wrapper<T> queryWrapper) {
        return super.listObjs(queryWrapper);
    }

    public <V> List<V> listObjs(Wrapper<T> queryWrapper, Function<? super Object, V> mapper) {
        return super.listObjs(queryWrapper, mapper);
    }

    public <E extends IPage<Map<String, Object>>> E pageMaps(E page, Wrapper<T> queryWrapper) {
        return super.pageMaps(page, queryWrapper);
    }

    public <E extends IPage<Map<String, Object>>> E pageMaps(E page) {
        return super.pageMaps(page);
    }

    @Override
    public Class<T> currentModelClass() {
        return super.currentModelClass();
    }

    public boolean isCurrentModelClass(Class<?> clazz) {
        return ObjectUtil.equal(super.currentModelClass(), clazz);
    }


}
