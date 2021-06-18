package pro.restommender.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.restommender.dto.responseDTO.RestaurantResponseDTO;
import pro.restommender.mapper.RestaurantMapper;
import pro.restommender.model.Restaurant;
import pro.restommender.repository.RestaurantRepository;

@Service
public class RestaurantService {

  @Autowired
  private RestaurantRepository restaurantRepository;

  @Autowired
  private RestaurantMapper restaurantMapper;

  public Restaurant add(Restaurant restaurant) {

    return restaurantRepository.save(restaurant);
  }

  public List<RestaurantResponseDTO> getAll() {

    List<Restaurant> restaurants = restaurantRepository.findAll();

    List<RestaurantResponseDTO> dtos = restaurantMapper.toDtoList2(restaurants);

    return dtos;
  }
}
