package com.camel.simplecamelspringboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;


@SpringBootApplication(exclude = { CacheAutoConfiguration.class})
@EnableAutoConfiguration
public class SimpleCamelSpringbootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SimpleCamelSpringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
