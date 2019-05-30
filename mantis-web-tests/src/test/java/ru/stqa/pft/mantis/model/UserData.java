package ru.stqa.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "mantis_user_table")
public class UserData {

  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "email")
  private String email;

  @Column(name = "access_level", columnDefinition = "SMALLINT(6)")
  private String access_level;


  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public UserData withUsername(String username) {
    this.username = username;
    return this;
  }

  public UserData withPassword(String password) {
    this.password = password;
    return this;
  }


  public UserData withAccess_level(String access_level) {
    this.access_level = access_level;
    return this;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }

  public String getAccess_level() {
    return access_level;
  }

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String toString() {
    return "UserData{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", access_level='" + access_level + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData userData = (UserData) o;
    return id == userData.id &&
            Objects.equals(username, userData.username) &&
            Objects.equals(password, userData.password) &&
            Objects.equals(email, userData.email) &&
            Objects.equals(access_level, userData.access_level);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, password, email, access_level);
  }

}
