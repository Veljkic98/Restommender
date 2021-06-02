package pro.restommender.service;

import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.restommender.dto.Search;
import pro.restommender.dto.requestDTO.ReservationRequestDTO;
import pro.restommender.dto.responseDTO.ReservationResponseDTO;
import pro.restommender.mapper.ReservationMapper;
import pro.restommender.model.RelevantRestaurants;
import pro.restommender.model.Reservation;
import pro.restommender.model.Restaurant;
import pro.restommender.repository.ReservationRepository;
import pro.restommender.repository.RestaurantRepository;

@Service
public class ReservationService {

  @Autowired
  ReservationRepository reservationRepository;

  @Autowired
  private RestaurantRepository restaurantRepository;

  @Autowired
  private ReservationMapper reservationMapper;

  @Autowired
  private KieSession kieSession;

  public Reservation add(ReservationRequestDTO reservationRequestDTO) {

    // TODO: pozvati pravila


    Reservation reservation = reservationMapper.toEntity(reservationRequestDTO);

    RelevantRestaurants rr = new RelevantRestaurants(restaurantRepository.findAll());

    Search s = new Search();
    s.setRate(0.5);
    kieSession.getAgenda().getAgendaGroup("rate").setFocus();
		kieSession.insert(s);
		kieSession.insert(reservation);
		kieSession.insert(rr);
		int num = kieSession.fireAllRules();

		System.out.println("Fired rules: " + num);

    reservationRepository.save(reservation);

    return reservationRepository.save(reservation);
  }

  public List<ReservationResponseDTO> getAll() {

    List<Reservation> reservations = reservationRepository.findAll();

    List<ReservationResponseDTO> responseDto = reservationMapper.toDtoList(reservations);

    return responseDto;
  }
}
