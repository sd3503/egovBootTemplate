package egovframework.servicename.config;

import egovframework.servicename.interceptor.AllRequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AllRequestInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/js/**", "/css/**");
    }
}
