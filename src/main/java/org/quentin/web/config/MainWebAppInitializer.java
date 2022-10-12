/**
 * @author:quentin
 * @create: 2022-09-30 17:42
 * @Description: init
 */
package org.quentin.web.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;

public class MainWebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(final ServletContext sc) {

        // 如果使用xml配置
//        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
//        appContext.setConfigLocation("/WEB-INF/spring/dispatcher-config.xml");

        AnnotationConfigWebApplicationContext root =
                new AnnotationConfigWebApplicationContext();

        // 配置方式不同
//        root.scan("org.quentin.web");
        root.register(WebMvcConfig.class);
        sc.addListener(new ContextLoaderListener(root));

        FilterRegistration.Dynamic shiroFilter = sc.addFilter("shiroFilterFactoryBean", DelegatingFilterProxy.class);
        shiroFilter.setInitParameter("targetFilterLifecycle", "true");
        shiroFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");

        ServletRegistration.Dynamic appServlet =
                sc.addServlet("mvc", new DispatcherServlet(new GenericWebApplicationContext()));
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");
        appServlet.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }
}