package pro.restommender.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.restommender.dto.requestDTO.ReservationRequestDTO;
import pro.restommender.dto.responseDTO.ReservationResponseDTO;
import pro.restommender.dto.responseDTO.RestaurantResponseDTO;
import pro.restommender.dto.responseDTO.UserResponseDTO;
import pro.restommender.model.AuthenticatedUser;
import pro.restommender.model.Reservation;
import pro.restommender.model.Restaurant;
import pro.restommender.repository.AuthenticatedUserRepository;
import pro.restommender.repository.RestaurantRepository;

@Service
public class ReservationMapper {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AuthenticatedUserRepository userRepository;

    @Autowired
    private RestaurantMapper restaurantMapper;

    public Reservation toEntity(ReservationRequestDTO dto) {

        Reservation reservation = new Reservation();

        AuthenticatedUser user = userRepository.findById(dto.getUserId()).orElse(null);
        reservation.setUser(user);

        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId()).orElse(null);
        reservation.setRestaurant(restaurant);

        reservation.setNumOfPersons(dto.getNumOfPersons());

        return reservation;
    }

    public List<ReservationResponseDTO> toDtoList(List<Reservation> reservations) {

        List<ReservationResponseDTO> dtoList = new ArrayList<>();

        for (Reservation res : reservations) {
            ReservationResponseDTO dto = new ReservationResponseDTO();
            dto.setDiscount(res.getDiscount());
            dto.setId(res.getId());
            dto.setNumOfPersons(res.getNumOfPersons());

            RestaurantResponseDTO resDto = restaurantMapper.toDto(res);

            dto.setRestaurant(resDto);

            UserResponseDTO userDto = toUserDtoFromEntity(res);

            dto.setUser(userDto);

            dtoList.add(dto);
        }

        return dtoList;
    }

    public UserResponseDTO toUserDtoFromEntity(Reservation res) {

        UserResponseDTO userDto = new UserResponseDTO();
        userDto.setEmail(res.getUser().getEmail());
        userDto.setFirstName(res.getUser().getFirstName());
        userDto.setId(res.getUser().getId());
        userDto.setLastName(res.getUser().getLastName());
        userDto.setPassword(res.getUser().getPassword());
        userDto.setType(res.getUser().getType());

        return userDto;
    }

    public ReservationResponseDTO toDto(Reservation res) {

        ReservationResponseDTO dto = new ReservationResponseDTO();
        dto.setDiscount(res.getDiscount());
        dto.setId(res.getId());
        dto.setNumOfPersons(res.getNumOfPersons());
        dto.setRestaurant(restaurantMapper.toDto(res));
        dto.setUser(toUserDtoFromEntity(res));

        return dto;
    }

}
