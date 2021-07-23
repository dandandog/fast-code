package com.dandandog.framework.api.jdopenapi.param;

import com.dandandog.framework.api.jdopenapi.entity.AbstractAPIRequest;
import com.dandandog.framework.api.jdopenapi.result.ApiOrderSubmitOrderResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: StephenZhang
 * @date: 2021-07-23 10:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiOrderSubmitOrderParam extends AbstractAPIRequest<ApiOrderSubmitOrderResult> {

    private String thirdOrder;
    private String sku;
    private String name;
    private Integer province;
    private Integer city;
    private Integer county;
    private Integer town;
    private String address;
    private String zip;
    private String phone;
    private String mobile;
    private String email = "1030249232@qq.com";
    private String remark;
    private Integer invoiceState;
    private Integer invoiceType;
    private Integer selectedInvoiceTitle;
    private String companyName;
    private Integer invoiceContent;
    private Integer paymentType;
    private String payDetails;
    private Integer isUseBalance;
    private Integer submitState;
    private String invoiceName;
    private String invoicePhone;
    private Integer invoiceProvice;
    private Integer invoiceCity;
    private Integer invoiceCounty;
    private String invoiceAddress;
    private String regCompanyName;
    private String regCode;
    private String regAddr;
    private String regPhone;
    private String regBank;
    private String regBankAccount;
    private Integer reservingDate;
    private Integer installDate;
    private Boolean needInstall;
    private String promiseDate;
    private String promiseTimeRange;
    private Integer promiseTimeRangeCode;
    private String reservedDateStr;
    private String reservedTimeRange;
    private String cycleCalendar;
    private String poNo;
    private Boolean validHolidayVocation;
    private String thrPurchaserAccount;
    private String thrPurchaserName;
    private String thrPurchaserPhone;


    @Override
    public String getUrl() {
        return "/api/order/submitOrder";
    }
}
