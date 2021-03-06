package pro.restommender.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
public class Restaurant {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name = "name")
  private String name;

  @Column(name = "location")
  private double location;

  @Column(name = "music")
  private String music; // relaxing, loud

  @Column(name = "accomodation") //udobno, tradicionalno
  private String accomodation;

  @Column(name = "smoking_area")
  private Boolean smokingArea;

  @Column(name = "nonsmoking_area")
  private Boolean nonSmokingArea;

  @Column(name = "alcoholic_drinks")
  private Boolean alcoholicDrinks;

  @Column(name = "nonalcoholic_drinks")
  private Boolean nonAlcoholicDrinks;

  @Column(name = "pet_friendly")
  private Boolean petFriendly;

  @Column(name = "kid_friendly")
  private Boolean kidFriendly;

  @Column(name = "rate")
  private double rate;

  @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
  private List<Reservation> reservations;

  @Column(name= "high_demand")
  private Boolean highDemand;

  public Restaurant() {
  }

  public Restaurant(Long id, String name, double location, String music, String accomodation, Boolean smokingArea, Boolean nonSmokingArea, Boolean alcoholicDrinks, Boolean nonAlcoholicDrinks, Boolean petFriendly, Boolean kidFriendly, double rate, List<Reservation> reservations, Boolean highDemand) {
    this.id = id;
    this.name = name;
    this.location = location;
    this.music = music;
    this.accomodation = accomodation;
    this.smokingArea = smokingArea;
    this.nonSmokingArea = nonSmokingArea;
    this.alcoholicDrinks = alcoholicDrinks;
    this.nonAlcoholicDrinks = nonAlcoholicDrinks;
    this.petFriendly = petFriendly;
    this.kidFriendly = kidFriendly;
    this.rate = rate;
    this.reservations = reservations;
    this.highDemand = highDemand;
  }


  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getLocation() {
    return this.location;
  }

  public void setLocation(double location) {
    this.location = location;
  }

  public String getMusic() {
    return this.music;
  }

  public void setMusic(String music) {
    this.music = music;
  }

  public String getAccomodation() {
    return this.accomodation;
  }

  public void setAccomodation(String accomodation) {
    this.accomodation = accomodation;
  }

  public Boolean isSmokingArea() {
    return this.smokingArea;
  }

  public Boolean getSmokingArea() {
    return this.smokingArea;
  }

  public void setSmokingArea(Boolean smokingArea) {
    this.smokingArea = smokingArea;
  }

  public Boolean isNonSmokingArea() {
    return this.nonSmokingArea;
  }

  public Boolean getNonSmokingArea() {
    return this.nonSmokingArea;
  }

  public void setNonSmokingArea(Boolean nonSmokingArea) {
    this.nonSmokingArea = nonSmokingArea;
  }

  public Boolean isAlcoholicDrinks() {
    return this.alcoholicDrinks;
  }

  public Boolean getAlcoholicDrinks() {
    return this.alcoholicDrinks;
  }

  public void setAlcoholicDrinks(Boolean alcoholicDrinks) {
    this.alcoholicDrinks = alcoholicDrinks;
  }

  public Boolean isNonAlcoholicDrinks() {
    return this.nonAlcoholicDrinks;
  }

  public Boolean getNonAlcoholicDrinks() {
    return this.nonAlcoholicDrinks;
  }

  public void setNonAlcoholicDrinks(Boolean nonAlcoholicDrinks) {
    this.nonAlcoholicDrinks = nonAlcoholicDrinks;
  }

  public Boolean isPetFriendly() {
    return this.petFriendly;
  }

  public Boolean getPetFriendly() {
    return this.petFriendly;
  }

  public void setPetFriendly(Boolean petFriendly) {
    this.petFriendly = petFriendly;
  }

  public Boolean isKidFriendly() {
    return this.kidFriendly;
  }

  public Boolean getKidFriendly() {
    return this.kidFriendly;
  }

  public void setKidFriendly(Boolean kidFriendly) {
    this.kidFriendly = kidFriendly;
  }

  public double getRate() {
    return this.rate;
  }

  public void setRate(double rate) {
    this.rate = rate;
  }

  public List<Reservation> getReservations() {
    return this.reservations;
  }

  public void setReservations(List<Reservation> reservations) {
    this.reservations = reservations;
  }

  public Boolean getHighDemand() {
    return this.highDemand;
  }

  public void setHighDemand(Boolean highDemand) {
    this.highDemand = highDemand;
  }


  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", name='" + getName() + "'" +
      ", location='" + getLocation() + "'" +
      ", music='" + getMusic() + "'" +
      ", accomodation='" + getAccomodation() + "'" +
      ", smokingArea='" + isSmokingArea() + "'" +
      ", nonSmokingArea='" + isNonSmokingArea() + "'" +
      ", alcoholicDrinks='" + isAlcoholicDrinks() + "'" +
      ", nonAlcoholicDrinks='" + isNonAlcoholicDrinks() + "'" +
      ", petFriendly='" + isPetFriendly() + "'" +
      ", kidFriendly='" + isKidFriendly() + "'" +
      ", rate='" + getRate() + "'" +
      ", reservations='" + getReservations() + "'" +
      ", highDemand='" + getHighDemand() + "'" +
      "}";
  }

}
