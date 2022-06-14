package com.genspark.backend;

import com.genspark.backend.Service.TextServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		TextServiceImpl.sendSMS("8564057466","tett3");
		SpringApplication.run(BackendApplication.class, args);
	}

}
