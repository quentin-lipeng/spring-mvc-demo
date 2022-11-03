package org.quentin.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author quentin
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("org.quentin.web.aspectj")
public class AopConfig {
}
