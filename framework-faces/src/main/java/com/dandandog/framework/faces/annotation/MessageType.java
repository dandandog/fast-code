package com.dandandog.framework.faces.annotation;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author JohnnyLiu
 */
@AllArgsConstructor
@Getter
public enum MessageType {

    OPERATION("operationSuccess", "operationFailed"),
    SAVE("saveSuccess", "saveFailed"),
    DELETE("deleteSuccess", "deleteFailed"),
    DOWNLOAD("downloadSuccess", "downloadFailed"),
    UPLOAD("uploadSuccess", "uploadFailed");


    private final String successCode;

    private final String failedCode;

}
