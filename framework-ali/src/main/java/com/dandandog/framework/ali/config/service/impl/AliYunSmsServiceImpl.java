package com.dandandog.framework.ali.config.service.impl;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.*;
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

    @Override
    public QuerySmsTemplateResponse queryTemplate(QuerySmsTemplateRequest request) {
        QuerySmsTemplateResponse response = null;
        try {
            response = client.querySmsTemplate(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public AddSmsTemplateResponse addTemplate(AddSmsTemplateRequest request) {
        AddSmsTemplateResponse response = null;
        try {
            response = client.addSmsTemplate(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public ModifySmsTemplateResponse modifyTemplate(ModifySmsTemplateRequest request) {
        ModifySmsTemplateResponse response = null;
        try {
            response = client.modifySmsTemplate(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public DeleteSmsTemplateResponse deleteTemplate(DeleteSmsTemplateRequest request) {
        DeleteSmsTemplateResponse response = null;
        try {
            response = client.deleteSmsTemplate(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
