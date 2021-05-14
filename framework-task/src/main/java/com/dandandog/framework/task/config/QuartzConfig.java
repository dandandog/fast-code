package com.dandandog.framework.task.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/5/12 17:24
 */

@Configuration
@EnableScheduling
public class QuartzConfig {

//    @Bean
//    public ServletListenerRegistrationBean<QuartzInitializerListener> quartzInitializerListener() {
//        return new ServletListenerRegistrationBean<>(new QuartzInitializerListener());
//    }

//    @Bean
//    public JobFactory jobFactory(ApplicationContext applicationContext) {
//        JobFactory jobFactory = new JobFactory();
//        jobFactory.setApplicationContext(applicationContext);
//        return jobFactory;
//    }

//    @Bean("schedulerFactory")
//    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
//        SchedulerFactoryBean factory = new SchedulerFactoryBean();
//        //factory.setJobFactory(jobFactory);
//        //factory.setDataSource(dataSource);
//        factory.setOverwriteExistingJobs(true);
//        factory.setAutoStartup(true);
//        factory.setStartupDelay(30);
////        factory.setQuartzProperties(quartzProperties());
//        return factory;
//    }

//    @Bean
//    public Properties quartzProperties() throws IOException {
//        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
//        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
//        propertiesFactoryBean.afterPropertiesSet();
//        return propertiesFactoryBean.getObject();
//    }

}
