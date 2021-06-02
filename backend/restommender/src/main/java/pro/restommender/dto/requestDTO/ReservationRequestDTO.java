package pro.restommender.dto.requestDTO;

public class ReservationRequestDTO {

    private Long userId;

    private Long restaurantId;

    private int numOfPersons;

    public ReservationRequestDTO() {
    }

    public ReservationRequestDTO(Long userId, Long restaurantId, int numOfPersons) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.numOfPersons = numOfPersons;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Long getRestaurantId() {
        return this.restaurantId;
    }

    public int getNumOfPersons() {
        return this.numOfPersons;
    }

    @Override
    public String toString() {
        return "{" +
            " userId='" + getUserId() + "'" +
            ", restaurantId='" + getRestaurantId() + "'" +
            ", numOfPersons='" + getNumOfPersons() + "'" +
            "}";
    }

}
