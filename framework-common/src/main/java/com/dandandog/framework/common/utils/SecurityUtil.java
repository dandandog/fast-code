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

    @Autowired
    public SecurityUtil(IAuthenticationFacade authenticationFacade) {
        SecurityUtil.authenticationFacade = authenticationFacade;
    }


    public static String getUniqueId() {
        return authenticationFacade.getUniqueId();
    }


    public static boolean isLogin() {
        return authenticationFacade.isLogin();
    }

}
