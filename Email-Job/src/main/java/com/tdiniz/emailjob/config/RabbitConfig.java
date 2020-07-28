package com.tdiniz.emailjob.config;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tdiniz.emailjob.service.EmailConsumerService;

@Configuration
@EnableRabbit
public class RabbitConfig {

	@Value("${rabbitmq.host}")
	private String rabbitmqHost;
	
	@Value("${rabbitmq.port}")
	private int rabbitmqPort;
	
	@Value("${rabbitmq.username}")
	private String rabbitmqUsername;
	
	@Value("${rabbitmq.password}")
	private String rabbitmqPassword;
	
	@Value("${queue.email}")
	private String queueEmail;
	
	@Value("${rabbitmq.max-concurrent-consumers}")
	private int rabbitmqMaxQueue;
	
	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
		SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();
		containerFactory.setConnectionFactory(connectionFactory());
		containerFactory.setMaxConcurrentConsumers(rabbitmqMaxQueue);
		return containerFactory;
	}
	
	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(rabbitmqHost);
		connectionFactory.setPort(rabbitmqPort);
		connectionFactory.setUsername(rabbitmqUsername);
		connectionFactory.setPassword(rabbitmqPassword);
		return connectionFactory;
	}
	
	@Bean
	public EmailConsumerService emailConsumerService() {
		return new EmailConsumerService();
	}
}
