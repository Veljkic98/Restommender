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
import pro.restommender.model.Restaurant;
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

    RelevantRestaurants rr = new RelevantRestaurants(restaurantRepository.findAll());

    AuthenticatedUser user = userRepository.findById(userId).orElse(null);

    Search s = new Search();
    s.setRate(rate);
    kieSession.getAgenda().getAgendaGroup("rate").setFocus();
    kieSession.insert(s);
    kieSession.insert(reservation);
    kieSession.insert(rr);
    kieSession.insert(user);

    kieSession.insert(new ReservationEvent(new Date(), userId));
    FactHandle handle = kieSession.insert(user);

    int num = kieSession.fireAllRules();

    kieSession.delete(handle);

    System.out.println("Fired rules: " + num);
    System.out.println("User blocked: " + user.getBlocked());

    reservationRepository.save(reservation);
    userRepository.save(user);

    return reservationRepository.save(reservation);
  }

  public List<ReservationResponseDTO> getAll() {

    List<Reservation> reservations = reservationRepository.findAll();

    List<ReservationResponseDTO> responseDto = reservationMapper.toDtoList(reservations);

    return responseDto;
  }
}
