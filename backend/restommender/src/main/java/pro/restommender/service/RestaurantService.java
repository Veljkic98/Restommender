package pro.restommender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.restommender.model.Restaurant;
import pro.restommender.repository.RestaurantRepository;

@Service
public class RestaurantService {
  
  @Autowired
  private RestaurantRepository restaurantRepository;

  public Restaurant add(Restaurant restaurant) {
    return restaurantRepository.save(restaurant);
  }
}
