package pro.restommender;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.PostLoad;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import pro.restommender.dto.Search;
import pro.restommender.model.Message;
import pro.restommender.model.RelevantRestaurants;
import pro.restommender.model.Reservation;
import pro.restommender.model.Restaurant;
import pro.restommender.model.Rule;
import pro.restommender.model.User;
import pro.restommender.service.Testovi;

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
