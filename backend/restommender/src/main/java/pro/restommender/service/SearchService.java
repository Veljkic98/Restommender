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

        // doFilter(search, relevantRestaurants);

        // doLocation(search, relevantRestaurants);

        // doRestourantMusic(search, relevantRestaurants);

        doDiscount(search, reservations);




        return relevantRestaurants.getRelevantRestaurants();
    }

    // TODO: ova pravila aktivirati samo kad se dodaju nove rezervacije
    private void doDiscount(Search search, List<Reservation> reservations) {

        System.out.println("****** SEARCH DISCOUNT ******");

        kieSession.getAgenda().getAgendaGroup("reservation-number-discount").setFocus();
		kieSession.insert(reservations.get(0));
		kieSession.insert(search);
		int num = kieSession.fireAllRules();

		System.out.println("Fired rules: " + num);
        System.out.println("Reservations list size is : " + reservations.size());
    }

    private void doLocation(Search search, RelevantRestaurants relevantRestaurants) {

        System.out.println("****** SEARCH LOCATION ******");

        kieSession.getAgenda().getAgendaGroup("location").setFocus();
		kieSession.insert(relevantRestaurants);
		kieSession.insert(search);
		int num = kieSession.fireAllRules();

		System.out.println("Fired rules: " + num);
        System.out.println("RR list size is : " + relevantRestaurants.getRelevantRestaurants().size());
    }

    /**
     * 
     * 
     * @param search
     * @param relevantRestaurants
     */
    private void doFilter(Search search, RelevantRestaurants relevantRestaurants) {

        System.out.println("****** SEARCH FILTER ******");

        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
		kieSession.insert(relevantRestaurants);
		kieSession.insert(search);
		int num = kieSession.fireAllRules();

		System.out.println("Fired rules: " + num);
        System.out.println("RR list size is : " + relevantRestaurants.getRelevantRestaurants().size());
    }

}
