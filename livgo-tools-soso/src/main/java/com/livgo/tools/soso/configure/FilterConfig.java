package com.livgo.tools.soso.configure;

import com.livgo.core.filter.CharacterFilter;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Description:过滤器配置
 * Author:     Livgo
 * Date:       2017/11/15
 * Verion:     V1.0.0
 * Update:     更新说明
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filterCharacterBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new CharacterFilter());
        registrationBean.addUrlPatterns("/*");
//        registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
//        registrationBean.addInitParameter("confReloadCheckInterval", "5");
//        registrationBean.addInitParameter("logLevel", "DEBUG");
        return registrationBean;
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Bean
    public ServletRegistrationBean dispatcherServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet(), "/*");
        registration.addUrlMappings("/**");
        registration.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
        return registration;
    }

}
