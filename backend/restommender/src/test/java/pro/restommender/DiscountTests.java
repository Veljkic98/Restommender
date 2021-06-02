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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import pro.restommender.dto.Search;
import pro.restommender.model.RelevantRestaurants;
import pro.restommender.model.Reservation;
import pro.restommender.model.Restaurant;
import pro.restommender.model.User;
import pro.restommender.repository.AuthenticatedUserRepository;
import pro.restommender.repository.ReservationRepository;
import pro.restommender.repository.RestaurantRepository;

@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class DiscountTests {

  private KieContainer kContainer;
  
  @Autowired
  private ReservationRepository reservationRepository;


  @BeforeEach
  public void init() {
    KieServices ks = KieServices.Factory.get();
    this.kContainer = ks.newKieContainer(ks.newReleaseId("pro", "drools-kjar", "0.0.1-SNAPSHOT"));
  }

  @Test
  void lessThen2Reservations_ShouldAdd5PercentDiscount() {
    KieSession kieSession = kContainer.newKieSession("ksession-rules");

    Reservation r = reservationRepository.findById(1L).orElse(null);

		kieSession.getAgenda().getAgendaGroup("reservation-number-discount").setFocus();
		kieSession.insert(r);
		int num = kieSession.fireAllRules();
    kieSession.dispose();

    assertEquals(r.getDiscount(), 5);
    assertEquals(num, 1);
  }

}
