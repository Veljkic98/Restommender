package pro.restommender.dto.responseDTO;

public class ReservationResponseDTO {

    private Long id;

    private UserResponseDTO user;

    private RestaurantResponseDTO restaurant;

    private int numOfPersons;

    private double discount = 0;

    public ReservationResponseDTO() {
    }

    public ReservationResponseDTO(Long id, UserResponseDTO user, RestaurantResponseDTO restaurant, int numOfPersons,
            double discount) {
        this.id = id;
        this.user = user;
        this.restaurant = restaurant;
        this.numOfPersons = numOfPersons;
        this.discount = discount;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserResponseDTO getUser() {
        return this.user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

    public RestaurantResponseDTO getRestaurant() {
        return this.restaurant;
    }

    public void setRestaurant(RestaurantResponseDTO restaurant) {
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
