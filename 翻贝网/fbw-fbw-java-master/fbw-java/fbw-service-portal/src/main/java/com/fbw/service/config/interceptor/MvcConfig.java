package com.fbw.service.config.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * 
 * <一句话功能简述>
 * MVC配置
 * <功能详细描述>
 * 
 * @author  JACK HUANG
 * @version  [版本号, 2017年7月7日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter
{
   @Bean
   public CusInterceptor cusInterceptor(){
       return new CusInterceptor();
   }
   
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(cusInterceptor()); 
   }
   
}
