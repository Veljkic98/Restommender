package pro.restommender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import pro.restommender.dto.Search;
import pro.restommender.model.RelevantRestaurants;
import pro.restommender.model.Restaurant;
import pro.restommender.repository.RestaurantRepository;

@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class MultipleInvocationTests {
  private KieContainer kContainer;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@BeforeEach
	public void init() {
		KieServices ks = KieServices.Factory.get();
		this.kContainer = ks.newKieContainer(ks.newReleaseId("pro", "drools-kjar", "0.0.1-SNAPSHOT"));
	}

	@Test
	void searchLocation_inCenter_true_MultipleInvocation() {

		KieSession kieSession = kContainer.newKieSession("ksession-rules");

    // first invocation
		Search s = new Search();
		s.setLocation(0.6);
		List<Restaurant> restaurants = restaurantRepository.findAll();
		RelevantRestaurants rr = new RelevantRestaurants();
		rr.setRelevantRestaurants(restaurants);

		kieSession.getAgenda().getAgendaGroup("location").setFocus();
		FactHandle rrfc = kieSession.insert(rr);
		FactHandle sfc = kieSession.insert(s);
		int num = kieSession.fireAllRules();

    // ovo je neophodno kako se pravila ne bi izvrsila vise puta
    kieSession.delete(rrfc);
    kieSession.delete(sfc);

		System.out.println("Fired rules: " + num);
		System.out.println(rr.getRelevantRestaurants().size());

		assertTrue(rr.getRelevantRestaurants().size() == 1);
		assertEquals(num, 1);

    // second invocation
    Search s2 = new Search();
		s2.setLocation(0.6);
		List<Restaurant> restaurants2 = restaurantRepository.findAll();
		RelevantRestaurants rr2 = new RelevantRestaurants();
		rr2.setRelevantRestaurants(restaurants2);

		kieSession.getAgenda().getAgendaGroup("location").setFocus();
		FactHandle rr2fc = kieSession.insert(rr2);
		FactHandle s2fc = kieSession.insert(s2);
		int num2 = kieSession.fireAllRules();
    // ovo je neophodno kako se pravila ne bi izvrsila vise puta
    kieSession.delete(rr2fc);
    kieSession.delete(s2fc);

		System.out.println("Fired rules: " + num2);
		System.out.println(rr2.getRelevantRestaurants().size());

		assertEquals(1, rr2.getRelevantRestaurants().size());
		assertEquals(1, num2);
	}
}
