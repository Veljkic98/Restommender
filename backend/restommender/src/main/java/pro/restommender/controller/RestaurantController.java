package pro.restommender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.restommender.model.Restaurant;
import pro.restommender.service.RestaurantService;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {

  @Autowired
  RestaurantService restaurantService;
  
  /**
  curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"username":"alek","email":"alek@gmail.com", "petFriendly": "true"}' \
  http://localhost:8080/restaurants
   */
  @PostMapping
  public ResponseEntity<?> add(@RequestBody Restaurant restaurant) {
    try {
      restaurantService.add(restaurant);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
