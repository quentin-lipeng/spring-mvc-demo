package org.quentin.web.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

/**
 * @author:quentin
 * @create: 2022-09-30 17:42
 * @Description: init
 */
public class MainWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * spring 配置 通过父类获取加载并注册
     * 这个相当于 applicationContext.xml
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                SpringBeanConfig.class,
                FunctionalConfig.class,
                CacheConfig.class,
                AopConfig.class,
                MybatisConfig.class,
                WebMvcConfig.class,
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
     * 又因为shiroConfig中需要用到service类 所以需要弄到ComponentScan所以就把配置类放在一起
     * 如果需要把mvc的层级分清楚 需要解决shiro的filterChain动态配置的问题（需要访问dao层获取过滤规则）
     *
     * @author quentin
     * @date 2022/10/25
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    // AbstractDispatcherServletInitializer 获取后注册
    @Override
    protected Filter[] getServletFilters() {
        // 此方法不用添加对应的映射路径
        // 参照<i>https://www.baeldung.com/spring-delegating-filter-proxy</i>
        DelegatingFilterProxy shiroFilter = new DelegatingFilterProxy("shiroFilterFactoryBean");
        shiroFilter.setTargetFilterLifecycle(true);
        return new Filter[]{new CharacterEncodingFilter("utf-8"), shiroFilter};
    }

    // 返回后通过AbstractDispatcherServletInitializer获取并加载
    @Override
    protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
        DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        dispatcherServlet.setNamespace("mvc");
        return dispatcherServlet;
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
//        // 下面注册filter可以参照 AbstractDispatcherServletInitializer.registerServletFilter()
//        FilterRegistration.Dynamic shiroFilter = servletContext.addFilter("shiroFilterFactoryBean", DelegatingFilterProxy.class);
//        shiroFilter.setInitParameter("targetFilterLifecycle", "true");
//        shiroFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
        super.onStartup(servletContext);
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // 开启日志 似乎和把log等级调到debug差不多
//        registration.setInitParameter("enableLoggingRequestDetails", "true");
    }
}