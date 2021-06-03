package pro.restommender;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
public class CategoryTests {
  private KieContainer kContainer;

  @Autowired
  private RestaurantRepository restaurantRepository;

  @BeforeEach
  public void init() {
    KieServices ks = KieServices.Factory.get();
    this.kContainer = ks.newKieContainer(ks.newReleaseId("pro", "drools-kjar", "0.0.1-SNAPSHOT"));
  }

  // ------------------------Pravila prvog nivoa------------------------
  // -------------------------------------------------------------------
  @Test
  void relaxingMusic() {
    KieSession kieSession = kContainer.newKieSession("ksession-rules");

    List<Restaurant> restaurants = restaurantRepository.findAll();
    RelevantRestaurants rr = new RelevantRestaurants();
		rr.setRelevantRestaurants(restaurants);
    Search search = new Search();
		search.setMusic("relaxing");

    kieSession.getAgenda().getAgendaGroup("filter").setFocus();
    kieSession.insert(rr);
    kieSession.insert(search);
    int num = kieSession.fireAllRules();
    kieSession.dispose();

    assertEquals(2, rr.getRelevantRestaurants().size());
    assertEquals(1, num);
  }

  @Test
  void loudMusic() {
    KieSession kieSession = kContainer.newKieSession("ksession-rules");

    List<Restaurant> restaurants = restaurantRepository.findAll();
    RelevantRestaurants rr = new RelevantRestaurants();
		rr.setRelevantRestaurants(restaurants);
    Search search = new Search();
		search.setMusic("loud");

    kieSession.getAgenda().getAgendaGroup("filter").setFocus();
    kieSession.insert(rr);
    kieSession.insert(search);
    int num = kieSession.fireAllRules();
    kieSession.dispose();

    assertEquals(1, rr.getRelevantRestaurants().size());
    assertEquals(1, num);
  }
  
  // ------------------------Pravila drugog nivoa------------------------
  // --------------------------------------------------------------------
  @Test
  void relaxingMusicAndAccomodationUdobno() {
    KieSession kieSession = kContainer.newKieSession("ksession-rules");

    List<Restaurant> restaurants = restaurantRepository.findAll();
    RelevantRestaurants rr = new RelevantRestaurants();
    rr.setRelevantRestaurants(restaurants);
    
    Search search = new Search();
    search.setMusic("relaxing");
    search.setAccomodation("udobno");

    kieSession.getAgenda().getAgendaGroup("filter").setFocus();
    kieSession.insert(rr);
    kieSession.insert(search);
    int num = kieSession.fireAllRules();
    kieSession.dispose();

    assertEquals(2, rr.getRelevantRestaurants().size());
    assertEquals(2, num);
  }

  @Test
  void loudMusicAndAccomodationTradicionalno() {
    KieSession kieSession = kContainer.newKieSession("ksession-rules");

    List<Restaurant> restaurants = restaurantRepository.findAll();
    RelevantRestaurants rr = new RelevantRestaurants();
		rr.setRelevantRestaurants(restaurants);
    Search search = new Search();
		search.setMusic("loud");
    search.setAccomodation("tradicionalno");

    kieSession.getAgenda().getAgendaGroup("filter").setFocus();
    kieSession.insert(rr);
    kieSession.insert(search);
    int num = kieSession.fireAllRules();
    kieSession.dispose();

    assertEquals(1, rr.getRelevantRestaurants().size());
    assertEquals(2, num);
  }
  
  // ------------------------Pravila treceg nivoa------------------------
  // --------------------------------------------------------------------
}

