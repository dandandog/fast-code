package com.dandandog.framework.wx.model;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * @author JohnnyLiu
 */
@Data
@Accessors(chain = true)
public class WxMsgTemplate implements Serializable {

    private String id;

    private String toUser;

    private String page;

    private Map<String, String> data = Maps.newHashMap();


    public WxMsgTemplate put(String key, String value) {
        this.getData().put(key, value);
        return this;
    }

}
