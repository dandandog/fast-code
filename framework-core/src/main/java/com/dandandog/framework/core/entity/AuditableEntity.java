package com.dandandog.framework.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 可查证性的实体类
 *
 * @author JohnnyLiu
 */
@Getter
@Setter
public class AuditableEntity extends AbstractEntity {

    @TableField(fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NOT_NULL)
    protected String creator;

    @TableField(fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NOT_NULL)
    protected LocalDateTime createdTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected String operator;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime operatedTime;

}
