package com.gleisser.social.user.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SocialUserController {

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
}
