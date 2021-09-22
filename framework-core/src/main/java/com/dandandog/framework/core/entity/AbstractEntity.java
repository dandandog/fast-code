package com.dandandog.framework.core.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Getter;
import lombok.Setter;

/**
 * @author JohnnyLiu
 */
@Getter
@Setter
public abstract class AbstractEntity extends BaseEntity {

    @TableLogic
    protected Boolean del = Boolean.FALSE;
}
