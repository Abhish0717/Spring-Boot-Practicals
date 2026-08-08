package com.rays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.rays.common.FrontCtl;

@SpringBootApplication
public class SimpleSpringApplication {

//	@Autowired
//	FrontCtl frontCtl;

	public static void main(String[] args) {

		SpringApplication.run(SimpleSpringApplication.class, args);

	}

	@Bean
	public WebMvcConfigurer webConfig() {

		return new WebMvcConfigurer() {

//			@Override
//			public void addInterceptors(InterceptorRegistry registry) {
//				registry.addInterceptor(frontCtl).addPathPatterns("/**").excludePathPatterns("/Auth/*");
//			}

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200");
			}

		};

	}
}