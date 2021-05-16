package pro.restommender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.restommender.model.Reservation;
import pro.restommender.service.ReservationService;

@RestController
@RequestMapping("reservations")
public class ReservationController {
  
  @Autowired
  ReservationService reservationService;

  @PostMapping
  public ResponseEntity<?> newReservation(@RequestBody Reservation reservation){ 
    try {
      reservationService.add(reservation);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
