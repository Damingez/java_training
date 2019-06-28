package pl.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private final String id;
  private final String firstname;
  private final String surname;
  private final String homeNumber;
  private final String email;
  private String group;

  public ContactData(String firstname, String surname, String homeNumber, String email, String group) {
    this.id = null;
    this.firstname = firstname;
    this.surname = surname;
    this.homeNumber = homeNumber;
    this.email = email;
    this.group = group;
  }

  public ContactData(String id, String firstname, String surname, String homeNumber, String email, String group) {
    this.id = id;
    this.firstname = firstname;
    this.surname = surname;
    this.homeNumber = homeNumber;
    this.email = email;
    this.group = group;
  }

  public String getId() {
    return id;
  }

  public String getGroup() {
    return group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getSurname() {
    return surname;
  }

  public String getHomeNumber() {
    return homeNumber;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", surname='" + surname + '\'' +
            '}';
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(surname, that.surname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, surname);
  }
}
