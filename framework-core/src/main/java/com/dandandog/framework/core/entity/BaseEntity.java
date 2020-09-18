package com.dandandog.framework.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 通用实体类
 *
 * @author JohnnyLiu
 */
@Getter
@Setter
public class BaseEntity implements Serializable {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    protected String id;

    @TableLogic
    protected Boolean del = Boolean.FALSE;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof BaseEntity)) {
            BaseEntity entity = (BaseEntity) obj;
            if ((entity.getId() == null) || (this.getId() == null)) {
                return false;
            }
            return entity.getId().equals(this.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (this.getId() == null) {
            return super.hashCode();
        }
        return this.getId().hashCode();
    }
}