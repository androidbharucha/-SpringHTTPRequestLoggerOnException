package org.tiaa.chaos.chaostest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.tiaa.spring.common.web.error.replay" , "org.tiaa.chaos.chaostest"})
public class ChaostestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChaostestApplication.class, args);
	}

}
