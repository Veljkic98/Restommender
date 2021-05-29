package pro.restommender.service;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.restommender.dto.Search;
import pro.restommender.model.RelevantRestaurants;
import pro.restommender.model.Reservation;
import pro.restommender.model.Restaurant;
import pro.restommender.repository.AuthenticatedUserRepository;
import pro.restommender.repository.ReservationRepository;
import pro.restommender.repository.RestaurantRepository;

@Service
public class SearchService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AuthenticatedUserRepository authenticatedUserRepository;

    @Autowired
    private KieSession kieSession;

    public List<Restaurant> findRestaurants(Search search) {

        System.out.println("****** SEARCH SERVICE ******");

        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<Reservation> reservations = reservationRepository.findAll();

        RelevantRestaurants relevantRestaurants = new RelevantRestaurants();
        relevantRestaurants.setRelevantRestaurants(restaurants);

		System.out.println(relevantRestaurants.getRelevantRestaurants().size());
        doRestourantsCategory(search, relevantRestaurants);
		System.out.println(relevantRestaurants.getRelevantRestaurants().size());




        

        return relevantRestaurants.getRelevantRestaurants();
    }

    private void doRestourantsCategory(Search search, RelevantRestaurants relevantRestaurants) {

        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
		kieSession.insert(relevantRestaurants);
		kieSession.insert(search);
		int num = kieSession.fireAllRules();

		System.out.println("Fired rules: " + num);

    }

}
