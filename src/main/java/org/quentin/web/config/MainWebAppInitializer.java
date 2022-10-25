
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

    // spring 配置 通过父类获取加载并注册
    // 这个相当于 applicationContext.xml
    // TODO 此配置后以下配置类也需要使用@Configuration 但不加也可以用 具体未知
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                MybatisConfig.class,
                SpringBeanConfig.class,
                ShiroConfig.class,
                FunctionalConfig.class,
                CacheConfig.class,
                WebMvcConfig.class,
        };
    }

    // spring mvc 配置 通过父类获取加载并注册
    // 这个等同于 dispatcher-servlet.xml
    // 因为只有一个DispatcherServlet 所以也可以把WebMvcConfig放在getRootConfigClasses()下
    // 参照 https://stackoverflow.com/questions/35258758/getservletconfigclasses-vs-getrootconfigclasses-when-extending-abstractannot
    // 又因为shiroConfig中需要用到service类 所以需要弄到ComponentScan所以就把配置类放在一起
    // 后期可以把扫描注解和bean放在SpringBeanConfig中管理
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

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
//        // 下面注册filter可以参照 AbstractDispatcherServletInitializer.registerServletFilter()
        FilterRegistration.Dynamic shiroFilter = servletContext.addFilter("shiroFilterFactoryBean", DelegatingFilterProxy.class);
        shiroFilter.setInitParameter("targetFilterLifecycle", "true");
        shiroFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
        super.onStartup(servletContext);
    }
}