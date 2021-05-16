package pro.restommender.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
  private String music;

  @Column(name = "accomodation")
  private String accomodation;

  @Column(name = "type")
  private String type; // moderni, tradicionalni, organizovane svirke, porodicni

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


  public Restaurant(Long id, String name, double location, String music, String accomodation, String type, Boolean smokingArea, Boolean nonSmokingArea, Boolean alcoholicDrinks, Boolean nonAlcoholicDrinks, Boolean petFriendly, Boolean kidFriendly) {
    this.id = id;
    this.name = name;
    this.location = location;
    this.music = music;
    this.accomodation = accomodation;
    this.type = type;
    this.smokingArea = smokingArea;
    this.nonSmokingArea = nonSmokingArea;
    this.alcoholicDrinks = alcoholicDrinks;
    this.nonAlcoholicDrinks = nonAlcoholicDrinks;
    this.petFriendly = petFriendly;
    this.kidFriendly = kidFriendly;
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

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
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

}
