package pro.restommender.event;

import java.io.Serializable;
import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("2h30m")
public class SearchEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date executionTime;
    private Long userId;

    public SearchEvent() {
    }

    public SearchEvent(Date executionTime, Long userId) {
        this.executionTime = executionTime;
        this.userId = userId;
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

}
