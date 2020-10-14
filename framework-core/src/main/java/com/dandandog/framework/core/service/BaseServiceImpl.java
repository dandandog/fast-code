package com.dandandog.framework.core.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {


    @Caching(
            evict = {
                    @CacheEvict(value = "list", key = "#root.targetClass + ':list'", beforeInvocation = true),
                    @CacheEvict(value = "page", condition = "T(com.dandandog.framework.core.cache.PageCacheHelper).canEvict(#root.caches[0])", beforeInvocation = true),
                    @CacheEvict(value = "entity", key = "#root.targetClass + #entity.id", beforeInvocation = true)
            }
    )
    public boolean save(T entity) {
        return super.save(entity);
    }

    @CacheEvict(value = "list", key = "#root.targetClass + ':list'")
    public boolean update(T entity, Wrapper<T> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @CacheEvict(value = "entity", key = "#root.targetClass + ':' + #entity.id", beforeInvocation = true)
    public boolean updateById(T entity) {
        return super.updateById(entity);
    }

    @CacheEvict(value = "entity", key = "#root.targetClass + ':' + #id", beforeInvocation = true)
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @CacheEvict(value = "list", key = "#root.targetClass + ':list'")
    public boolean remove(Wrapper<T> queryWrapper) {
        return super.remove(queryWrapper);
    }

    @Cacheable(value = "entity", key = "#root.targetClass + ':' + #id")
    public T getById(Serializable id) {
        return super.getById(id);
    }

    @Cacheable(value = "list", key = "#root.targetClass + ':list'")
    public List<T> list(Wrapper<T> queryWrapper) {
        return super.list(queryWrapper);
    }

    @Cacheable(value = "list", key = "#root.targetClass + ':list'")
    public List<T> list() {
        return super.list();
    }

    @Cacheable(value = "page", key = "#root.targetClass + ':' + #page.cacheKey()")
    public <E extends IPage<T>> E page(E page, Wrapper<T> queryWrapper) {
        return super.page(page, queryWrapper);
    }

    @Cacheable(value = "page", key = "#root.targetClass + ':' + #page.cacheKey()")
    public <E extends IPage<T>> E page(E page) {
        return super.page(page);
    }

    public boolean isCurrentModelClass(Class<?> clazz) {
        return ObjectUtil.equal(super.currentModelClass(), clazz);
    }


}
