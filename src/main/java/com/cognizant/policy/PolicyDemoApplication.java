package com.cognizant.policy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages="com.cognizant.policy, com.cognizant.policy.repository, com.cognizant.policy.model")
@ComponentScan({"com.cognizant.policy.repository" , "com.cognizant.policy.model" , "com.cognizant.policy.controller"})
@EntityScan("com.cognizant.model.*")
@EnableJpaRepositories("com.cognizant.policy.repository")

public class PolicyDemoApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(PolicyDemoApplication.class, args);
	}
}