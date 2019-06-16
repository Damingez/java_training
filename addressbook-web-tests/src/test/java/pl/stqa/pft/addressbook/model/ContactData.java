package pl.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String surname;
  private final String homeNumber;
  private final String email;
  private String group;

  public ContactData(String firstname, String surname, String homeNumber, String email, String group) {
    this.firstname = firstname;
    this.surname = surname;
    this.homeNumber = homeNumber;
    this.email = email;
    this.group = group;
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
}
