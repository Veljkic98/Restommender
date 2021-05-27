package pro.restommender.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "authenticated_user")
public class AuthenticatedUser extends User {

    public AuthenticatedUser() {
        super();
    }

    @Override
    public String getUsername() {

        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

}
