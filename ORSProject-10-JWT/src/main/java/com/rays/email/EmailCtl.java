package com.rays.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "mail")
public class EmailCtl {

	@Autowired
	private EmailServiceInt emailService;

	@GetMapping("/send")
	public String sendMail() {

		EmailMessage msg = new EmailMessage();
		msg.setTo("abhibhawsar2015@gmail.com");
		msg.setSubject("Spring Boot Mail");
		msg.setMessage("Hello, Mail sent successfully!");

		emailService.sendMail(msg);

		return "Mail Sent Successfully";
	}
}