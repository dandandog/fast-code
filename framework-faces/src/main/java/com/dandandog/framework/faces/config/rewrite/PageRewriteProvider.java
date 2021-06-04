package com.dandandog.framework.faces.config.rewrite;

import cn.hutool.core.util.StrUtil;
import com.dandandog.framework.faces.config.properties.PageProperties;
import lombok.AllArgsConstructor;
import org.ocpsoft.logging.Logger.Level;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.config.Log;
import org.ocpsoft.rewrite.servlet.config.Forward;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;
import org.ocpsoft.rewrite.servlet.config.rule.Join;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

/**
 * @author JohnnyLiu
 */
@Component
@AllArgsConstructor
@EnableConfigurationProperties(PageProperties.class)
public class PageRewriteProvider extends HttpConfigurationProvider {

    private final PageProperties properties;

    @Override
    public Configuration getConfiguration(ServletContext context) {
        return ConfigurationBuilder.begin()
                .addRule()
                .when(Direction.isInbound().and(Path.matches("/")))
                .perform(Redirect.temporary(context.getContextPath() + properties.getIndex()))
                .addRule(Join.path("/{path}").to(StrUtil.addSuffixIfNot("/{path}", ".faces")))
                .where("path").matches(".*").convertedBy((rewrite, evaluationContext, o) -> o.toString())
                ;
    }

    @Override
    public int priority() {
        return 10;
    }
}
