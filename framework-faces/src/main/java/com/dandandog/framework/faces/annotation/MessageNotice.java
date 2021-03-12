package com.dandandog.framework.faces.annotation;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/3/12 15:40
 */
@AllArgsConstructor
@Getter
public enum MessageNotice {

    GROWL("growl"), MESSAGES("messages"), ALL(null);

    private final String clientId;
}
