package com.dandandog.framework.core.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

import java.io.Serializable;

/**
 * @author JohnnyLiu
 */
public interface BaseEnum<T extends Serializable> extends IEnum<T> {

    String getTitle();
}

