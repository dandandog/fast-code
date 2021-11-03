package com.dandandog.framework.ali.config.service.impl;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendBatchSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendBatchSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.dandandog.framework.ali.config.service.AliYunSmsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: StephenZhang
 * @date: 2021-11-02 16:40
 */
@Service
public class AliYunSmsServiceImpl implements AliYunSmsService {

    @Resource
    private Client client;

    @Override
    public SendSmsResponse sendSms(SendSmsRequest sendSmsRequest) {
        SendSmsResponse response = null;
        try {
            response = client.sendSms(sendSmsRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public SendBatchSmsResponse sendBatchSms(SendBatchSmsRequest sendBatchSmsRequest) {
        SendBatchSmsResponse response = null;
        try {
            response = client.sendBatchSms(sendBatchSmsRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
