package pro.restommender;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
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
import pro.restommender.event.SearchEvent;
import pro.restommender.model.AuthenticatedUser;
import pro.restommender.model.RelevantRestaurants;
import pro.restommender.model.Restaurant;
import pro.restommender.repository.AuthenticatedUserRepository;
import pro.restommender.repository.RestaurantRepository;

@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class CepSearchTest {

    private KieContainer kContainer;

    @Autowired
    private AuthenticatedUserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    public void init() {
        KieServices ks = KieServices.Factory.get();
        this.kContainer = ks.newKieContainer(ks.newReleaseId("pro", "drools-kjar", "0.0.1-SNAPSHOT"));
    }

    @Test
    void searchLocation_userUnblocked6Searches_blockUser() {

        KieSession kieSession = kContainer.newKieSession("ksession-rules");
        Long userId = 2L;

        AuthenticatedUser user = userRepository.findById(userId).orElse(null);

        Search s = new Search();
        s.setLocation(0.6);
        s.setUserId(2L);

        List<Restaurant> restaurants = restaurantRepository.findAll();

        RelevantRestaurants rr = new RelevantRestaurants();
        rr.setRelevantRestaurants(restaurants);

        kieSession.getAgenda().getAgendaGroup("location").setFocus();
        kieSession.insert(rr);
        kieSession.insert(s);

        kieSession.insert(new SearchEvent(new Date(), userId));
        kieSession.insert(new SearchEvent(new Date(), userId));
        kieSession.insert(new SearchEvent(new Date(), userId));
        kieSession.insert(new SearchEvent(new Date(), userId));
        kieSession.insert(new SearchEvent(new Date(), userId));
        kieSession.insert(new SearchEvent(new Date(), userId));

        FactHandle handle = kieSession.insert(user);

        kieSession.fireAllRules();

        kieSession.delete(handle);

        kieSession.dispose();

        assertEquals(user.getBlocked(), true);
    }

    @Test
    void searchLocation_userUnblocked5Searches_noBlockUser() {

        KieSession kieSession = kContainer.newKieSession("ksession-rules");
        Long userId = 2L;

        AuthenticatedUser user = userRepository.findById(userId).orElse(null);

        Search s = new Search();
        s.setLocation(0.6);
        s.setUserId(2L);

        List<Restaurant> restaurants = restaurantRepository.findAll();

        RelevantRestaurants rr = new RelevantRestaurants();
        rr.setRelevantRestaurants(restaurants);

        kieSession.getAgenda().getAgendaGroup("location").setFocus();
        kieSession.insert(rr);
        kieSession.insert(s);

        kieSession.insert(new SearchEvent(new Date(), userId));
        kieSession.insert(new SearchEvent(new Date(), userId));
        kieSession.insert(new SearchEvent(new Date(), userId));
        kieSession.insert(new SearchEvent(new Date(), userId));
        kieSession.insert(new SearchEvent(new Date(), userId));

        FactHandle handle = kieSession.insert(user);

        kieSession.fireAllRules();

        kieSession.delete(handle);

        kieSession.dispose();

        assertEquals(user.getBlocked(), false);
    }

    @Test
    void searchLocation_userUnblocked2Searches_noBlockUser() {

        KieSession kieSession = kContainer.newKieSession("ksession-rules");
        Long userId = 2L;

        AuthenticatedUser user = userRepository.findById(userId).orElse(null);

        Search s = new Search();
        s.setLocation(0.6);
        s.setUserId(2L);

        List<Restaurant> restaurants = restaurantRepository.findAll();

        RelevantRestaurants rr = new RelevantRestaurants();
        rr.setRelevantRestaurants(restaurants);

        kieSession.getAgenda().getAgendaGroup("location").setFocus();
        kieSession.insert(rr);
        kieSession.insert(s);

        kieSession.insert(new SearchEvent(new Date(), userId));
        kieSession.insert(new SearchEvent(new Date(), userId));

        FactHandle handle = kieSession.insert(user);

        kieSession.fireAllRules();

        kieSession.delete(handle);

        kieSession.dispose();

        assertEquals(user.getBlocked(), false);
    }
}
