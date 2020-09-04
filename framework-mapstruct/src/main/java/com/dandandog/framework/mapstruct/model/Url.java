package com.dandandog.framework.mapstruct.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author JohnnyLiu
 */
@Slf4j
@Data
@AllArgsConstructor
public class Url {

    private String value;

    @Override
    public String toString() {
        return getValue();
    }

}
