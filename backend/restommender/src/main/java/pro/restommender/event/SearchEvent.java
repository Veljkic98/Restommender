package pro.restommender.event;

import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("2h30m")
public class SearchEvent {

    private Date executionTime;
    private Long userId;
    private String restaurantName;

    public SearchEvent() {
    }
    public SearchEvent(Date executionTime, Long userId) {
        this.executionTime = executionTime;
        this.userId = userId;
    }

    public SearchEvent(Date executionTime, Long userId, String string) {
        this.executionTime = executionTime;
        this.userId = userId;
        this.restaurantName = string;
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

    public String getRestaurantName() {
        return this.restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

}
