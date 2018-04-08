package com.example.Transion.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Transion.app.util.CascadingMongoEventListener;

@SpringBootApplication
public class TransionApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransionApplication.class, args);
	}
}
