package pro.restommender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ConfigurationPropertiesScan
public class RestommenderApplication {

	/*
	 * pokretanje:
	 * 1. cd backend/drools-kjar
	 * 2. ./mvnw  clean install
	 * 
	 * 1. cd backend/restommender
	 * 2. ./mvnw  clean package
	 * 3. run restommender
	 */

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(RestommenderApplication.class, args);
	}

}
