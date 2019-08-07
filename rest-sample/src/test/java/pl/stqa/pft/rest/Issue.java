package pl.stqa.pft.rest;

import java.math.BigInteger;
import java.util.Objects;

public class Issue {


  private BigInteger id;
  private  String subject;
  private String description;
  private String status_name;

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


  public String getStatus_name() {
    return status_name;
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

  public Issue withStatus_name(String status_name) {
    this.status_name = status_name;
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
            Objects.equals(status_name, issue.status_name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, subject, description, status_name);
  }
}
