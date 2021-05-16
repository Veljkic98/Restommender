package pro.restommender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.restommender.model.Reservation;
import pro.restommender.repository.ReservationRepository;

@Service
public class ReservationService {
  
  @Autowired
  ReservationRepository reservationRepository;

  public Reservation add(Reservation reservation) {
    return reservationRepository.save(reservation);
  }
}
