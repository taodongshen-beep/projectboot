package com.example.projectboot.config;

import com.example.projectboot.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
    public class AxiosConfig implements WebMvcConfigurer {
       @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOriginPatterns("*")
                    .allowCredentials(true)
                    .allowedMethods("GET", "POST", "DELETE", "PUT")
                    .maxAge(3600);
        }


//        拦截器配置
//   @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//    registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");//拦截全部请求
//   }

//    静态处理

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")    // 虚拟路径
                // file: 表示以本地的路径方式去访问绝对路径。
                .addResourceLocations("file:D:\\img\\");    // 绝对路径
    }
}

