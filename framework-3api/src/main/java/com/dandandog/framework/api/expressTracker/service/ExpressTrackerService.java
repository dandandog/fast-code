package com.dandandog.framework.api.expressTracker.service;

import com.dandandog.framework.api.expressTracker.entity.ExpressTrackerInfo;

/**
 * @author JohnnyLiu
 */
public interface ExpressTrackerService {

    /**
     * @param expCode 快递公司代号
     * @param expNo   快递单号
     * @return Json
     * @throws Exception 异常
     *                   <p>
     *                   通过公司代号和快递单号查询物流信息
     */
    String getOrderTracesByJson(String expCode, String expNo) throws Exception;

    ExpressTrackerInfo getOrderTracesByObj(String expCode, String expNo) throws Exception;
}
