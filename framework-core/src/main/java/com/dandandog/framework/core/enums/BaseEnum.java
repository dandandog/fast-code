package com.dandandog.framework.core.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @author JohnnyLiu
 */
public interface BaseEnum extends IEnum<Integer> {


    /**
     * 枚举持久化值
     *
     * @return Integer
     */
    @Override
    Integer getValue();

    /**
     * 枚举标题
     *
     * @return String
     */
    String getLabel();


    /**
     * 枚举名字
     *
     * @return String
     */
    String getName();


}
