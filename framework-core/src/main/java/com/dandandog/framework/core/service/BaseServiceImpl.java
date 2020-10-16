package com.dandandog.framework.core.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dandandog.framework.cache.annotation.CacheCheck;
import com.dandandog.framework.cache.annotation.CacheRemove;
import com.dandandog.framework.cache.annotation.CacheUpdate;
import com.dandandog.framework.common.model.IEntity;

import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<M extends BaseMapper<T>, T extends IEntity> extends ServiceImpl<M, T> implements ICacheService<T> {

    public boolean isCurrentModelClass(Class<?> clazz) {
        return ObjectUtil.equal(super.currentModelClass(), clazz);
    }

    @CacheRemove({"list + '::' + #root.targetClass", "page + '::' + #entity"})
    @CacheUpdate("entity + '::' + #root.targetClass + #entity.id")
    public boolean cacheSave(T entity) {
        return super.save(entity);
    }

    @CacheRemove({"list + '::' + #root.targetClass", "page + '::' + #entity"})
    @CacheUpdate("entity + '::' + #root.targetClass + #entity.id")
    public boolean cacheSaveOrUpdate(T entity) {
        return super.saveOrUpdate(entity);
    }

    @CacheRemove({"list + '::' + #root.targetClass", "page + '::' + #entity"})
    @CacheUpdate("entity + '::' + #root.targetClass + #entity.id")
    public boolean cacheUpdate(T entity, Wrapper<T> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @CacheRemove({"list + '::' + #root.targetClass", "page + '::' + #entity"})
    @CacheUpdate("entity + '::' + #root.targetClass + #id")
    public boolean cacheUpdateById(T entity) {
        return super.updateById(entity);
    }

    @CacheRemove({"list + '::' + #root.targetClass", "page + '::' + #entity"})
    public boolean cacheRemove(Wrapper<T> queryWrapper) {
        return super.remove(queryWrapper);
    }

    @CacheRemove({"list + '::' + #root.targetClass", "page + '::' + #entity", "entity + '::' + #root.targetClass + #entity.id"})
    public boolean cacheRemoveById(Serializable id) {
        return super.removeById(id);
    }

    @CacheCheck("entity + '::' + #root.targetClass + #entity.id")
    public T cacheGetById(Serializable id) {
        return getById(id);
    }

    @CacheCheck("entity + '::' + #root.targetClass + #entity.id")
    public T cacheGetOne(Wrapper<T> queryWrapper) {
        return getOne(queryWrapper);
    }

    @CacheCheck("list + '::' + #root.targetClass")
    public List<T> cacheList() {
        return list();
    }

    @CacheCheck("list + '::' + #root.targetClass")
    public List<T> cacheList(Wrapper<T> queryWrapper) {
        return list(queryWrapper);
    }

    @CacheCheck("list + '::' + #root.targetClass")
    public <E extends IPage<T>> E cachePage(E page, Wrapper<T> queryWrapper) {
        return page(page, queryWrapper);
    }

    @CacheCheck("list + '::' + #root.targetClass")
    public <E extends IPage<T>> E cachePage(E page) {
        return page(page);
    }


}
