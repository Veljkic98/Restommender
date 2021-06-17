package pro.restommender.dto.responseDTO;

import pro.restommender.model.User.Type;

public class UserResponseDTO {

    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private Type type;

    private boolean canBeUnblocked;

    public UserResponseDTO() {
    }

    public UserResponseDTO(Long id, String email, String firstName, String lastName, Type type, boolean canBeUnblocked) {
        this.id = id;
        this.email = email;
        // this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.canBeUnblocked = canBeUnblocked;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean getCanBeUnblocked() {
        return this.canBeUnblocked;
    }

    public void setCanBeUnblocked(boolean canBeUnblocked) {
        this.canBeUnblocked = canBeUnblocked;
    }


}
