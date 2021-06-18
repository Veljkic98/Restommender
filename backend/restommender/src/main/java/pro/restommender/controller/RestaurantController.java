package pro.restommender.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.restommender.dto.responseDTO.RestaurantResponseDTO;
import pro.restommender.model.Restaurant;
import pro.restommender.model.User;
import pro.restommender.service.RestaurantService;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {

  @Autowired
  RestaurantService restaurantService;
  
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

  @GetMapping
  @PreAuthorize("hasRole('ROLE_AUTH_USER')")
  public ResponseEntity<?> getAll() {
    
    try {
      List<RestaurantResponseDTO> restaurantsDto = restaurantService.getAll();
      
      return new ResponseEntity<>(restaurantsDto, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
