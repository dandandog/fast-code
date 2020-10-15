package com.dandandog.framework.core.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dandandog.framework.common.model.IEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.io.Serializable;
import java.util.List;

/**
 * @author JohnnyLiu
 */
public class CacheServiceImpl<M extends BaseMapper<T>, T extends IEntity> extends BaseServiceImpl<M, T> implements ICacheService<T> {

    @Caching(
            evict = {
                    @CacheEvict(value = "list", key = "#root.targetClass + ':list'", beforeInvocation = true),
                    @CacheEvict(value = "page", allEntries = true)
            },
            put = {
                    @CachePut(value = "entity", key = "#root.targetClass + #entity.id")
            }
    )
    @Override
    public boolean cacheSave(T entity) {
        return super.save(entity);
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "list", key = "#root.targetClass + ':list'", beforeInvocation = true),
                    @CacheEvict(value = "entity", key = "#root.targetClass + #entity.id", beforeInvocation = true),
                    @CacheEvict(value = "page", allEntries = true)
            },
            put = {
                    @CachePut(value = "entity", key = "#root.targetClass + #entity.id")
            }
    )
    @Override
    public boolean cacheSaveOrUpdate(T entity) {
        return super.saveOrUpdate(entity);
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "list", key = "#root.targetClass + ':list'", beforeInvocation = true),
                    @CacheEvict(value = "entity", key = "#root.targetClass + #entity.id", beforeInvocation = true),
                    @CacheEvict(value = "page", allEntries = true)
            }
    )
    @Override
    public boolean cacheUpdate(T entity, Wrapper<T> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "list", key = "#root.targetClass + ':list'", beforeInvocation = true),
                    @CacheEvict(value = "entity", key = "#root.targetClass + #entity.id", beforeInvocation = true),
                    @CacheEvict(value = "page", allEntries = true)
            }
    )
    @Override
    public boolean cacheUpdateById(T entity) {
        return super.updateById(entity);
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "list", key = "#root.targetClass + ':list'", beforeInvocation = true),
                    @CacheEvict(value = "page", allEntries = true)
            }
    )
    @Override
    public boolean cacheRemove(Wrapper<T> queryWrapper) {
        return false;
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "list", key = "#root.targetClass + ':list'", beforeInvocation = true),
                    @CacheEvict(value = "entity", key = "#root.targetClass + #id", beforeInvocation = true),
                    @CacheEvict(value = "page", allEntries = true)
            }
    )
    @Override
    public boolean cacheRemoveById(Serializable id) {
        return false;
    }

    @Cacheable(value = "entity", key = "#root.targetClass + #id")
    @Override
    public T cacheGetById(Serializable id) {
        return null;
    }

    @Cacheable(value = "entity", key = "#root.targetClass + #queryWrapper.sqlSelect")
    @Override
    public T cacheGetOne(Wrapper<T> queryWrapper) {
        return null;
    }

    @Cacheable(value = "list", key = "#root.targetClass + ':list'")
    @Override
    public List<T> cacheList() {
        return null;
    }

    @Cacheable(value = "list", key = "#root.targetClass + ':list:'")
    @Override
    public List<T> cacheList(Wrapper<T> queryWrapper) {
        return null;
    }

    @Cacheable(value = "page", key = "#root.targetClass + ':' + #page.cacheKey()")
    @Override
    public <E extends IPage<T>> E cachePage(E page, Wrapper<T> queryWrapper) {
        return null;
    }

    @Cacheable(value = "page", key = "#root.targetClass + ':' + #page.cacheKey()")
    @Override
    public <E extends IPage<T>> E cachePage(E page) {
        return null;
    }
}
