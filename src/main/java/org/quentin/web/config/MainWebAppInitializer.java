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
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class MainWebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(final ServletContext sc) {

        AnnotationConfigWebApplicationContext root =
                new AnnotationConfigWebApplicationContext();

        root.scan("org.quentin.web");
        sc.addListener(new ContextLoaderListener(root));

        ServletRegistration.Dynamic appServlet =
                sc.addServlet("mvc", new DispatcherServlet(new GenericWebApplicationContext()));
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");
    }
}