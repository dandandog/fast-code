package com.dandandog.framework.core.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dandandog.framework.cache.annotation.CacheStore;
import com.dandandog.framework.cache.annotation.CacheDelete;
import com.dandandog.framework.common.model.IEntity;
import org.springframework.cache.annotation.Cacheable;

import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<M extends BaseMapper<T>, T extends IEntity> extends ServiceImpl<M, T> implements ICacheService<T> {

    public boolean isCurrentModelClass(Class<?> clazz) {
        return ObjectUtil.equal(super.currentModelClass(), clazz);
    }

    @CacheDelete({"list + '::' + #root.targetClass", "page + '::' + #entity"})
    public boolean cacheSave(T entity) {
        return super.save(entity);
    }

    @CacheDelete({"list + '::' + #root.targetClass", "page + '::' + #entity"})
    public boolean cacheSaveOrUpdate(T entity) {
        return super.saveOrUpdate(entity);
    }

    @CacheDelete({"list + '::' + #root.targetClass", "page + '::' + #entity"})
    public boolean cacheUpdate(T entity, Wrapper<T> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @CacheDelete({"list + '::' + #root.targetClass", "page + '::' + #entity"})
    public boolean cacheUpdateById(T entity) {
        return super.updateById(entity);
    }

    @CacheDelete({"list + '::' + #root.targetClass", "page + '::' + #entity"})
    public boolean cacheRemove(Wrapper<T> queryWrapper) {
        return super.remove(queryWrapper);
    }

    @CacheDelete({"list + '::' + #root.targetClass", "page + '::' + #entity", "entity + '::' + #root.targetClass + #entity.id"})
    public boolean cacheRemoveById(Serializable id) {
        return super.removeById(id);
    }

    @CacheStore("entity + '::' + #root.targetClass + #entity.id")
    public T cacheGetById(Serializable id) {
        return getById(id);
    }

    @CacheStore("entity + '::' + #root.targetClass + #entity.id")
    public T cacheGetOne(Wrapper<T> queryWrapper) {
        return getOne(queryWrapper);
    }

    @Cacheable("#root")
    public List<T> cacheList() {
        return list();
    }

    @CacheStore("list + '::' + #root.targetClass")
    public List<T> cacheList(Wrapper<T> queryWrapper) {
        return list(queryWrapper);
    }

    @Cacheable(key="#root")
    public <E extends IPage<T>> E cachePage(E page, Wrapper<T> queryWrapper) {
        return page(page, queryWrapper);
    }

    @CacheStore(value = "list", key = "#root.targetClass")
    public <E extends IPage<T>> E cachePage(E page) {
        return page(page);
    }


}
