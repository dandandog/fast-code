package com.dandandog.framework.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 可查证性的实体类
 *
 * @author JohnnyLiu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuditableEntity extends AbstractEntity {

    @TableField(fill = FieldFill.INSERT)
    protected String creator;

    @TableField(fill = FieldFill.INSERT)
    protected LocalDateTime createdTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected String operator;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime operatedTime;

}
