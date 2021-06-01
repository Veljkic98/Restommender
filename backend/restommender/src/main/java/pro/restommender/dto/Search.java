package pro.restommender.dto;

public class Search {

  private Long userId;
  
  private int numOfPersons;

  private String name;

  private double location;

  private String music; // relaxing, loud

  private String accomodation;

  private String type; // moderni, tradicionalni, organizovane svirke, porodicni

  private Boolean smokingArea;

  private Boolean nonSmokingArea;

  private Boolean alcoholicDrinks;

  private Boolean nonAlcoholicDrinks;

  private Boolean petFriendly;

  private Boolean kidFriendly;


  public Long getUserId() {
    return this.userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public int getNumOfPersons() {
    return this.numOfPersons;
  }

  public void setNumOfPersons(int numOfPersons) {
    this.numOfPersons = numOfPersons;
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

  @Override
  public String toString() {
    return "{" +
      " userId='" + getUserId() + "'" +
      ", numOfPersons='" + getNumOfPersons() + "'" +
      ", name='" + getName() + "'" +
      ", location='" + getLocation() + "'" +
      ", music='" + getMusic() + "'" +
      ", accomodation='" + getAccomodation() + "'" +
      ", type='" + getType() + "'" +
      ", smokingArea='" + isSmokingArea() + "'" +
      ", nonSmokingArea='" + isNonSmokingArea() + "'" +
      ", alcoholicDrinks='" + isAlcoholicDrinks() + "'" +
      ", nonAlcoholicDrinks='" + isNonAlcoholicDrinks() + "'" +
      ", petFriendly='" + isPetFriendly() + "'" +
      ", kidFriendly='" + isKidFriendly() + "'" +
      "}";
  }

}
