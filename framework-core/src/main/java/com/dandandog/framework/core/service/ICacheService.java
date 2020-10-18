package com.dandandog.framework.core.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dandandog.framework.common.model.IEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author JohnnyLiu
 */
public interface ICacheService<T extends IEntity> extends IService<T> {

    boolean cacheSave(T entity);

    boolean cacheSaveOrUpdate(T entity);

    boolean cacheUpdateById(T entity);

    boolean cacheRemoveById(Serializable id);

    T cacheGetById(Serializable id);

    List<T> cacheList();

    <E extends IPage<T>> E cachePage(E page);

    <E extends IPage<T>> E cachePage(E page, Wrapper<T> queryWrapper);


}
