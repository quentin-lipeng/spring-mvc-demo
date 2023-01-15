package org.quentin.web.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.quentin.web.shiro.AccountRealm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author:quentin
 * @create: 2022-10-15 15:39
 * @Description:
 */
@Configuration
public class SpringBeanConfig {

    @Bean
    public AccountRealm realm() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher("MD5");
        AccountRealm accountRealm = new AccountRealm();
        accountRealm.setCredentialsMatcher(credentialsMatcher);
        return accountRealm;
    }

    // Lifecycle and event form ShiroBeanConfiguration
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}
