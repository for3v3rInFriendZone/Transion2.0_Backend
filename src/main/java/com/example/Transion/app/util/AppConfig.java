package com.example.Transion.app.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean
	public CascadingMongoEventListener cascadingMongoEventListener() {
		return new CascadingMongoEventListener();
	}
}
