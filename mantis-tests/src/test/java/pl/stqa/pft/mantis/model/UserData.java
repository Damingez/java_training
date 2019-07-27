package pl.stqa.pft.mantis.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "mantis_user_table")
public class UserData {

  @Id
  @Column (name = "id")
  private int user_id;
  @Column (name = "username")
  private String username;
  @Column (name = "email")
  private  String email;
  @Column (name = "enabled")
  private byte enabled;



  public UserData withUsername(String username) {
    this.username = username;
    return this;
  }

  public UserData withUser_id(int user_id) {
    this.user_id = user_id;
    return this;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }

  public UserData withEnabled(byte enabled) {
    this.enabled = enabled;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public int getUser_id() {
    return user_id;
  }

  public String getEmail() {
    return email;
  }

  public int getEnabled() {
    return enabled;
  }
}
