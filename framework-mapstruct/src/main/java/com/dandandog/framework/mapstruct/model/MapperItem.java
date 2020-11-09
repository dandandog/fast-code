package com.dandandog.framework.mapstruct.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author JohnnyLiu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MapperItem extends MapperVo {

    private String id;

    private String title;

}
