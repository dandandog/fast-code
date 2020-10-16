package com.dandandog.framework.core.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dandandog.framework.common.model.IEntity;

/**
 * @author JohnnyLiu
 */
public class CacheServiceImpl<M extends BaseMapper<T>, T extends IEntity> extends BaseServiceImpl<M, T> implements ICacheService<T> {


}
