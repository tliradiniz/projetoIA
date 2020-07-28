package com.tdiniz.emailjob.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdiniz.emailjob.repository.UserRepository;
import com.tdiniz.emailjob.model.Email;


public class EmailConsumerService {
	
	@Autowired
	UserRepository userRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailConsumerService.class);

	@RabbitListener(containerFactory="rabbitListenerContainerFactory", queues = "${queue.email}")
	public void consumeEmailQueue(String message) {
		try {		
			Email email = new ObjectMapper().readValue(message, Email.class);
			for (String username : userRepository.queryUsers(email.getSenderId())) {
				LOGGER.info("Enviando mensagem : '"+email.getMessage()+"' para o Usuário: "+username);
			}			
		} catch (Exception e) {
			LOGGER.error("Envio de Email falhou. "+e.getMessage(), e);
		}
	}
}
