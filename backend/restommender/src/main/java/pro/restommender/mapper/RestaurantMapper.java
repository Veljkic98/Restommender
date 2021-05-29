package pro.restommender.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pro.restommender.dto.RestaurantDTO;
import pro.restommender.model.Restaurant;

@Service
public class RestaurantMapper {
    
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
            dto.type = restaurant.getType();

            dtos.add(dto);
        }

        return dtos;


    }

}
