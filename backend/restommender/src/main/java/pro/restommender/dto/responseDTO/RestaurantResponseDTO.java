package pro.restommender.dto.responseDTO;

import java.util.List;

public class RestaurantResponseDTO {

    public Long id;
    public String name;
    public double location;
    public String music; // relaxing, loud
    public String accomodation;
    public Boolean smokingArea;
    public Boolean nonSmokingArea;
    public Boolean alcoholicDrinks;
    public Boolean nonAlcoholicDrinks;
    public Boolean petFriendly;
    public Boolean kidFriendly;
    public double rate;
    public List<ReservationResponseDTO> reservations;

    public RestaurantResponseDTO() {
    }


    public RestaurantResponseDTO(Long id, String name, double location, String music, String accomodation, Boolean smokingArea, Boolean nonSmokingArea, Boolean alcoholicDrinks, Boolean nonAlcoholicDrinks, Boolean petFriendly, Boolean kidFriendly, double rate, List<ReservationResponseDTO> reservations) {
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

    public List<ReservationResponseDTO> getReservations() {
        return this.reservations;
    }

    public void setReservations(List<ReservationResponseDTO> reservations) {
        this.reservations = reservations;
    }

}
