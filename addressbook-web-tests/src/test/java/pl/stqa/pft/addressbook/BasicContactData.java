package pl.stqa.pft.addressbook;

public class BasicContactData {
  private final String firstname;
  private final String surname;
  private final String homeNumber;
  private final String email;

  public BasicContactData(String firstname, String surname, String homeNumber, String email) {
    this.firstname = firstname;
    this.surname = surname;
    this.homeNumber = homeNumber;
    this.email = email;
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
