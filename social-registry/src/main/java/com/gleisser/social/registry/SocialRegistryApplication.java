package com.gleisser.social.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SocialRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialRegistryApplication.class, args);
	}

}
