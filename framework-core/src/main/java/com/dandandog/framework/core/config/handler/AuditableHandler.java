package com.dandandog.framework.core.config.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.dandandog.framework.common.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author JohnnyLiu
 */
@Slf4j
@Component
public class AuditableHandler implements MetaObjectHandler {


    public AuditableHandler() {
        log.debug("AuditableHandler initialization");
    }

    @Override
    public void insertFill(MetaObject metaObject) {

        this.strictInsertFill(metaObject, "creator", SecurityUtil::getCurrUsername, String.class);
        this.strictInsertFill(metaObject, "operator", SecurityUtil::getCurrUsername, String.class);
        this.strictInsertFill(metaObject, "createdTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "operatedTime", LocalDateTime::now, LocalDateTime.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createdTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "operatedTime", LocalDateTime::now, LocalDateTime.class);
    }

}