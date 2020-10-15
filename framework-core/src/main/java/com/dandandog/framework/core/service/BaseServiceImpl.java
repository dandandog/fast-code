package com.dandandog.framework.core.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dandandog.framework.common.model.IEntity;

public class BaseServiceImpl<M extends BaseMapper<T>, T extends IEntity> extends ServiceImpl<M, T> {

    public boolean isCurrentModelClass(Class<?> clazz) {
        return ObjectUtil.equal(super.currentModelClass(), clazz);
    }

}
