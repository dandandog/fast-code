package com.dandandog.framework.core.config.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.dandandog.framework.core.utils.ParameterUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;

/**
 * @author JohnnyLiu
 */
public class SortInnerInterceptor implements InnerInterceptor {


    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        QueryWrapper<?> wrapper = ParameterUtils.findWrapper(parameter).orElse(null);
        //TODO @Sort自动排序
    }


    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        //TODO @Sort自动插入排序
    }


}
