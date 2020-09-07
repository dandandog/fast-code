package com.dandandog.framework.faces.utils;

import org.primefaces.PrimeFaces;

/**
 * @author create by Johnny
 * @description com.johnny.web.restful
 * @date 七月 06,2019
 */
public class RequestContextUtils {

    public static void execute(String script) {
        PrimeFaces.current().executeScript(script);
    }

    public static void update(String componentId) {
        PrimeFaces.current().ajax().update(componentId);
    }

    public static void executeAndUpdate(String script, String componentId) {
        execute(script);
        update(componentId);
    }
}
