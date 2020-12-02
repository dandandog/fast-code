package com.dandandog.framework.api.expressTracker.entity;

import lombok.Data;

import java.util.List;

/**
 * @author JohnnyLiu
 */
@Data
public class ExpressTrackerInfo {

    private String LogisticCode;

    private String ShipperCode;

    private String State;

    private String EBusinessID;

    private String Reason;

    private boolean Success;

    private List<?> Traces;

}
