package com.dandandog.framework.api.jdopenapi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 14:48
 */
@Data
public class ApiOrderSelectJdOrderDTO {

    private Integer pOrder;
    private Integer orderState;
    private Long jdOrderId;
    private Integer state;
    private Integer submitState;
    private Integer type;
    private BigDecimal freight;
    private List<OrderDetailsSkuDTO> sku;
    private BigDecimal orderPrice;
    private BigDecimal orderNakedPrice;
    private BigDecimal orderTaxPrice;
    private Integer orderType;
    private String createOrderTime;
    private String finishTime;
    private Integer jdOrderState;
    private String address;
    private String name;
    private String mobile;
    private Integer paymentType;
    private List<OrderDetailsPayDetailsDTO> payDeatails;
    private Date outTime;
    private String invoiceType;

}
