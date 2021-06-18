package pro.restommender.event;

import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("2h30m")
public class ReservationEvent {

    private Date executionTime;
    private Long userId;
    private Long restaurantId;

    public ReservationEvent() {
    }

    public ReservationEvent(Date executionTime, Long userId) {
        this.executionTime = executionTime;
        this.userId = userId;
    }

    public ReservationEvent(Date executionTime, Long userId, Long restaurantId) {
        this.executionTime = executionTime;
        this.userId = userId;
        this.restaurantId = restaurantId;
    }

    public Date getExecutionTime() {
        return this.executionTime;
    }

    public void setExecutionTime(Date executionTime) {
        this.executionTime = executionTime;
    }

    public Long getuserId() {
        return this.userId;
    }

    public void setuserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
    
}
