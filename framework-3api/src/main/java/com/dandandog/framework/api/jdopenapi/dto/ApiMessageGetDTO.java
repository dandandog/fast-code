package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 14:48
 */
@Data
public class ApiMessageGetDTO {

    private String id;
    private Map<String, Object> result;
    private Integer type;
    private LocalDateTime time;

}
