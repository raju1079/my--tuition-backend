package com.snipe.myTuitionCenter.admin.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpConfig {
	
	@Bean
	RestTemplate restTemplate(RestTemplateBuilder builder) {
	 return builder
	  .setConnectTimeout(Duration.ofMinutes(1))
	  .setReadTimeout(Duration.ofMinutes(5))
	  .build();
	}

}
