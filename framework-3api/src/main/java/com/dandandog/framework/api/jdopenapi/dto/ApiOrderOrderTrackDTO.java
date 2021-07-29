package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 14:48
 */
@Data
public class ApiOrderOrderTrackDTO {

    private OrderTrackDTO[] orderTrack;

    private WaybillCodeDTO[] waybillCode;


}
