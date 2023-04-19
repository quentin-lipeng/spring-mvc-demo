package org.quentin.web.config;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author:quentin
 * @create: 2022-10-15 15:39
 * @Description:
 */
@Configuration
@ComponentScan({"org.quentin.web.service"})
public class SpringBeanConfig {

}
