package pro.restommender;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import pro.restommender.dto.Search;
import pro.restommender.event.ReservationEvent;
import pro.restommender.model.AuthenticatedUser;
import pro.restommender.model.RelevantRestaurants;
import pro.restommender.model.Reservation;
import pro.restommender.model.Restaurant;
import pro.restommender.repository.AuthenticatedUserRepository;
import pro.restommender.repository.ReservationRepository;
import pro.restommender.repository.RestaurantRepository;

@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class CepReservationTest {

    private KieContainer kContainer;

    @Autowired
    private AuthenticatedUserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    public void init() {
        KieServices ks = KieServices.Factory.get();
        this.kContainer = ks.newKieContainer(ks.newReleaseId("pro", "drools-kjar", "0.0.1-SNAPSHOT"));
    }

    @Test
    void addReservation_userUnblocked6Times_blockUser() {

        Long userId = 2L;

        AuthenticatedUser user = userRepository.findById(userId).orElse(null);

        KieSession kieSession = kContainer.newKieSession("ksession-rules");

        Reservation res = reservationRepository.findById(1L).orElse(null);
        res.setNumOfPersons(5);
        res.setId(null);

        Search s = new Search();
        s.setRate(5);

        List<Restaurant> restaurants = restaurantRepository.findAll();

        RelevantRestaurants rr = new RelevantRestaurants();
        rr.setRelevantRestaurants(restaurants);

        ReservationEvent re1 = new ReservationEvent(new Date(), userId);
        ReservationEvent re2 = new ReservationEvent(new Date(), userId);
        ReservationEvent re3 = new ReservationEvent(new Date(), userId);
        ReservationEvent re4 = new ReservationEvent(new Date(), userId);
        ReservationEvent re5 = new ReservationEvent(new Date(), userId);
        ReservationEvent re6 = new ReservationEvent(new Date(), userId);

        kieSession.getAgenda().getAgendaGroup("rate").setFocus();
        kieSession.insert(rr);
        kieSession.insert(s);
        kieSession.insert(user);
        kieSession.insert(res);

        kieSession.insert(re1);
        kieSession.insert(re2);
        kieSession.insert(re3);
        kieSession.insert(re4);
        kieSession.insert(re5);
        kieSession.insert(re6);
        // int num = kieSession.fireAllRules();
        kieSession.fireAllRules();

        kieSession.dispose();

        assertEquals(user.getBlocked(), true);
    }

    @Test
    void addReservation_userUnblocked5Times_blockUser() {

        Long userId = 2L;

        AuthenticatedUser user = userRepository.findById(userId).orElse(null);

        KieSession kieSession = kContainer.newKieSession("ksession-rules");

        Reservation res = reservationRepository.findById(1L).orElse(null);
        res.setNumOfPersons(5);
        res.setId(null);

        Search s = new Search();
        s.setRate(5);

        List<Restaurant> restaurants = restaurantRepository.findAll();

        RelevantRestaurants rr = new RelevantRestaurants();
        rr.setRelevantRestaurants(restaurants);

        ReservationEvent re1 = new ReservationEvent(new Date(), userId);
        ReservationEvent re2 = new ReservationEvent(new Date(), userId);
        ReservationEvent re3 = new ReservationEvent(new Date(), userId);
        ReservationEvent re4 = new ReservationEvent(new Date(), userId);
        ReservationEvent re5 = new ReservationEvent(new Date(), userId);

        kieSession.getAgenda().getAgendaGroup("rate").setFocus();
        kieSession.insert(rr);
        kieSession.insert(s);
        kieSession.insert(user);
        kieSession.insert(res);

        kieSession.insert(re1);
        kieSession.insert(re2);
        kieSession.insert(re3);
        kieSession.insert(re4);
        kieSession.insert(re5);
        kieSession.fireAllRules();

        kieSession.dispose();

        assertEquals(user.getBlocked(), false);
    }

    @Test
    void addReservation_userUnblocked2Times_noBlockUser() {

        Long userId = 2L;

        AuthenticatedUser user = userRepository.findById(userId).orElse(null);

        KieSession kieSession = kContainer.newKieSession("ksession-rules");

        Reservation res = reservationRepository.findById(1L).orElse(null);
        res.setNumOfPersons(5);
        res.setId(null);

        Search s = new Search();
        s.setRate(5);

        List<Restaurant> restaurants = restaurantRepository.findAll();

        RelevantRestaurants rr = new RelevantRestaurants();
        rr.setRelevantRestaurants(restaurants);

        ReservationEvent re1 = new ReservationEvent(new Date(), userId);
        ReservationEvent re2 = new ReservationEvent(new Date(), userId);

        kieSession.getAgenda().getAgendaGroup("rate").setFocus();
        kieSession.insert(rr);
        kieSession.insert(s);
        kieSession.insert(user);
        kieSession.insert(res);

        kieSession.insert(re1);
        kieSession.insert(re2);
        kieSession.fireAllRules();

        kieSession.dispose();

        assertEquals(user.getBlocked(), false);
    }

}
