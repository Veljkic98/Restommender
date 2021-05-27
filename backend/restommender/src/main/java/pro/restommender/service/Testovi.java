package pro.restommender.service;

import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import pro.restommender.dto.Search;
import pro.restommender.model.Message;
import pro.restommender.model.RelevantRestaurants;
import pro.restommender.model.Reservation;
import pro.restommender.model.Restaurant;
import pro.restommender.model.Rule;
import pro.restommender.model.User;

@Service
public class Testovi {
 
  // @Autowired
  // private KieContainer kieContainer;

  @Autowired
  private KieSession kieSession;

  public void testRestourantsCategory() {

		Search search = new Search();
		search.setMusic("relaxing");
		search.setAccomodation("udobno");
		search.setNonSmokingArea(true);
		search.setKidFriendly(true);
		search.setNonAlcoholicDrinks(true);
		search.setPetFriendly(true);

		Restaurant r = new Restaurant();
		r.setId(1L);
		r.setMusic("relaxing");
		r.setAccomodation("udobno");
		r.setNonSmokingArea(true);
		r.setKidFriendly(true);
		r.setNonAlcoholicDrinks(true);
		r.setPetFriendly(true);
		Restaurant r2 = new Restaurant();
		r2.setId(2L);
		r2.setMusic("relaxing");
		r2.setAccomodation("tradicionalno");
		r2.setNonSmokingArea(true);
		r2.setKidFriendly(true);
		r2.setNonAlcoholicDrinks(true);
		r2.setPetFriendly(true);

		List<Restaurant> restaurants = new ArrayList<>();
		restaurants.add(r);
		restaurants.add(r2);

		RelevantRestaurants rr = new RelevantRestaurants();
		rr.setRelevantRestaurants(restaurants);

		Rule rule = new Rule();

		kieSession.getAgenda().getAgendaGroup("filter").setFocus();
		kieSession.insert(rr);
		kieSession.insert(search);
		kieSession.insert(rule);
		int num = kieSession.fireAllRules();

		System.out.println("----------------------");
		System.out.println("Fired rules: " + num);
		System.out.println(rr.getRelevantRestaurants().size());
	}


}
