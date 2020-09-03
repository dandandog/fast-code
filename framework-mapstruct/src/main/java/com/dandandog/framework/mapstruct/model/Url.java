package com.dandandog.framework.mapstruct.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author JohnnyLiu
 */
@Data
@AllArgsConstructor
public class Url {

    private String value;

    @Override
    public String toString() {
        return getValue();
    }

}
