package pl.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
 @Table (name="addressbook")
public class ContactData {

  @Id
  @Column (name = "id")
  private  int id = Integer.MAX_VALUE;
  @Expose
  @Column (name="firstname")
  private  String firstname;
  @Expose
  @Column (name="lastname")
  private  String lastname;
  @Expose
  @Column (name="home")
  @Type(type = "text")
  private  String homeNumber;
   @Column (name="mobile")
   @Type(type = "text")
  private  String mobileNumber;
   @Column (name="work")
   @Type(type = "text")
  private  String workNumber;
   @Transient
  private  String email;
   @Transient
  private  String email2;
   @Transient
  private  String email3;
   @Transient
  private  String address;
   @Transient
  private  String address2;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable (name= "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();


   @Transient
  private String allPhones;
   @Transient
  private String allEmails;
   @Transient
  private String allAdresses;
  @Transient





  public int getId() {
    return id;
  }



  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public Groups getGroups() {
    return new Groups(groups);
  }

  public String getHomeNumber() {
    return homeNumber;
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

  public String getAddress2() {
    return address2;
  }

  public String getAllAdresses() {
    return allAdresses;
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

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withAddress2(String address) {
    this.address2 = address;
    return this;
  }

  public ContactData withAllAdresses(String allAdresses) {
    this.allAdresses = allAdresses;
    return this;
  }



  public ContactData withId(int id) {
    this.id = id;
    return this;
  }
    /*
  public ContactData withGroup(String group) {
    this.groups = group;
    return this;
  }   */


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
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(homeNumber, that.homeNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, homeNumber);
  }

  public ContactData inGroup(GroupData group) {
      groups.add(group);
      return this;
  }
}
