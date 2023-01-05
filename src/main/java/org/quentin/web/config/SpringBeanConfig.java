package org.quentin.web.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.quentin.web.shiro.AccountRealm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author:quentin
 * @create: 2022-10-15 15:39
 * @Description:
 */
@Configuration
@ComponentScan("org.quentin.web.service.impl")
public class SpringBeanConfig {

    @Bean
    public AccountRealm realm() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        AccountRealm accountRealm = new AccountRealm();

        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        accountRealm.setCredentialsMatcher(credentialsMatcher);
        return accountRealm;
    }

    // Lifecycle and event form ShiroBeanConfiguration
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}
