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
  private long id;
  
  @Column(name = "name")
  private String name;

  @Column(name = "location")
  private double location;

  @Column(name = "music")
  private String music;

  @Column(name = "accomodation")
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
}
