//package com.dandandog.framework.faces.config;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.ocpsoft.rewrite.config.Configuration;
//import org.ocpsoft.rewrite.config.ConfigurationBuilder;
//import org.ocpsoft.rewrite.servlet.config.Forward;
//import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
//import org.ocpsoft.rewrite.servlet.config.Path;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletContext;
//
//@Slf4j
//@Component("rewrite")
//public class RewriteConfig extends HttpConfigurationProvider {
//
//
//    public RewriteConfig() {
//        log.debug("~~~~RewriteConfig~~~~");
//    }
//
//
//    @Override
//    public Configuration getConfiguration(ServletContext servletContext) {
//        log.debug("~~~~~Rewrite rule ~~~~ ");
//        return ConfigurationBuilder.begin()
//                .addRule()
//                .when(Path.matches("/**/{path}.faces").withRequestBinding())
//                .perform(Forward.to("/views/**/{path}"));
//    }
//
//    @Override
//    public int priority() {
//        return 0;
//    }
//}
