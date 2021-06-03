package pro.restommender.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.restommender.dto.RestaurantDTO;
import pro.restommender.dto.responseDTO.ReservationResponseDTO;
import pro.restommender.dto.responseDTO.RestaurantResponseDTO;
import pro.restommender.model.Reservation;
import pro.restommender.model.Restaurant;

@Service
public class RestaurantMapper {

    @Autowired
    private ReservationMapper reservationMapper;
    
    public List<RestaurantDTO> toDtoList(List<Restaurant> restaurants) {

        List<RestaurantDTO> dtos = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            RestaurantDTO dto = new RestaurantDTO();
            dto.accomodation = restaurant.getAccomodation();
            dto.alcoholicDrinks = restaurant.getAlcoholicDrinks();
            dto.kidFriendly = restaurant.getKidFriendly();
            dto.location = restaurant.getLocation();
            dto.music = restaurant.getMusic();
            dto.name = restaurant.getName();
            dto.nonAlcoholicDrinks = restaurant.getNonAlcoholicDrinks();
            dto.nonSmokingArea = restaurant.getNonSmokingArea();
            dto.petFriendly = restaurant.getPetFriendly();
            dto.smokingArea = restaurant.getSmokingArea();

            dtos.add(dto);
        }

        return dtos;
    }

    public List<RestaurantResponseDTO> toDtoList2(List<Restaurant> restaurants) {

        List<RestaurantResponseDTO> dtos = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            RestaurantResponseDTO dto = new RestaurantResponseDTO();
            dto.id = restaurant.getId();
            dto.accomodation = restaurant.getAccomodation();
            dto.alcoholicDrinks = restaurant.getAlcoholicDrinks();
            dto.kidFriendly = restaurant.getKidFriendly();
            dto.location = restaurant.getLocation();
            dto.music = restaurant.getMusic();
            dto.name = restaurant.getName();
            dto.nonAlcoholicDrinks = restaurant.getNonAlcoholicDrinks();
            dto.nonSmokingArea = restaurant.getNonSmokingArea();
            dto.petFriendly = restaurant.getPetFriendly();
            dto.smokingArea = restaurant.getSmokingArea();

            List<ReservationResponseDTO> reservations = reservationMapper.toDtoList(restaurant.getReservations());
            dto.setReservations(reservations);

            dtos.add(dto);
        }

        return dtos;
    }

    public RestaurantResponseDTO toDto(Reservation res) {

        RestaurantResponseDTO resDto = new RestaurantResponseDTO();
        resDto.setAccomodation(res.getRestaurant().getAccomodation());
        resDto.setAlcoholicDrinks(res.getRestaurant().getAlcoholicDrinks());
        resDto.setId(res.getRestaurant().getId());
        resDto.setKidFriendly(res.getRestaurant().getKidFriendly());
        resDto.setLocation(res.getRestaurant().getLocation());
        resDto.setMusic(res.getRestaurant().getMusic());
        resDto.setName(res.getRestaurant().getName());
        resDto.setNonAlcoholicDrinks(res.getRestaurant().getNonAlcoholicDrinks());
        resDto.setNonSmokingArea(res.getRestaurant().getNonSmokingArea());
        resDto.setPetFriendly(res.getRestaurant().getPetFriendly());
        resDto.setRate(res.getRestaurant().getRate());
        resDto.setSmokingArea(res.getRestaurant().getSmokingArea());

        return resDto;
    }

}
