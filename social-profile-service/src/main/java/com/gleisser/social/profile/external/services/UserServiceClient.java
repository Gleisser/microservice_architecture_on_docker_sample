package com.gleisser.social.profile.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name= "social-user-service")
public interface UserServiceClient {

	
	@GetMapping("/user/hello")
	String hello();
	
}
