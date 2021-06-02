package pro.restommender.mapper;

import org.springframework.stereotype.Service;

import pro.restommender.dto.requestDTO.ReservationRequestDTO;
import pro.restommender.model.AuthenticatedUser;
import pro.restommender.model.Reservation;
import pro.restommender.model.Restaurant;

@Service
public class ReservationMapper {

    public Reservation toEntity(ReservationRequestDTO dto) {

        Reservation reservation = new Reservation();
        
        AuthenticatedUser user = new AuthenticatedUser();
        user.setId(dto.getUserId());
        
        reservation.setUser(user);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(dto.getRestaurantId());

        reservation.setRestaurant(restaurant);

        reservation.setNumOfPersons(dto.getNumOfPersons());
    
        return reservation;
    }
    
}
