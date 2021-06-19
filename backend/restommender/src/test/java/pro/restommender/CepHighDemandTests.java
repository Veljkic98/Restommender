package pro.restommender;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import pro.restommender.event.ReservationEvent;
import pro.restommender.event.SearchEvent;
import pro.restommender.model.RelevantRestaurants;
import pro.restommender.model.Restaurant;
import pro.restommender.repository.RestaurantRepository;

@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class CepHighDemandTests {
  
  private KieContainer kContainer;

  @Autowired
  private RestaurantRepository restaurantRepository;

  @BeforeEach
  public void init() {
      KieServices ks = KieServices.Factory.get();
      this.kContainer = ks.newKieContainer(ks.newReleaseId("pro", "drools-kjar", "0.0.1-SNAPSHOT"));
  }

  @Test
  void add2Reservations_add2SearchesWithRestaurantName_returnsHighDemandRestaurant() {

      KieSession kieSession = kContainer.newKieSession("ksession-rules");

      List<Restaurant> restaurants = restaurantRepository.findAll();
      RelevantRestaurants rr = new RelevantRestaurants();
      rr.setRelevantRestaurants(restaurants);

      Restaurant restaurant = restaurants.get(0); // Petrus

      kieSession.getAgenda().getAgendaGroup("high-demand").setFocus();
      // prvi search
      kieSession.insert(new SearchEvent(new Date(), 1L, "Petrus"));
      // drugi search
      kieSession.insert(new SearchEvent(new Date(), 2L, "Petrus"));

      // prva rezervacija
      kieSession.insert(restaurant);
      kieSession.insert(rr);
      kieSession.insert(new ReservationEvent(new Date(), 1L, 1L, null, 0));

      // druga rezervacija
      kieSession.insert(new ReservationEvent(new Date(), 2L, 1L, null, 0));
      int num = kieSession.fireAllRules();

      kieSession.dispose();
      System.out.println("Fired rules: " + num);

      assertTrue(restaurant.getHighDemand());
      assertEquals(num, 1);
  }
}
