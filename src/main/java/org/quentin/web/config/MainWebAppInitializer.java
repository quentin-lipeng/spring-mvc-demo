package org.quentin.web.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * @author:quentin
 * @create: 2022-09-30 17:42
 * @Description: init
 */

public class MainWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * - 此处返回的配置类需要加@Configuration注解
     * 但目前只知道因为有循环依赖问题所以才需要此注解
     * - spring 配置 通过父类获取加载并注册
     * 这个相当于 applicationContext.xml
     *
     * @author quentin
     * @date 2022/10/25
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                MybatisConfig.class,
                SpringBeanConfig.class,
                ShiroConfig.class,
                FunctionalConfig.class,
                CacheConfig.class,
                AopConfig.class,
                WebMvcConfig.class,
        };
    }

    /**
     * If an application context hierarchy is not required,
     * applications can return all configuration through
     * getRootConfigClasses() and null from getServletConfigClasses().
     * spring mvc 配置 通过父类获取加载并注册
     * 这个等同于 dispatcher-servlet.xml
     * 因为只有一个DispatcherServlet 所以也可以把WebMvcConfig放在getRootConfigClasses()下
     * 参照 <a href="https://stackoverflow.com/questions/35258758/getservletconfigclasses-vs-getrootconfigclasses-when-extending-abstractannot">...</a>
     * 又因为shiroConfig中需要用到service类 所以需要弄到ComponentScan所以就把配置类放在一起
     * 后期可以把扫描注解和bean放在SpringBeanConfig中管理
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
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("utf-8");
        // TODO 此配置暂时不可用 会报错No bean named 'delegatingFilterProxy' available
        // 此方法不用添加对应的映射路径
        // 参照<i>https://www.baeldung.com/spring-delegating-filter-proxy</i>
//        DelegatingFilterProxy shiroFilter = new DelegatingFilterProxy();
//        shiroFilter.setTargetFilterLifecycle(true);
//        shiroFilter.setBeanName("shiroFilterFactoryBean");

        return new Filter[]{encodingFilter};
    }

    // 返回后通过AbstractDispatcherServletInitializer获取并加载
    @Override
    protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
        DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        dispatcherServlet.setNamespace("mvc");
        return dispatcherServlet;
    }

    // TODO: 2022/11/8 如果需要开启shiro的过滤 取消掉此方法内的注释
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
//        // 下面注册filter可以参照 AbstractDispatcherServletInitializer.registerServletFilter()
        FilterRegistration.Dynamic shiroFilter = servletContext.addFilter("shiroFilterFactoryBean", DelegatingFilterProxy.class);
        shiroFilter.setInitParameter("targetFilterLifecycle", "true");
        shiroFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
        super.onStartup(servletContext);
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // 开启日志 似乎和把log等级调到debug差不多
//        registration.setInitParameter("enableLoggingRequestDetails", "true");
    }
}