package com.dandandog.framework.core.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dandandog.framework.cache.annotation.CacheStoreEvict;
import com.dandandog.framework.cache.annotation.CacheStorePut;
import com.dandandog.framework.common.model.IEntity;
import com.dandandog.framework.core.service.ICacheService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class BaseServiceImpl<M extends BaseMapper<T>, T extends IEntity> extends ServiceImpl<M, T> implements ICacheService<T> {

    public boolean isCurrentModelClass(Class<?> clazz) {
        return ObjectUtil.equal(super.currentModelClass(), clazz);
    }

    @CacheStoreEvict(value = "entity", keys = {"page:*", "list"})
    public boolean save(T entity) {
        return super.save(entity);
    }

    @CacheStoreEvict(value = "entity", keys = {"page:*", "list", "#entity.id"})
    public boolean saveOrUpdate(T entity) {
        return super.saveOrUpdate(entity);
    }

    @CacheStoreEvict(value = "entity", keys = {"page:*", "list", "#entity.id"})
    public boolean saveOrUpdate(T entity, Wrapper<T> queryWrapper) {
        return super.saveOrUpdate(entity, queryWrapper);
    }

    @CacheStoreEvict(value = "entity", keys = {"page:*", "list", "#entity.id"})
    public boolean updateById(T entity) {
        return super.updateById(entity);
    }

    @CacheStoreEvict(value = "entity", keys = {"page:*", "list"})
    public boolean update(Wrapper<T> updateWrapper) {
        return super.update(updateWrapper);
    }

    @CacheStoreEvict(value = "entity", keys = {"page:*", "list", "#id"})
    public boolean update(T entity, Wrapper<T> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @CacheStoreEvict(value = "entity", keys = {"page:*", "list", "#id"})
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @CacheStoreEvict(value = "entity", keys = {"page:*", "list"})
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @CacheStoreEvict(value = "entity", keys = {"page:*", "'list:' + #queryWrapper.targetSql", "'one:' + #queryWrapper.targetSql"})
    public boolean remove(Wrapper<T> queryWrapper) {
        return super.remove(queryWrapper);
    }


    @CacheStorePut(value = "entity", key = "#id")
    public T cacheGetById(Serializable id) {
        return getById(id);
    }

    @CacheStorePut(value = "entity", key = "'one:' + #queryWrapper.targetSql")
    public T cacheGetOne(Wrapper<T> queryWrapper) {
        return getOne(queryWrapper);
    }

    @CacheStorePut(value = "entity", key = "list")
    public List<T> cacheList() {
        return list();
    }

    @CacheStorePut(value = "entity", key = "'list:' + #queryWrapper.targetSql")
    public List<T> cacheList(Wrapper<T> queryWrapper) {
        return list(queryWrapper);
    }

    @CacheStorePut(value = "entity", key = "'page:' + #page.cacheKey()")
    public <E extends IPage<T>> E cachePage(E page) {
        return page(page);
    }

    @CacheStorePut(value = "entity", key = "'page:' + #page.cacheKey() + ':' + #queryWrapper.targetSql")
    public <E extends IPage<T>> E cachePage(E page, Wrapper<T> queryWrapper) {
        return page(page, queryWrapper);
    }
}
