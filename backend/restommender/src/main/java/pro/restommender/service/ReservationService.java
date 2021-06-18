package pro.restommender.service;

import java.util.Date;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.restommender.dto.Search;
import pro.restommender.dto.requestDTO.ReservationRequestDTO;
import pro.restommender.dto.responseDTO.ReservationResponseDTO;
import pro.restommender.event.ReservationEvent;
import pro.restommender.mapper.ReservationMapper;
import pro.restommender.model.AuthenticatedUser;
import pro.restommender.model.RelevantRestaurants;
import pro.restommender.model.Reservation;
import pro.restommender.repository.AuthenticatedUserRepository;
import pro.restommender.repository.ReservationRepository;
import pro.restommender.repository.RestaurantRepository;

@Service
public class ReservationService {

  @Autowired
  ReservationRepository reservationRepository;

  @Autowired
  private RestaurantRepository restaurantRepository;

  @Autowired
  private AuthenticatedUserRepository userRepository;

  @Autowired
  private ReservationMapper reservationMapper;

  @Autowired
  private KieSession kieSession;

  public Reservation add(ReservationRequestDTO reservationRequestDTO, double rate) {
    Long userId = reservationRequestDTO.getUserId();
    Reservation reservation = reservationMapper.toEntity(reservationRequestDTO);
    AuthenticatedUser user = userRepository.findById(userId).orElse(null);

    Search s = new Search();
    s.setRate(rate);
    s.setNumOfPersons(reservationRequestDTO.getNumOfPersons());

    // trigger rules
    doDiscountRules(s, reservation);
    doHighDemandRules(reservation, user);
    doRateRules(s, reservation, user);
    
    // save in db
    userRepository.save(user);
    restaurantRepository.save(reservation.getRestaurant());
    return reservationRepository.save(reservation);
  }

  public List<ReservationResponseDTO> getAll() {
    List<Reservation> reservations = reservationRepository.findAll();
    List<ReservationResponseDTO> responseDto = reservationMapper.toDtoList(reservations);
    return responseDto;
  }

  private void doRateRules(Search search, Reservation reservation, AuthenticatedUser user) {
    RelevantRestaurants rr = new RelevantRestaurants(restaurantRepository.findAll());

    kieSession.getAgenda().getAgendaGroup("rate").setFocus();
    FactHandle searchFc = kieSession.insert(search);
    FactHandle rFc = kieSession.insert(reservation);
    FactHandle rrFc = kieSession.insert(rr);
    FactHandle userFc = kieSession.insert(user);
    // kieSession.insert(new ReservationEvent(new Date(), user.getId(), reservation.getRestaurant().getId()));
    int num = kieSession.fireAllRules();

    kieSession.delete(searchFc);
    kieSession.delete(rFc);
    kieSession.delete(rrFc);
    kieSession.delete(userFc);

    System.out.println("Fired rules: " + num);
    System.out.println("User blocked: " + user.getBlocked());
  }

  private void doDiscountRules(Search search, Reservation reservation) {
    List<Reservation> reservations = reservationRepository.findAll();
    kieSession.getAgenda().getAgendaGroup("reservation-number-discount").setFocus();
    FactHandle rFc = kieSession.insert(reservation);
    FactHandle searchFc = kieSession.insert(search);
    int num = kieSession.fireAllRules();

    kieSession.delete(rFc);
    kieSession.delete(searchFc);

    System.out.println("Fired rules: " + num);
    System.out.println("Reservations list size is : " + reservations.size());
  }

  private void doHighDemandRules(Reservation reservation, AuthenticatedUser user) {
    RelevantRestaurants rr = new RelevantRestaurants(restaurantRepository.findAll());

    kieSession.getAgenda().getAgendaGroup("high-demand").setFocus();
    FactHandle restaurantFc = kieSession.insert(reservation.getRestaurant());
    FactHandle userFc = kieSession.insert(user);
    FactHandle rrFc = kieSession.insert(rr);
    kieSession.insert(new ReservationEvent(new Date(), user.getId(), reservation.getRestaurant().getId()));
    int num = kieSession.fireAllRules();

    kieSession.delete(restaurantFc);
    kieSession.delete(userFc);
    kieSession.delete(rrFc);
  }
}
