package com.dandandog.framework.wx.exception;

import com.dandandog.framework.common.model.IError;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.shiro.authc.AuthenticationException;

/**
 * @author JohnnyLiu
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class WxTokenException extends AuthenticationException {

    private IError error;

    public WxTokenException(IError error) {
        super(error.getMsg());
        this.error = error;
    }
}
