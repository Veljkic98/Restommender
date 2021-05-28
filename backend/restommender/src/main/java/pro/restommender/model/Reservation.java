package pro.restommender.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private AuthenticatedUser user;

  @ManyToOne
  private Restaurant restaurant;

  @Column(name = "num_of_persons")
  private int numOfPersons;

  @Column(name = "discount")
  private double discount = 0;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public AuthenticatedUser getUser() {
    return this.user;
  }

  public void setUser(AuthenticatedUser user) {
    this.user = user;
  }

  public Restaurant getRestaurant() {
    return this.restaurant;
  }

  public void setRestaurant(Restaurant restaurant) {
    this.restaurant = restaurant;
  }

  public int getNumOfPersons() {
    return this.numOfPersons;
  }

  public void setNumOfPersons(int numOfPersons) {
    this.numOfPersons = numOfPersons;
  }

  public double getDiscount() {
    return this.discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }

}
