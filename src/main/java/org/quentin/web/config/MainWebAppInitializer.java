package org.quentin.web.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.*;

/**
 * @author:quentin
 * @create: 2022-09-30 17:42
 *
 * @see WebApplicationInitializer
 */
public class MainWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * spring 配置 通过父类获取加载并注册
	 * 这个相当于 applicationContext.xml
	 * spring will load root config first then servlet
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {
				SpringBeanConfig.class,
				CacheConfig.class,
				AopConfig.class,
				MybatisConfig.class,
				FunctionalConfig.class,
				ShiroConfig.class,
		};
	}

	/**
	 * <p>If an application context hierarchy is not required,
	 * applications can return all configuration through
	 * getRootConfigClasses() and null from getServletConfigClasses().</p>
	 * spring mvc 配置 通过父类获取加载并注册
	 * <p>这个等同于 dispatcher-servlet.xml</p>
	 * 因为只有一个DispatcherServlet 所以也可以把WebMvcConfig放在getRootConfigClasses()下
	 * 参照 <a href="https://stackoverflow.com/questions/35258758/getservletconfigclasses-vs-getrootconfigclasses-when-extending-abstractannot">...</a>
	 *
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {
				WebMvcConfig.class,
		};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		// 此方法不用添加对应的映射路径
		// 参照<i>https://www.baeldung.com/spring-delegating-filter-proxy</i>
		DelegatingFilterProxy shiroFilter = new DelegatingFilterProxy();
		shiroFilter.setTargetBeanName("shiroFilterFactoryBean");
		shiroFilter.setTargetFilterLifecycle(true);
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("utf-8");
		return new Filter[] {
				encodingFilter,
				shiroFilter
		};
	}

	/**
	 * 返回后通过AbstractDispatcherServletInitializer获取并加载
	 */
	@Override
	protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
		DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		dispatcherServlet.setNamespace("mvc");
		return dispatcherServlet;
	}

	/**
	 * check {@link AbstractDispatcherServletInitializer#registerServletFilter(ServletContext, Filter)}
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		// 开启日志 似乎和把log等级调到debug差不多
        registration.setInitParameter("enableLoggingRequestDetails", "true");
	}
}