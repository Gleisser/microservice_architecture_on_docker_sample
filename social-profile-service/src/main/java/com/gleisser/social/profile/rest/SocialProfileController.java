package com.gleisser.social.profile.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gleisser.social.profile.external.services.UserServiceClient;

@RestController
public class SocialProfileController {
	
	@Autowired
	private UserServiceClient serviceClient;

	@GetMapping("/hello")
	public String hello() {
		return "hello profile, Hello from User: " + serviceClient.hello();
	}
	
}
