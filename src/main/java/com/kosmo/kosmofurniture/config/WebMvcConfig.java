package com.kosmo.kosmofurniture.config;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//    정적파일들의 URL을 재설정해주는 메소드이다.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /* '/js/**'로 호출하는 자원은 '/static/js/' 폴더 아래에서 찾는다. */
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/").setCachePeriod(60 * 60 * 24 * 7);
        /* '/css/**'로 호출하는 자원은 '/static/css/' 폴더 아래에서 찾는다. */
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/").setCachePeriod(60 * 60 * 24 * 7);
        /* '/img/**'로 호출하는 자원은 '/static/img/' 폴더 아래에서 찾는다. */
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/").setCachePeriod(60 * 60 * 24 * 7);
        /* '/font/**'로 호출하는 자원은 '/static/font/' 폴더 아래에서 찾는다. */
        registry.addResourceHandler("/font/**").addResourceLocations("classpath:/static/font/").setCachePeriod(60 * 60 * 24 * 7);

        /* files/817718b05f04e528f79589b038eb5934.png 접속하면 파일볼수있게 URI주소를 추가한다 */
        registry.addResourceHandler("/files/**").addResourceLocations("file:files/");
    }

    /* XSS 필터추가설정 */
    @Bean
    public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean() {
        FilterRegistrationBean<XssEscapeServletFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new XssEscapeServletFilter());
        filterRegistration.setOrder(1);
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

}