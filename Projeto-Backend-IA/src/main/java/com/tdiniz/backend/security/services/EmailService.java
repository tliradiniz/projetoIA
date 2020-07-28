package com.tdiniz.backend.security.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tdiniz.backend.models.Email;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EmailService {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private Queue emailQueue;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

	public boolean produce(Email email) {
		try {
			String json = new ObjectMapper().writeValueAsString(email);
			rabbitTemplate.convertAndSend(emailQueue.getName(), json);
			return true;
		} catch (JsonProcessingException e) {
			LOGGER.error("Erro na geração do email. "+e.getMessage(), e);
			return false;
		}
	}
}
