package pro.restommender;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import pro.restommender.dto.Search;
import pro.restommender.model.Message;
import pro.restommender.model.RelevantRestaurants;
import pro.restommender.model.Reservation;
import pro.restommender.model.Restaurant;
import pro.restommender.model.Rule;
import pro.restommender.model.User;

@SpringBootApplication
@EnableScheduling
public class RestommenderApplication {

	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("pro", "drools-kjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10_000);
		return kContainer;
	}

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
		
		testReservationDiscount();
	}

	public static void testRestourantMusic() {
		// search obj
		Search s = new Search();
		s.setMusic("relaxing");
		s.setAccomodation("udobno");

		// restorani
		Restaurant r = new Restaurant();
		r.setId(1L);
		r.setMusic("relaxing");
		r.setAccomodation("udobno");
		Restaurant r2 = new Restaurant();
		r2.setId(2L);
		r2.setMusic("loud");
		r2.setAccomodation("tradicionalno");

		List<Restaurant> restaurants = new ArrayList<>();
		restaurants.add(r);
		restaurants.add(r2);
		RelevantRestaurants rr = new RelevantRestaurants();
		rr.setRelevantRestaurants(restaurants);

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("pro", "drools-kjar", "0.0.1-SNAPSHOT"));
		KieSession kieSession = kContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("music").setFocus();
		kieSession.insert(rr);
		kieSession.insert(s);
		int num = kieSession.fireAllRules();
		// kieSession.dispose();

		System.out.println("----------------------");
		System.out.println("Fired rules: " + num);
		System.out.println(rr.getRelevantRestaurants().size());



		kieSession.getAgenda().getAgendaGroup("accomodation").setFocus();
		kieSession.insert(rr);
		num = kieSession.fireAllRules();
		kieSession.dispose();

		System.out.println("----------------------");
		System.out.println("Fired rules: " + num);
		System.out.println(rr.getRelevantRestaurants().size());
	}

	public static void testReservationDiscount() {

		int numOfPersons = 5;
		User u = new User();
		Reservation r1 = new Reservation();
		r1.setUser(u);
		r1.setNumOfPersons(numOfPersons);
		// Reservation r2 = new Reservation();
		// r2.setUser(u);
		// Reservation r3 = new Reservation();
		// r3.setUser(u);

		List<Reservation> reservations = new ArrayList<>();
		reservations.add(r1);
		// reservations.add(r2);
		// reservations.add(r3);
		u.setReservations(reservations);

		Search s = new Search();
		s.setNumOfPersons(numOfPersons);

		Rule rule = new Rule();
		rule.setFact("");
		rule.setRule("");

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("pro", "drools-kjar", "0.0.1-SNAPSHOT"));
		KieSession kieSession = kContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("reservation-number-discount").setFocus();
		kieSession.insert(r1);
		kieSession.insert(s);
		kieSession.insert(rule);
		int num = kieSession.fireAllRules();
		kieSession.dispose();

		System.out.println("----------------------");
		System.out.println("Fired rules: " + num);
		System.out.println("new dicount: " + r1.getDiscount());
		System.out.println("Izvrseno pravilo: " + rule.getRule());
		System.out.println("Izvrsena cinjenica: " + rule.getFact());
	}


	public static void testLocation() {
		Restaurant r1 = new Restaurant();
		r1.setLocation(0.2);
		Restaurant r2 = new Restaurant();
		r2.setLocation(6.5);
		Restaurant r3 = new Restaurant();
		r3.setLocation(0.7);
		List<Restaurant> restaurants = new ArrayList<>();
		restaurants.add(r1);
		restaurants.add(r2);
		restaurants.add(r3);
		RelevantRestaurants rr = new RelevantRestaurants();
		rr.setRelevantRestaurants(restaurants);



		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("pro", "drools-kjar", "0.0.1-SNAPSHOT"));
		KieSession kieSession = kContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("location").setFocus();
		kieSession.insert(rr);
		int num = kieSession.fireAllRules();
		kieSession.dispose();

		System.out.println("----------------------");
		System.out.println("Fired rules: " + num);
		System.out.println(rr.getRelevantRestaurants());
	}

	public static void testMessage() throws Exception{
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

	/*
	 * pokretanje:
	 * 1. cd backend/drools-kjar
	 * 2. ./mvnw  clean install
	 * 
	 * 1. cd backend/restommender
	 * 2. ./mvnw  clean package
	 * 3. run restommender
	 */

}
