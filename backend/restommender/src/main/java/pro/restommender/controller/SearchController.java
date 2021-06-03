package pro.restommender.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.restommender.dto.RestaurantDTO;
import pro.restommender.dto.Search;
import pro.restommender.mapper.RestaurantMapper;
import pro.restommender.model.Restaurant;
import pro.restommender.service.SearchService;

@RestController
@RequestMapping("api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @GetMapping
    public ResponseEntity<?> search(@RequestBody Search search) {


        try {
            List<Restaurant> restaurants = searchService.findRestaurants(search);

            List<RestaurantDTO> restaurantsDTO = restaurantMapper.toDtoList(restaurants);

            return new ResponseEntity<>(restaurantsDTO, HttpStatus.OK);
        } catch (Exception e) {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
