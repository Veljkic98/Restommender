package pro.restommender.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
  
  public enum Type {
    USER, ADMIN
  };

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username",nullable = false,unique = true)
  private String username;
  
  @Column(name = "password",nullable = false)
  private String password;
  
  @Column(name = "first_name")
  private String firstName;
  
  @Column(name = "last_name")
  private String lastName;

  @Column(name = "type")
  private Type type;

  @Column(name = "reservations")
  private int reservations;
}