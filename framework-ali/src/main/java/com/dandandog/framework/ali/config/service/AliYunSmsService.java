package com.dandandog.framework.ali.config.service;

import com.aliyun.dysmsapi20170525.models.*;

/**
 * @Author: Stephen
 * @date: 2021-11-02 16:36
 */
public interface AliYunSmsService {

    /**
     * 发生短信
     *
     * @param param 请求参数
     * @return SendSmsResponse
     */
    SendSmsResponse sendSms(SendSmsRequest param);

    /**
     * 批量发送短信
     *
     * @param params 请求参数
     * @return SendBatchSmsResponse
     */
    SendBatchSmsResponse sendBatchSms(SendBatchSmsRequest params);

    /**
     * 查询模板
     *
     * @param request 请求参数
     * @return QuerySmsTemplateResponse
     */
    QuerySmsTemplateResponse queryTemplate(QuerySmsTemplateRequest request);

    /**
     * 添加模板
     *
     * @param request 请求参数
     * @return AddSmsTemplateResponse
     */
    AddSmsTemplateResponse addTemplate(AddSmsTemplateRequest request);

    /**
     * 修改未通过的模板
     *
     * @param request 请求参数
     * @return ModifySmsTemplateResponse
     */
    ModifySmsTemplateResponse modifyTemplate(ModifySmsTemplateRequest request);

    /**
     * 删除模板
     *
     * @param request 请求参数
     * @return ModifySmsTemplateResponse
     */
    DeleteSmsTemplateResponse deleteTemplate(DeleteSmsTemplateRequest request);


}
