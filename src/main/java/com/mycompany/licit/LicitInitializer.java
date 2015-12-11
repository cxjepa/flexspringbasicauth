package com.mycompany.licit;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class LicitInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(LicitConfig.class);
        rootContext.register(SecurityConfig.class);
        sc.addListener(new ContextLoaderListener(rootContext));
        ServletRegistration.Dynamic registration = sc.addServlet("dispatcher", 
                new DispatcherServlet(rootContext));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
    
}
