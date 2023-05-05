package org.quentin.web.config;

import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.event.EventBus;
import org.apache.shiro.event.support.DefaultEventBus;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.mgt.SubjectDAO;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.ShiroEventBusBeanPostProcessor;
import org.apache.shiro.spring.config.ShiroAnnotationProcessorConfiguration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.ShiroUrlPathHelper;
import org.apache.shiro.spring.web.config.AbstractShiroWebConfiguration;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroWebConfiguration;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.quentin.web.shiro.AccountRealm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

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
		ShiroConfig.ShiroWebConfig.class
})
public class ShiroConfig {

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

	@DependsOn({"securityManager", "shiroFilterChainDefinition"})
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager, ShiroFilterChainDefinition filterChainDefinition) {
		ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
		filterFactoryBean.setSecurityManager(securityManager);
		// 通过向此处传入所有映射规则
		filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinition.getFilterChainMap());
		filterFactoryBean.setLoginUrl("/auth/login");
		filterFactoryBean.setSuccessUrl("/home");
//        filterFactoryBean.setFilters(filterMap);
		return filterFactoryBean;
	}

	@Bean
	public EventBus eventBus() {
		return new DefaultEventBus();
	}

	@Bean
	public ShiroEventBusBeanPostProcessor shiroEventBusAwareBeanPostProcessor(EventBus eventBus) {
		return new ShiroEventBusBeanPostProcessor(eventBus);
	}

	/**
	 * @see ShiroWebConfiguration
	 */
	static class ShiroWebConfig extends AbstractShiroWebConfiguration {
		@Bean
		@Override
		protected SubjectDAO subjectDAO() {
			return super.subjectDAO();
		}

		@Bean
		@Override
		protected SessionStorageEvaluator sessionStorageEvaluator() {
			return super.sessionStorageEvaluator();
		}

		@Bean
		@Override
		protected SessionFactory sessionFactory() {
			return super.sessionFactory();
		}

		@Bean
		@Override
		protected SessionDAO sessionDAO() {
			return super.sessionDAO();
		}

		@Bean(name = "sessionCookieTemplate")
		@Override
		protected Cookie sessionCookieTemplate() {
			return super.sessionCookieTemplate();
		}

		@Bean(name = "rememberMeCookieTemplate")
		@Override
		protected Cookie rememberMeCookieTemplate() {
			return super.rememberMeCookieTemplate();
		}

		@Bean
		@Override
		protected RememberMeManager rememberMeManager() {
			return super.rememberMeManager();
		}

		@Bean
		@Override
		protected SubjectFactory subjectFactory() {
			return super.subjectFactory();
		}

		@Bean
		@Override
		protected Authorizer authorizer() {
			return super.authorizer();
		}

		@Bean
		@Override
		protected AuthenticationStrategy authenticationStrategy() {
			return super.authenticationStrategy();
		}

		@Bean
		@Override
		protected Authenticator authenticator() {
			return super.authenticator();
		}

		/**
		 * it relates jsessionid cookie
		 */
		@Bean
		@Override
		protected SessionManager sessionManager() {
			// see parent method of it
			useNativeSessionManager=true;
			return super.sessionManager();
		}

		/**
		 * todo 虽然只自定义了一个Realm的bean 但是不知道为什么自动注入到realms中了
		 */
		@Bean
		@Override
		protected SessionsSecurityManager securityManager(List<Realm> realms) {
			return super.securityManager(realms);
		}

		@Bean
		@Override
		protected ShiroFilterChainDefinition shiroFilterChainDefinition() {
//			 其中会创建LinkedHashMap 但不知为何会报错 所以使用了HashMap
			return () -> {
				Map<String, String> chainMap = new HashMap<>();
				chainMap.put("/**", "authc");
				chainMap.put("/auth/login", "anon");
				return chainMap;
			};
		}

		@Bean
//		@Conditional()
		@Override
		protected ShiroUrlPathHelper shiroUrlPathHelper() {
			return super.shiroUrlPathHelper();
		}
	}
}
