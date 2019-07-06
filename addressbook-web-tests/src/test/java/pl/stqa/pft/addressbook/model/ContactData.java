package pl.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private  int id = Integer.MAX_VALUE;
  private  String firstname;
  private  String lastname;
  private  String homeNumber;
  private  String mobileNumber;
  private  String workNumber;
  private  String email;
  private  String address;
  private  String group;
  private String allPhones;



  public ContactData() {};
  /*
  public ContactData(String firstname, String lastname, String homeNumber, String email, String group) {
    this.id = 0;
    this.firstname = firstname;
    this.lastname = lastname;
    this.homeNumber = homeNumber;
    this.email = email;
    this.group = group;
  }

  public ContactData(int id, String firstname, String lastname, String homeNumber, String email, String group) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.homeNumber = homeNumber;
    this.email = email;
    this.group = group;
  }

   */

  public int getId() {
    return id;
  }

  public String getGroup() {
    return group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getHomeNumber() {
    return homeNumber;
  }

  public String getEmail() {
    return email;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public String getWorkNumber() {
    return workNumber;
  }
  public String getAllPhones() {
    return allPhones;
  }

  public String getAddress() {
    return address;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withSurname(String surname) {
    this.lastname = surname;
    return this;
  }

  public ContactData withHomeNumber(String homeNumber) {
    this.homeNumber = homeNumber;
    return this;
  }

  public ContactData withMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  public ContactData withWorkNumber(String workNumber) {
    this.workNumber = workNumber;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }
}
