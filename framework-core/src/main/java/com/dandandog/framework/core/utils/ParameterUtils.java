package com.dandandog.framework.core.utils;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;
import java.util.Optional;

/**
 * @author JohnnyLiu
 */
public class ParameterUtils {

    private ParameterUtils() {

    }

    /**
     * 查找分页参数
     *
     * @param parameterObject 参数对象
     * @return 分页参数
     */
    public static Optional<QueryWrapper> findWrapper(Object parameterObject) {
        if (parameterObject != null) {
            if (parameterObject instanceof Map) {
                Map<?, ?> parameterMap = (Map<?, ?>) parameterObject;
                for (Map.Entry entry : parameterMap.entrySet()) {
                    if (entry.getValue() != null && entry.getValue() instanceof Wrapper) {
                        return Optional.of((QueryWrapper) entry.getValue());
                    }
                }
            } else if (parameterObject instanceof Wrapper) {
                return Optional.of((QueryWrapper) parameterObject);
            }
        }
        return Optional.empty();
    }
}
