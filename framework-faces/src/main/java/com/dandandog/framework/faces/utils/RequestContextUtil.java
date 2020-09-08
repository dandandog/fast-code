package com.dandandog.framework.faces.utils;

import org.primefaces.PrimeFaces;

/**
 * @author JohnnyLiu
 */
public class RequestContextUtil {

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
