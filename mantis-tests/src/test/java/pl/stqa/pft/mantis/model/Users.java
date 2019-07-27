package pl.stqa.pft.mantis.model;

import java.util.ArrayList;
import java.util.List;

public class Users /*extends ForwardingSet <UserData>*/ {

  private ArrayList<UserData> delegate;


  public Users(List<UserData> users) {
    this.delegate = new ArrayList<>(users);
  }


  public ArrayList<UserData> delegate() {
    return delegate;
  }
}
