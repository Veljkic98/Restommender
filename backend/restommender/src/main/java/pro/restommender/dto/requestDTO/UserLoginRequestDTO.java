package pro.restommender.dto.requestDTO;

import javax.validation.constraints.NotBlank;

public class UserLoginRequestDTO {

    @NotBlank(message = "Username cannot be null.")
    private String username;

    @NotBlank(message = "Password cannot be null.")
    private String password;

    public UserLoginRequestDTO() {
    }

    public UserLoginRequestDTO(String user, String pw) {
        this.username = user;
        this.password = pw;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
            " username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }

}
