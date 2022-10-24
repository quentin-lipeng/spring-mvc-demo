/**
 * @author:quentin
 * @create: 2022-09-30 17:42
 * @Description: init
 */
package org.quentin.web.config;

import org.apache.shiro.spring.config.ShiroAnnotationProcessorConfiguration;
import org.apache.shiro.spring.web.config.ShiroRequestMappingConfig;
import org.apache.shiro.spring.web.config.ShiroWebConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.util.EnumSet;

//public class MainWebAppInitializer implements WebApplicationInitializer {
//    @Override
//    public void onStartup(final ServletContext sc) {
//
//        // 如果使用xml配置
////        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
////        appContext.setConfigLocation("/WEB-INF/spring/dispatcher-config.xml");
//
//        AnnotationConfigWebApplicationContext root =
//                new AnnotationConfigWebApplicationContext();
//
//        // 配置方式不同
////        root.scan("org.quentin.web");
//        root.register(WebMvcConfig.class);
//        sc.addListener(new ContextLoaderListener(root));
//
//        FilterRegistration.Dynamic shiroFilter = sc.addFilter("shiroFilterFactoryBean", DelegatingFilterProxy.class);
//        shiroFilter.setInitParameter("targetFilterLifecycle", "true");
//        shiroFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
//
//        ServletRegistration.Dynamic appServlet =
//                sc.addServlet("mvc", new DispatcherServlet(new GenericWebApplicationContext()));
//        appServlet.setLoadOnStartup(1);
//        appServlet.addMapping("/");
//        appServlet.setInitParameter("throwExceptionIfNoHandlerFound", "true");
//    }
//}
public class MainWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // spring 配置 通过父类获取加载并注册
    // 这个相当于 applicationContext.xml
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                WebMvcConfig.class,
                MybatisConfig.class,
                SpringBeanConfig.class,
                ShiroConfig.class,
                ShiroAnnotationProcessorConfiguration.class,
                ShiroWebConfiguration.class,
                ShiroRequestMappingConfig.class,
                FunctionalConfig.class,
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
        return new Class<?>[0];
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
        DelegatingFilterProxy shiroFilter = new DelegatingFilterProxy();
        shiroFilter.setTargetFilterLifecycle(true);
        shiroFilter.setBeanName("shiroFilterFactoryBean");

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