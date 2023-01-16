package org.quentin.web.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.event.EventBus;
import org.apache.shiro.event.support.DefaultEventBus;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.ShiroEventBusBeanPostProcessor;
import org.apache.shiro.spring.config.ShiroAnnotationProcessorConfiguration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.ShiroRequestMappingConfig;
import org.apache.shiro.spring.web.config.ShiroWebConfiguration;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.quentin.web.service.ResourceService;
import org.quentin.web.shiro.AccountRealm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:quentin
 * @create: 2022-10-03 12:49
 * @Description: shiro config file
 */
@Configuration
@Import({
        ShiroAnnotationProcessorConfiguration.class,
        ShiroWebConfiguration.class,
        ShiroRequestMappingConfig.class,
})
public class ShiroConfig {

    /**
     * shiro过滤映射规则的map
     * 后续可以通过向此map添加配置进行动态的过滤规则添加（但不一定需要这种功能）
     */
    private final Map<String, String> filterChainMap;

    public ShiroConfig(
            ResourceService resourceService) {
        filterChainMap = resourceService.resourceMap();
    }

    /*@Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // 通过数据库动态加载
        chainDefinition.addPathDefinitions(this.filterChainMap);
        return chainDefinition;
    }*/

    @Bean
    public AccountRealm realm() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher("MD5");
        AccountRealm accountRealm = new AccountRealm();
        accountRealm.setCredentialsMatcher(credentialsMatcher);
        return accountRealm;
    }

    /**
     * Lifecycle and event form ShiroBeanConfiguration
     */
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    public List<String> globalFilters() {
        return Collections.singletonList(DefaultFilter.invalidRequest.name());
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager());
        //使用方法作为参数需要在@Configuration类下才可使用 否则使用上面的方法 通过成员方法注入 方式选其一
        // 如果不在@Configuration下会造成循环依赖的问题
//        filterFactoryBean.setSecurityManager(securityManager());
        // 通过向此处传入所有映射
        filterFactoryBean.setFilterChainDefinitionMap(this.filterChainMap);
        filterFactoryBean.setGlobalFilters(globalFilters());
        filterFactoryBean.setLoginUrl("/auth/login/");
        filterFactoryBean.setSuccessUrl("/home");
//        filterFactoryBean.setFilters(filterMap);
        return filterFactoryBean;
    }

    protected CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    @Bean
    public EventBus eventBus() {
        return new DefaultEventBus();
    }

    @Bean
    public ShiroEventBusBeanPostProcessor shiroEventBusAwareBeanPostProcessor() {
        return new ShiroEventBusBeanPostProcessor(eventBus());
    }

}
