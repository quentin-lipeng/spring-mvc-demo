package org.quentin.web.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
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
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author:quentin
 * @create: 2022-10-03 12:49
 * @Description: shiro config file
 */
public class ShiroConfig {

    @Resource
    private AccountRealm realm;

    @Resource
    private EventBus eventBus;

    @Resource
    private SecurityManager securityManager;

    @Bean
    public AccountRealm realm() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        AccountRealm accountRealm = new AccountRealm();

        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        accountRealm.setCredentialsMatcher(credentialsMatcher);
        return accountRealm;
    }


    //    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        // TODO 把所有路径访问权限存储到数据库 并通过前端控制显示
        // 也可以使用map一次性把所有规则注入
        chainDefinition.addPathDefinition("/error", "anon");
        chainDefinition.addPathDefinition("/welcome", "anon");
        chainDefinition.addPathDefinition("/resource", "anon");
        chainDefinition.addPathDefinition("/resource/list", "anon");
        chainDefinition.addPathDefinition("/", "anon");
        chainDefinition.addPathDefinition("/auth/login", "anon");
        chainDefinition.addPathDefinition("/auth/register", "anon");
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
        //使用方法作为参数需要在@Configuration类下才可使用 否则使用上面的方法 通过成员方法注入
//        filterFactoryBean.setSecurityManager(securityManager());
        filterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition().getFilterChainMap());
        filterFactoryBean.setLoginUrl("/auth/login/");
        filterFactoryBean.setGlobalFilters(globalFilters());

//        filterFactoryBean.setSuccessUrl(successUrl);
//        filterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
//        filterFactoryBean.setFilters(filterMap);
        return filterFactoryBean;
    }

    protected CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }


    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setCacheManager(cacheManager());
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
