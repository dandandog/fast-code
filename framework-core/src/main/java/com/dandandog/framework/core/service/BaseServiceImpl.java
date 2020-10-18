package com.dandandog.framework.core.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dandandog.framework.cache.annotation.CacheStoreEvict;
import com.dandandog.framework.cache.annotation.CacheStorePut;
import com.dandandog.framework.common.model.IEntity;

import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<M extends BaseMapper<T>, T extends IEntity> extends ServiceImpl<M, T> implements ICacheService<T> {

    public boolean isCurrentModelClass(Class<?> clazz) {
        return ObjectUtil.equal(super.currentModelClass(), clazz);
    }

    @CacheStoreEvict(value = "entity", keys = {"page:*", "list"})
    public boolean cacheSave(T entity) {
        return super.save(entity);
    }

    @CacheStoreEvict(value = "entity", keys = {"page:*", "list"})
    public boolean cacheSaveOrUpdate(T entity) {
        return super.saveOrUpdate(entity);
    }

    @CacheStoreEvict(value = "entity", keys = {"page:*", "list", "#id"})
    public boolean cacheUpdateById(T entity) {
        return super.updateById(entity);
    }

    @CacheStoreEvict(value = "entity", keys = {"page:*", "list", "#id"})
    public boolean cacheRemoveById(Serializable id) {
        return super.removeById(id);
    }

    @CacheStorePut(value = "entity", key = "#id")
    public T cacheGetById(Serializable id) {
        return getById(id);
    }

    @CacheStorePut(value = "entity", key = "list")
    public List<T> cacheList() {
        return list();
    }

    @CacheStorePut(value = "entity", key = "'page' + #page.cacheKey()")
    public <E extends IPage<T>> E cachePage(E page) {
        return page(page);
    }

    @CacheStorePut(value = "entity", key = "'page' + #page.cacheKey()")
    public <E extends IPage<T>> E cachePage(E page, Wrapper<T> queryWrapper) {
        return page(page, queryWrapper);
    }


}
