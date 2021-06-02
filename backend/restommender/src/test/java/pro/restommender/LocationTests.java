package pro.restommender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import pro.restommender.dto.Search;
import pro.restommender.model.RelevantRestaurants;
import pro.restommender.model.Restaurant;
import pro.restommender.repository.RestaurantRepository;

@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class LocationTests {

	private KieContainer kContainer;

	// @MockBean
	@Autowired
	private RestaurantRepository restaurantRepository;

	@BeforeEach
	public void init() {
		KieServices ks = KieServices.Factory.get();
		this.kContainer = ks.newKieContainer(ks.newReleaseId("pro", "drools-kjar", "0.0.1-SNAPSHOT"));
	}

	/*
	 * Pozivamo pravila za lokaciju.
	 * 
	 * Trazimo sve lokacije koje se nalaze u centru grada (< 1).
	 */
	@Test
	void searchLocation_inCenter_true() {

		KieSession kieSession = kContainer.newKieSession("ksession-rules");

		Search s = new Search();
		s.setLocation(0.6);

		List<Restaurant> restaurants = restaurantRepository.findAll();

		RelevantRestaurants rr = new RelevantRestaurants();
		rr.setRelevantRestaurants(restaurants);

		kieSession.getAgenda().getAgendaGroup("location").setFocus();
		kieSession.insert(rr);
		kieSession.insert(s);
		int num = kieSession.fireAllRules();

		kieSession.dispose();

		System.out.println("Fired rules: " + num);
		System.out.println(rr.getRelevantRestaurants().size());

		assertTrue(rr.getRelevantRestaurants().size() == 1);
		assertEquals(num, 1);
	}

	@Test
	void searchLocation_nearCenter_true() {

		KieSession kieSession = kContainer.newKieSession("ksession-rules");

		Search s = new Search();
		s.setLocation(1.6);

		List<Restaurant> restaurants = restaurantRepository.findAll();

		RelevantRestaurants rr = new RelevantRestaurants();
		rr.setRelevantRestaurants(restaurants);

		kieSession.getAgenda().getAgendaGroup("location").setFocus();
		kieSession.insert(rr);
		kieSession.insert(s);
		int num = kieSession.fireAllRules();

		kieSession.dispose();

		System.out.println("Fired rules: " + num);
		System.out.println(rr.getRelevantRestaurants().size());

		assertTrue(rr.getRelevantRestaurants().size() == 1);
		assertEquals(num, 1);
	}

	@Test
	void searchLocation_farAwayFromCenter_true() {

		KieSession kieSession = kContainer.newKieSession("ksession-rules");

		Search s = new Search();
		s.setLocation(7);

		List<Restaurant> restaurants = restaurantRepository.findAll();

		RelevantRestaurants rr = new RelevantRestaurants();
		rr.setRelevantRestaurants(restaurants);

		kieSession.getAgenda().getAgendaGroup("location").setFocus();
		kieSession.insert(rr);
		kieSession.insert(s);
		int num = kieSession.fireAllRules();

		kieSession.dispose();

		System.out.println("Fired rules: " + num);
		System.out.println(rr.getRelevantRestaurants().size());

		assertTrue(rr.getRelevantRestaurants().size() == 1);
		assertEquals(num, 1);
	}

	@Test
	void searchLocation_notInCity_true() {

		KieSession kieSession = kContainer.newKieSession("ksession-rules");

		Search s = new Search();
		s.setLocation(20);

		List<Restaurant> restaurants = restaurantRepository.findAll();

		RelevantRestaurants rr = new RelevantRestaurants();
		rr.setRelevantRestaurants(restaurants);

		kieSession.getAgenda().getAgendaGroup("location").setFocus();
		kieSession.insert(rr);
		kieSession.insert(s);
		int num = kieSession.fireAllRules();

		kieSession.dispose();

		System.out.println("Fired rules: " + num);
		System.out.println(rr.getRelevantRestaurants().size());

		assertTrue(rr.getRelevantRestaurants().size() == 1);
		assertEquals(num, 1);
	}

	@Test
	void searchLocation_location0_true() {

		KieSession kieSession = kContainer.newKieSession("ksession-rules");

		Search s = new Search();
		s.setLocation(0);

		List<Restaurant> restaurants = restaurantRepository.findAll();

		RelevantRestaurants rr = new RelevantRestaurants();
		rr.setRelevantRestaurants(restaurants);

		kieSession.getAgenda().getAgendaGroup("location").setFocus();
		kieSession.insert(rr);
		kieSession.insert(s);
		int num = kieSession.fireAllRules();

		kieSession.dispose();

		System.out.println("Fired rules: " + num);
		System.out.println(rr.getRelevantRestaurants().size());

		assertTrue(rr.getRelevantRestaurants().size() == 1);
		assertEquals(num, 1);
	}

	@Test
	void searchLocation_location1_true() {

		KieSession kieSession = kContainer.newKieSession("ksession-rules");

		Search s = new Search();
		s.setLocation(1);

		List<Restaurant> restaurants = restaurantRepository.findAll();

		RelevantRestaurants rr = new RelevantRestaurants();
		rr.setRelevantRestaurants(restaurants);

		kieSession.getAgenda().getAgendaGroup("location").setFocus();
		kieSession.insert(rr);
		kieSession.insert(s);
		int num = kieSession.fireAllRules();

		kieSession.dispose();

		System.out.println("Fired rules: " + num);
		System.out.println(rr.getRelevantRestaurants().size());

		assertTrue(rr.getRelevantRestaurants().size() == 1);
		assertEquals(num, 1);
	}

	@Test
	void searchLocation_location5_true() {

		KieSession kieSession = kContainer.newKieSession("ksession-rules");

		Search s = new Search();
		s.setLocation(5);

		List<Restaurant> restaurants = restaurantRepository.findAll();

		RelevantRestaurants rr = new RelevantRestaurants();
		rr.setRelevantRestaurants(restaurants);

		kieSession.getAgenda().getAgendaGroup("location").setFocus();
		kieSession.insert(rr);
		kieSession.insert(s);
		int num = kieSession.fireAllRules();

		kieSession.dispose();

		System.out.println("Fired rules: " + num);
		System.out.println(rr.getRelevantRestaurants().size());

		assertTrue(rr.getRelevantRestaurants().size() == 1);
		assertEquals(num, 1);
	}

}
