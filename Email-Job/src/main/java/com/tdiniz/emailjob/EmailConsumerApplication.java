package com.tdiniz.emailjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class EmailConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailConsumerApplication.class, args);
	}
}
