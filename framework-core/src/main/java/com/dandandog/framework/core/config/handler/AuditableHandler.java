package com.dandandog.framework.core.config.handler;

import cn.hutool.core.date.LocalDateTimeUtil;
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

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "creator", SecurityUtil::getUniqueId, String.class);
        this.strictInsertFill(metaObject, "operator", SecurityUtil::getUniqueId, String.class);
        this.strictInsertFill(metaObject, "createdTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "operatedTime", LocalDateTime::now, LocalDateTime.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("operator: " + SecurityUtil.getUniqueId());
        this.strictUpdateFill(metaObject, "operator", String.class, SecurityUtil.getUniqueId());
        log.debug("operated time: " + LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy/MM/dd HH:mm:ss"));
        this.strictUpdateFill(metaObject, "operatedTime", LocalDateTime.class, LocalDateTime.now());
    }

}