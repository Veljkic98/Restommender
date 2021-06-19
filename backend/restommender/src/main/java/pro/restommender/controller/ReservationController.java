package pro.restommender.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.restommender.dto.requestDTO.ReservationRequestDTO;
import pro.restommender.dto.responseDTO.ReservationResponseDTO;
import pro.restommender.mapper.ReservationMapper;
import pro.restommender.model.Reservation;
import pro.restommender.model.User;
import pro.restommender.service.ReservationService;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

  @Autowired
  ReservationService reservationService;

  @Autowired
  private ReservationMapper reservationMapper;

  @PostMapping(path = "/{rate}")
  @PreAuthorize("hasRole('ROLE_AUTH_USER')")
  public ResponseEntity<?> newReservation(@RequestBody ReservationRequestDTO reservationRequestDTO,
      @PathVariable double rate) {

    try {

      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      if (user.getBlocked() && user.getType().name().equals("USER"))
        return new ResponseEntity<>("User is blocked.", HttpStatus.BAD_REQUEST);

      Reservation res = reservationService.add(reservationRequestDTO, rate);

      ReservationResponseDTO response = reservationMapper.toDto(res);

      return new ResponseEntity<>(response, HttpStatus.CREATED);
    } catch (Exception e) {
      e.printStackTrace();

      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping
  @PreAuthorize("hasRole('ROLE_AUTH_USER')")
  public ResponseEntity<?> getAll(HttpServletRequest request) {

    try {
      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      if (user.getType().name().equals("USER"))
        return new ResponseEntity<>("User must be admin.", HttpStatus.UNAUTHORIZED);

      List<ReservationResponseDTO> dtos = reservationService.getAll();

      return new ResponseEntity<>(dtos, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
