package com.dandandog.framework.api.jdopenapi.param;

import lombok.Data;

import java.util.List;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */
@Data
public class AfterSaleNewCreateAfsApplyParam {

    private String customerPin;
    private String orderId;
    private String thirdApplyId;
    private Boolean isHasInvoice;
    private AfsCustomerInfoParam customerInfo;
    private AfsPickupWareInfoParam pickwareInfo;
    private AfsReturnWareInfoParam returnWareInfo;
    private List<AfsApplyInfoItemParam> afsApplyInfoItemList;

}
