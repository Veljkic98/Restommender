package pro.restommender.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.restommender.dto.requestDTO.ReservationRequestDTO;
import pro.restommender.dto.responseDTO.ReservationResponseDTO;
import pro.restommender.mapper.ReservationMapper;
import pro.restommender.model.Reservation;
import pro.restommender.repository.ReservationRepository;
import pro.restommender.repository.RestaurantRepository;

@Service
public class ReservationService {

  @Autowired
  ReservationRepository reservationRepository;

  @Autowired
  private ReservationMapper reservationMapper;

  public Reservation add(ReservationRequestDTO reservationRequestDTO) {

    // TODO: pozvati pravila
    Reservation reservation = reservationMapper.toEntity(reservationRequestDTO);

    reservationRepository.save(reservation);

    return reservationRepository.save(reservation);
  }

  public List<ReservationResponseDTO> getAll() {

    List<Reservation> reservations = reservationRepository.findAll();

    List<ReservationResponseDTO> responseDto = reservationMapper.toDtoList(reservations);

    return responseDto;
  }
}
