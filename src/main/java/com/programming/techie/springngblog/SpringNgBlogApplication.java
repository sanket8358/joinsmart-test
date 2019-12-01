package com.programming.techie.springngblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
public class SpringNgBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringNgBlogApplication.class, args);
	}

}