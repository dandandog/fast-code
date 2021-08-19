package com.dandandog.framework.common.utils;

import com.dandandog.framework.common.components.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author JohnnyLiu
 */
@Component
public class SecurityUtil {


    private static IAuthenticationFacade authenticationFacade;

    @Autowired(required = false)
    public SecurityUtil(IAuthenticationFacade authenticationFacade) {
        SecurityUtil.authenticationFacade = authenticationFacade;
    }

    public SecurityUtil() {

    }

    public static String getUniqueId() {
        return authenticationFacade != null ? authenticationFacade.getUniqueId() : null;
    }


    public static boolean isLogin() {
        return authenticationFacade != null && authenticationFacade.isLogin();
    }

}
