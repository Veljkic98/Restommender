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

import pro.restommender.dto.RestaurantDTO;
import pro.restommender.dto.Search;
import pro.restommender.mapper.RestaurantMapper;
import pro.restommender.model.AuthenticatedUser;
import pro.restommender.model.Restaurant;
import pro.restommender.model.User;
import pro.restommender.repository.AuthenticatedUserRepository;
import pro.restommender.service.SearchService;

@RestController
@RequestMapping("api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Autowired
    private AuthenticatedUserRepository userRepository;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_AUTH_USER')")
    public ResponseEntity<?> search(@RequestBody Search search) {

        try {
            // User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findById(search.getUserId()).orElse(null);

            System.out.println(user);

            if (user.getBlocked() && user.getType().name().equals("USER"))
                return new ResponseEntity<>("User is blocked.", HttpStatus.BAD_REQUEST);

            List<Restaurant> restaurants = searchService.findRestaurants(search);

            List<RestaurantDTO> restaurantsDTO = restaurantMapper.toDtoList(restaurants);

            return new ResponseEntity<>(restaurantsDTO, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
