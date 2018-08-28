package com.yum.license;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;


@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ServletComponentScan
public class LicenseApplication {
	
	@SuppressWarnings("rawtypes")
	@Bean
	public HttpMessageConverter mappingJackson2HttpMessageConverter() {
	    FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
	    return fastJsonHttpMessageConverter;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LicenseApplication.class, args);
	}
}
