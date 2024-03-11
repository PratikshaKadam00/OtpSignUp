package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.config.TwilioConfig;
import com.twilio.Twilio;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class UserModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserModuleApplication.class, args);
	}
	
	@Autowired
	private TwilioConfig twilioConfig;
	
	@PostConstruct
	public void initTwilio()
	{
		Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
		
	}

}
