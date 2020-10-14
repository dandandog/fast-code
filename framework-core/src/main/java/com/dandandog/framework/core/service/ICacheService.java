package com.dandandog.framework.core.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.List;

/**
 * @author JohnnyLiu
 */
public interface ICacheService<T> extends IService<T> {

    boolean save(T entity);

    boolean saveOrUpdate(T entity);

    boolean update(T entity, Wrapper<T> updateWrapper);

    boolean updateById(T entity);

    boolean removeById(Serializable id);

    boolean remove(Wrapper<T> queryWrapper);

    T getById(Serializable id);

    List<T> list(Wrapper<T> queryWrapper);

    List<T> list();

    <E extends IPage<T>> E page(E page, Wrapper<T> queryWrapper);

    <E extends IPage<T>> E page(E page);

}
