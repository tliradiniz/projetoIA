package com.tdiniz.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdiniz.backend.models.Email;
import com.tdiniz.backend.security.services.EmailService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/email")
public class EmailController {

//	@Autowired
//	private EmailService emailService;
	
//	@PostMapping("/{id}")
//	public ResponseEntity<String> send(@RequestBody Email email, @PathVariable("id") long id) {
//		boolean success = emailService.produce(email);
//		if(success) {
//			return new ResponseEntity<String>("Enviado com sucesso.", HttpStatus.OK);				
//		} else {
//			return new ResponseEntity<String>("Não enviado.", HttpStatus.EXPECTATION_FAILED);
//		}
//	}
	
}
