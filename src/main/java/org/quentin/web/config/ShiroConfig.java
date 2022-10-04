/**
 * @author:quentin
 * @create: 2022-10-03 12:49
 * @Description:
 */
package org.quentin.web.config;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.event.EventBus;
import org.apache.shiro.event.support.DefaultEventBus;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.ShiroEventBusBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.quentin.web.shiro.AccountRealm;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

public class ShiroConfig {

    @Resource
    private AccountRealm realm;

    @Resource
    private EventBus eventBus;

    @Resource
    private SecurityManager securityManager;

    @Bean
    public AccountRealm realm() {
        AccountRealm accountRealm = new AccountRealm();
        return accountRealm;
    }

    @Bean
    protected CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    //    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        chainDefinition.addPathDefinition("/auth/login", "anon");
        // all other paths require a logged in user
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }

    public List<String> globalFilters() {
        return Collections.singletonList(DefaultFilter.invalidRequest.name());
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition().getFilterChainMap());
        filterFactoryBean.setLoginUrl("/auth/login/");
        filterFactoryBean.setGlobalFilters(globalFilters());

//        filterFactoryBean.setSuccessUrl(successUrl);
//        filterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
//        filterFactoryBean.setFilters(filterMap);
        return filterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    // Lifecycle and event form ShiroBeanConfiguration
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public EventBus eventBus() {
        return new DefaultEventBus();
    }

    @Bean
    public ShiroEventBusBeanPostProcessor shiroEventBusAwareBeanPostProcessor() {
        return new ShiroEventBusBeanPostProcessor(eventBus);
    }
}
