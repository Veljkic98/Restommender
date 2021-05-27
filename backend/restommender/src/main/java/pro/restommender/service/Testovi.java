package pro.restommender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieSession;

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

  public void testRestourantMusic() {
		// search obj
		Search s = new Search();
		s.setMusic("relaxing");
		s.setAccomodation("udobno");

		// restorani
		Restaurant r = new Restaurant();
		r.setId(1L);
		r.setMusic("relaxing");
		r.setAccomodation("udobno");
		Restaurant r2 = new Restaurant();
		r2.setId(2L);
		r2.setMusic("loud");
		r2.setAccomodation("tradicionalno");

		List<Restaurant> restaurants = new ArrayList<>();
		restaurants.add(r);
		restaurants.add(r2);
		RelevantRestaurants rr = new RelevantRestaurants();
		rr.setRelevantRestaurants(restaurants);

		kieSession.getAgenda().getAgendaGroup("music").setFocus();
		kieSession.insert(rr);
		kieSession.insert(s);
		int num = kieSession.fireAllRules();

		System.out.println("----------------------");
		System.out.println("Fired rules: " + num);
		System.out.println(rr.getRelevantRestaurants().size());


		kieSession.getAgenda().getAgendaGroup("accomodation").setFocus();
		kieSession.insert(rr);
		num = kieSession.fireAllRules();

		System.out.println("----------------------");
		System.out.println("Fired rules: " + num);
		System.out.println(rr.getRelevantRestaurants().size());
	}

  
	// public void testReservationDiscount() {

	// 	int numOfPersons = 5;
	// 	User u = new User();
	// 	Reservation r1 = new Reservation();
	// 	r1.setUser(u);
	// 	r1.setNumOfPersons(numOfPersons);
	// 	Reservation r2 = new Reservation();
	// 	r2.setUser(u);
	// 	Reservation r3 = new Reservation();
	// 	r3.setUser(u);

	// 	List<Reservation> reservations = new ArrayList<>();
	// 	reservations.add(r1);
	// 	reservations.add(r2);
	// 	reservations.add(r3);
	// 	u.setReservations(reservations);

	// 	Search s = new Search();
	// 	s.setNumOfPersons(numOfPersons);

	// 	Rule rule = new Rule();

		
	// 	kieSession.getAgenda().getAgendaGroup("reservation-number-discount").setFocus();
	// 	kieSession.insert(r1);
	// 	kieSession.insert(s);
	// 	kieSession.insert(rule);
	// 	int num = kieSession.fireAllRules();

	// 	System.out.println("----------------------");
	// 	System.out.println("Fired rules: " + num);
	// 	System.out.println("new dicount: " + r1.getDiscount());
	// 	System.out.println("Izvrseno pravilo: " + rule.getRule());
	// }

	public void testLocation() {
		Restaurant r1 = new Restaurant();
		r1.setLocation(0.2);
		Restaurant r2 = new Restaurant();
		r2.setLocation(6.5);
		Restaurant r3 = new Restaurant();
		r3.setLocation(0.7);
		List<Restaurant> restaurants = new ArrayList<>();
		restaurants.add(r1);
		restaurants.add(r2);
		restaurants.add(r3);
		RelevantRestaurants rr = new RelevantRestaurants();
		rr.setRelevantRestaurants(restaurants);

		kieSession.getAgenda().getAgendaGroup("location").setFocus();
		kieSession.insert(rr);
		int num = kieSession.fireAllRules();

		System.out.println("----------------------");
		System.out.println("Fired rules: " + num);
		System.out.println(rr.getRelevantRestaurants());
	}

	public void testMessage() throws Exception{
		Message message = new Message();
		message.setMessage("Hello World");
		message.setStatus(Message.GOODBYE);

		
		kieSession.insert(message);
		Thread.sleep(1000);
		int num = kieSession.fireAllRules();
		Thread.sleep(1000);

		System.out.println("----------------------");
		System.out.println("Fired rules: " + num);
	}

}
