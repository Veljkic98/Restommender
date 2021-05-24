package pro.restommender;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import pro.restommender.model.Message;

@SpringBootApplication
@EnableScheduling
public class RestommenderApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(RestommenderApplication.class, args);
		Message message = new Message();
		message.setMessage("Hello World");
		message.setStatus(Message.GOODBYE);

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("pro", "drools-kjar", "0.0.1-SNAPSHOT"));
		KieSession kieSession = kContainer.newKieSession();
		kieSession.insert(message);
		Thread.sleep(1000);
		int num = kieSession.fireAllRules();
		Thread.sleep(1000);
		kieSession.dispose();

		System.out.println("----------------------");
		System.out.println("Fired rules: " + num);
	}

	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("pro", "drools-kjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10_000);
		return kContainer;
	}

	/**
	 * pokretanje:
	 * 1. cd backend/drools-kjar
	 * 2. ./mvnw  clean install
	 * 
	 * 1. cd backend/restommender
	 * 2. ./mvnw  clean package
	 * 3. run restommender
	 */

}
