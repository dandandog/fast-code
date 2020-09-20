package com.dandandog.framework.faces.config.properties;

import com.dandandog.framework.common.config.constant.FastCodeConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "fast-code.message")
public class MessageProperties {

    private String codePrefix = FastCodeConstant.MESSAGE_CODE_PREFIX;


    private String dialogWidgetVar = FastCodeConstant.MESSAGE_DIALOG_WIDGET_VAR;


    private String growlWidgetVar = FastCodeConstant.MESSAGE_GROWL_WIDGET_VAR;


    private String title = FastCodeConstant.MESSAGE_DEFAULT_TITLE;


}
