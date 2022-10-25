package org.quentin.web.config;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.event.EventBus;
import org.apache.shiro.event.support.DefaultEventBus;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.ShiroEventBusBeanPostProcessor;
import org.apache.shiro.spring.config.ShiroAnnotationProcessorConfiguration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroRequestMappingConfig;
import org.apache.shiro.spring.web.config.ShiroWebConfiguration;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.quentin.web.service.ResourceService;
import org.quentin.web.shiro.AccountRealm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.util.Collections;
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

    private SecurityManager securityManager;
    private final Map<String, String> resourceMap;

    public ShiroConfig(
            @Qualifier("resourceServiceImpl") ResourceService resourceService) {
        this.resourceMap = resourceService.resourceMap();
    }

    @Resource
    public void setSecurityManager(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        // 通过数据库动态加载
        chainDefinition.addPathDefinitions(this.resourceMap);

        // 也可以使用map一次性把所有规则注入
//        chainDefinition.addPathDefinition("/error", "anon");
//        chainDefinition.addPathDefinition("/welcome", "anon");
//        chainDefinition.addPathDefinition("/resource", "anon");
//        chainDefinition.addPathDefinition("/resource/list", "anon");
//        chainDefinition.addPathDefinition("/", "anon");
//        chainDefinition.addPathDefinition("/auth/login", "anon");
//        chainDefinition.addPathDefinition("/auth/register", "anon");
//        // all other paths require a logged in user
//        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }

    public List<String> globalFilters() {
        return Collections.singletonList(DefaultFilter.invalidRequest.name());
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        //使用方法作为参数需要在@Configuration类下才可使用 否则使用上面的方法 通过成员方法注入 方式选其一
//        filterFactoryBean.setSecurityManager(securityManager());
        // 通过向此处传入所有映射
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
    public DefaultWebSecurityManager securityManager(
            AccountRealm accRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(accRealm);
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    @Bean
    public EventBus eventBus() {
        return new DefaultEventBus();
    }

    @Bean
    public ShiroEventBusBeanPostProcessor shiroEventBusAwareBeanPostProcessor(
            EventBus eventBus) {
        return new ShiroEventBusBeanPostProcessor(eventBus);
    }
}
