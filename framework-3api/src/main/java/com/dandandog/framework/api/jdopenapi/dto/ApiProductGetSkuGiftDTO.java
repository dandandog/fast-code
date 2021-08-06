package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/8/6 16:39
 */
@Data
public class ApiProductGetSkuGiftDTO {

    private List<GiftDTO> gifts;

    private Integer maxNum;

    private Integer minNum;

    private Long promoStartTime;

    private Long promoEndTime;

}
