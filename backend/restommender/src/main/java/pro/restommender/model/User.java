package pro.restommender.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User implements UserDetails {

  public enum Type {
    USER, ADMIN
  };

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_SEQ")
  @SequenceGenerator(sequenceName = "person_seq", name = "PERSON_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "type")
  private Type type;

  @Column(name = "blocked")
  private Boolean blocked;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Reservation> reservations;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
  private List<Authority> authorities;

  public User() {
  }

  public User(Long id, String email, String password, String firstName, String lastName, Type type, Boolean blocked) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.type = type;
    this.blocked = blocked;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Type getType() {
    return this.type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public List<Reservation> getReservations() {
    return this.reservations;
  }

  public void setReservations(List<Reservation> reservations) {
    this.reservations = reservations;
  }

  public Boolean isBlocked() {
    return this.blocked;
  }

  public Boolean getBlocked() {
    return this.blocked;
  }

  public void setBlocked(Boolean blocked) {
    this.blocked = blocked;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String toString() {
    return "{" + " id='" + getId() + "'" + ", email='" + getEmail() + "'" + ", password='" + getPassword() + "'"
        + ", firstName='" + getFirstName() + "'" + ", lastName='" + getLastName() + "'" + ", type='" + getType() + "'"
        + ", blocked='" + isBlocked() + "'" + "}";
  }

}