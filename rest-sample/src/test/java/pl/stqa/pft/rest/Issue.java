package pl.stqa.pft.rest;

import java.math.BigInteger;
import java.util.Objects;

public class Issue {


  private BigInteger id;
  private  String subject;
  private String description;
  private String state_name;

  public BigInteger getId() {
    return id;
  }

  public Issue withId(BigInteger id) {
    this.id = id;
    return this;
  }

  public String getSubject() {
    return subject;
  }


  public String getState_name() {
    return state_name;
  }

  public String getDescription() {
    return description;
  }

  public Issue withDescription(String description) {
    this.description = description;
    return this;
  }

  public Issue withSubject(String subject) {
    this.subject = subject;
    return this;
  }

  public Issue withState_name(String status_name) {
    this.state_name = status_name;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Issue issue = (Issue) o;
    return Objects.equals(id, issue.id) &&
            Objects.equals(subject, issue.subject) &&
            Objects.equals(description, issue.description) &&
            Objects.equals(state_name, issue.state_name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, subject, description, state_name);
  }
}
