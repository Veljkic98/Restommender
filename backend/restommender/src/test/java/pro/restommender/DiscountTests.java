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
import pro.restommender.model.AuthenticatedUser;
import pro.restommender.model.Reservation;
import pro.restommender.repository.ReservationRepository;

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

  // ------------------------Pravila prvog nivoa------------------------
  // -------------------------------------------------------------------
  @Test
  void lessThan2Reservations_ShouldAdd5PercentDiscount() {
    KieSession kieSession = kContainer.newKieSession("ksession-rules");

    Reservation r = reservationRepository.findById(1L).orElse(null);

    kieSession.getAgenda().getAgendaGroup("reservation-number-discount").setFocus();
    kieSession.insert(r);
    int num = kieSession.fireAllRules();
    kieSession.dispose();

    assertEquals(5, r.getDiscount());
    assertEquals(1, num);
  }

  @Test
  void between2And4Reservations_ShouldAdd10PercentDiscount() {
    KieSession kieSession = kContainer.newKieSession("ksession-rules");

    Reservation r = reservationRepository.findById(4L).orElse(null);

    kieSession.getAgenda().getAgendaGroup("reservation-number-discount").setFocus();
    kieSession.insert(r);
    int num = kieSession.fireAllRules();
    kieSession.dispose();

    assertEquals(10, r.getDiscount());
    assertEquals(1, num);
  }

  @Test
  void moreThan4Reservations_ShouldAdd15PercentDiscount() {
    KieSession kieSession = kContainer.newKieSession("ksession-rules");

    Reservation r = reservationRepository.findById(5L).orElse(null);

    kieSession.getAgenda().getAgendaGroup("reservation-number-discount").setFocus();
    kieSession.insert(r);
    int num = kieSession.fireAllRules();
    kieSession.dispose();

    assertEquals(15, r.getDiscount());
    assertEquals(1, num);
  }

  // ------------------------Pravila drugog nivoa------------------------
  // --------------------------------------------------------------------
  @Test
  void lessThan2ReservationsAndNumberOfPersons_ShouldAdd2PercentDiscount() {
    KieSession kieSession = kContainer.newKieSession("ksession-rules");

    Reservation r = reservationRepository.findById(1L).orElse(null);
    Search s = new Search();
    s.setNumOfPersons(4);

    kieSession.getAgenda().getAgendaGroup("reservation-number-discount").setFocus();
    kieSession.insert(r);
    kieSession.insert(s);
    int num = kieSession.fireAllRules();
    kieSession.dispose();

    assertEquals(5 + 2, r.getDiscount());
    assertEquals(2, num);
  }

  @Test
  void between2And4ReservationsAndNumberOfPersons_ShouldAdd3PercentDiscount() {
    KieSession kieSession = kContainer.newKieSession("ksession-rules");

    Reservation r = reservationRepository.findById(4L).orElse(null);
    Search s = new Search();
    s.setNumOfPersons(4);

    kieSession.getAgenda().getAgendaGroup("reservation-number-discount").setFocus();
    kieSession.insert(r);
    kieSession.insert(s);
    int num = kieSession.fireAllRules();
    kieSession.dispose();

    assertEquals(10 + 3, r.getDiscount());
    assertEquals(2, num);
  }

  @Test
  void moreThan4ReservationsAndNumberOfPersons_ShouldAdd4PercentDiscount() {
    KieSession kieSession = kContainer.newKieSession("ksession-rules");

    Reservation r = reservationRepository.findById(5L).orElse(null);
    Search s = new Search();
    s.setNumOfPersons(4);

    kieSession.getAgenda().getAgendaGroup("reservation-number-discount").setFocus();
    kieSession.insert(r);
    kieSession.insert(s);
    int num = kieSession.fireAllRules();
    kieSession.dispose();

    assertEquals(15 + 4, r.getDiscount());
    assertEquals(2, num);
  }

// ------------------------Pravila treceg nivoa------------------------
// --------------------------------------------------------------------
  @Test
  void between10And15ReservationsAndNumberOfPersons_ShouldAdd2PercentDiscount() {
    KieSession kieSession = kContainer.newKieSession("ksession-rules");
    // create dummy reservations and user
    AuthenticatedUser u = new AuthenticatedUser();

    List<Reservation> reservations = new ArrayList<>();
    for(int i = 0; i < 14; i++ ){
      Reservation r = new Reservation();
      r.setUser(u);
      reservations.add(r);
    }
    u.setReservations(reservations);

    Reservation r = reservations.get(0);
    
    Search s = new Search();
    s.setNumOfPersons(4);

    kieSession.getAgenda().getAgendaGroup("reservation-number-discount").setFocus();
    kieSession.insert(r);
    kieSession.insert(s);
    int num = kieSession.fireAllRules();
    kieSession.dispose();

    assertEquals(15 + 4 + 2, r.getDiscount());
    assertEquals(3, num);
  }

  @Test
  void between15And20ReservationsAndNumberOfPersons_ShouldAdd3PercentDiscount() {
    KieSession kieSession = kContainer.newKieSession("ksession-rules");
    // create dummy reservations and user
    AuthenticatedUser u = new AuthenticatedUser();

    List<Reservation> reservations = new ArrayList<>();
    for(int i = 0; i < 15; i++ ){
      Reservation r = new Reservation();
      r.setUser(u);
      reservations.add(r);
    }
    u.setReservations(reservations);

    Reservation r = reservations.get(0);
    
    Search s = new Search();
    s.setNumOfPersons(4);

    kieSession.getAgenda().getAgendaGroup("reservation-number-discount").setFocus();
    kieSession.insert(r);
    kieSession.insert(s);
    int num = kieSession.fireAllRules();
    kieSession.dispose();

    assertEquals(15 + 4 + 3, r.getDiscount());
    assertEquals(3, num);
  }

  @Test
  void moreThan20ReservationsAndNumberOfPersons_ShouldAdd4PercentDiscount() {
    KieSession kieSession = kContainer.newKieSession("ksession-rules");
    // create dummy reservations and user
    AuthenticatedUser u = new AuthenticatedUser();

    List<Reservation> reservations = new ArrayList<>();
    for(int i = 0; i < 20; i++ ){
      Reservation r = new Reservation();
      r.setUser(u);
      reservations.add(r);
    }
    u.setReservations(reservations);

    Reservation r = reservations.get(0);
    
    Search s = new Search();
    s.setNumOfPersons(4);

    kieSession.getAgenda().getAgendaGroup("reservation-number-discount").setFocus();
    kieSession.insert(r);
    kieSession.insert(s);
    int num = kieSession.fireAllRules();
    kieSession.dispose();

    assertEquals(15 + 4 + 4, r.getDiscount());
    assertEquals(3, num);
  }
}