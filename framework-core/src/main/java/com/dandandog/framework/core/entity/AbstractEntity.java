package com.dandandog.framework.core.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author JohnnyLiu
 */

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractEntity extends BaseEntity {

    @TableLogic
    protected Boolean del = Boolean.FALSE;
}
