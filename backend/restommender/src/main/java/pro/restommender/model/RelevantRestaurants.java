package pro.restommender.model;

import java.util.List;

public class RelevantRestaurants {
  
  private List<Restaurant> relevantRestaurants;


  public List<Restaurant> getRelevantRestaurants() {
    return this.relevantRestaurants;
  }

  public void setRelevantRestaurants(List<Restaurant> relevantRestaurants) {
    this.relevantRestaurants = relevantRestaurants;
  }
  public void addRestaurants(List<Restaurant> relevantRestaurants) {
    // for (Restaurant r : relevantRestaurants) {
    //   this.relevantRestaurants.add(r);
    // }
    this.relevantRestaurants.addAll(relevantRestaurants);
  }


  @Override
  public String toString() {
    String s = "";
    
    for (Restaurant r : getRelevantRestaurants()) {
      s += r.toString() + "\n";
    }

    return s;
  }


}
