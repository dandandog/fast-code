package com.dandandog.framework.ali.config.service;

import com.aliyun.dysmsapi20170525.models.SendBatchSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendBatchSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;

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

}
