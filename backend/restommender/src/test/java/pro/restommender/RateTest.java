package pro.restommender;

import static org.junit.jupiter.api.Assertions.*;

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
import pro.restommender.model.Reservation;
import pro.restommender.model.Restaurant;
import pro.restommender.repository.AuthenticatedUserRepository;
import pro.restommender.repository.ReservationRepository;
import pro.restommender.repository.RestaurantRepository;

@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class RateTest {

    private KieContainer kContainer;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @BeforeEach
    public void init() {
        KieServices ks = KieServices.Factory.get();
        this.kContainer = ks.newKieContainer(ks.newReleaseId("pro", "drools-kjar", "0.0.1-SNAPSHOT"));
    }

    @Test
    void setDiscount_rateLT3_true() {

        KieSession kieSession = kContainer.newKieSession("ksession-rules");

        Reservation res = reservationRepository.findById(1L).orElse(null);
        res.setNumOfPersons(5);
        res.setId(null);

        Search s = new Search();
        s.setRate(1.0);

        List<Restaurant> restaurants = restaurantRepository.findAll();

        RelevantRestaurants rr = new RelevantRestaurants();
        rr.setRelevantRestaurants(restaurants);

        kieSession.getAgenda().getAgendaGroup("rate").setFocus();
        kieSession.insert(rr);
        kieSession.insert(s);
        kieSession.insert(res);
        int num = kieSession.fireAllRules();

        kieSession.dispose();

        System.out.println("Fired rules: " + num);
        System.out.println(rr.getRelevantRestaurants().size());

        assertTrue(res.getDiscount() == 0.4);
        assertEquals(num, 1);
    }

    @Test
    void setDiscount_rateGT3_true() {

        KieSession kieSession = kContainer.newKieSession("ksession-rules");

        Reservation res = reservationRepository.findById(1L).orElse(null);
        res.setNumOfPersons(5);
        res.setId(null);

        Search s = new Search();
        s.setRate(4.0);

        List<Restaurant> restaurants = restaurantRepository.findAll();

        RelevantRestaurants rr = new RelevantRestaurants();
        rr.setRelevantRestaurants(restaurants);

        kieSession.getAgenda().getAgendaGroup("rate").setFocus();
        kieSession.insert(rr);
        kieSession.insert(s);
        kieSession.insert(res);
        int num = kieSession.fireAllRules();

        kieSession.dispose();

        System.out.println("Fired rules: " + num);
        System.out.println(rr.getRelevantRestaurants().size());

        assertTrue(res.getDiscount() == 3.2);
        assertEquals(num, 1);
    }

    @Test
    void setDiscount_rate0_true() {

        KieSession kieSession = kContainer.newKieSession("ksession-rules");

        Reservation res = reservationRepository.findById(1L).orElse(null);
        res.setNumOfPersons(5);
        res.setId(null);

        Search s = new Search();
        s.setRate(0);

        List<Restaurant> restaurants = restaurantRepository.findAll();

        RelevantRestaurants rr = new RelevantRestaurants();
        rr.setRelevantRestaurants(restaurants);

        kieSession.getAgenda().getAgendaGroup("rate").setFocus();
        kieSession.insert(rr);
        kieSession.insert(s);
        kieSession.insert(res);
        int num = kieSession.fireAllRules();

        kieSession.dispose();

        System.out.println("Fired rules: " + num);
        System.out.println(rr.getRelevantRestaurants().size());

        assertTrue(res.getDiscount() == 0.4);
        assertEquals(num, 1);
    }

    @Test
    void setDiscount_rate5_true() {

        KieSession kieSession = kContainer.newKieSession("ksession-rules");

        Reservation res = reservationRepository.findById(1L).orElse(null);
        res.setNumOfPersons(5);
        res.setId(null);

        Search s = new Search();
        s.setRate(5);

        List<Restaurant> restaurants = restaurantRepository.findAll();

        RelevantRestaurants rr = new RelevantRestaurants();
        rr.setRelevantRestaurants(restaurants);

        kieSession.getAgenda().getAgendaGroup("rate").setFocus();
        kieSession.insert(rr);
        kieSession.insert(s);
        kieSession.insert(res);
        int num = kieSession.fireAllRules();

        kieSession.dispose();

        System.out.println("Fired rules: " + num);
        System.out.println(rr.getRelevantRestaurants().size());

        assertTrue(res.getDiscount() == 3.2);
        assertEquals(num, 1);
    }

}
