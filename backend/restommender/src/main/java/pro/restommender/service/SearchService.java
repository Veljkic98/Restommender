package pro.restommender.service;

import java.util.Date;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.restommender.dto.Search;
import pro.restommender.event.SearchEvent;
import pro.restommender.model.AuthenticatedUser;
import pro.restommender.model.RelevantRestaurants;
import pro.restommender.model.Restaurant;
import pro.restommender.repository.AuthenticatedUserRepository;
import pro.restommender.repository.ReservationRepository;
import pro.restommender.repository.RestaurantRepository;

@Service
public class SearchService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AuthenticatedUserRepository authenticatedUserRepository;

    @Autowired
    private KieSession kieSession;

    public List<Restaurant> findRestaurants(Search search) {

        System.out.println("****** SEARCH SERVICE ******");

        List<Restaurant> restaurants = restaurantRepository.findAll();

        RelevantRestaurants relevantRestaurants = new RelevantRestaurants();
        relevantRestaurants.setRelevantRestaurants(restaurants);

        doFilter(search, relevantRestaurants);

        doLocation(search, relevantRestaurants);

        return relevantRestaurants.getRelevantRestaurants();
    }

    private void doLocation(Search search, RelevantRestaurants relevantRestaurants) {

        System.out.println("****** SEARCH LOCATION ******");

        AuthenticatedUser user = authenticatedUserRepository.findById(search.getUserId()).orElse(null);

        kieSession.getAgenda().getAgendaGroup("location").setFocus();
        FactHandle rrFc = kieSession.insert(relevantRestaurants);
        FactHandle userFc = kieSession.insert(user);
        FactHandle searchFc = kieSession.insert(search);
        kieSession.insert(new SearchEvent(new Date(), search.getUserId()));
        int num = kieSession.fireAllRules();

        kieSession.delete(rrFc);
        kieSession.delete(userFc);
        kieSession.delete(searchFc);

        authenticatedUserRepository.save(user);

        System.out.println("User blocked: " + user.getBlocked());
        System.out.println("Fired rules: " + num);
        System.out.println("RR list size is: " + relevantRestaurants.getRelevantRestaurants().size());
    }

    /**
     * 
     * @param search
     * @param relevantRestaurants
     */
    private void doFilter(Search search, RelevantRestaurants relevantRestaurants) {

        System.out.println("\n****** SEARCH FILTER ******");

        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        FactHandle rrFc = kieSession.insert(relevantRestaurants);
        FactHandle searchFc = kieSession.insert(search);
        int num = kieSession.fireAllRules();

        kieSession.delete(rrFc);
        kieSession.delete(searchFc);

        System.out.println("Fired rules: " + num);
        System.out.println("RR list size is : " + relevantRestaurants.getRelevantRestaurants().size());
    }
}
