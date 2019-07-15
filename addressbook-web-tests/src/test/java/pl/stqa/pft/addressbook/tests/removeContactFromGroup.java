package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class removeContactFromGroup extends TestBase{

  @Test
     public void removingContactFromGroup()
  {
           app.goTo().homePage();
           // Contacts before = app.db().contacts();  checking
           app.contact().clickGroupChoiceList();
           app.contact().clickFirstAvailableGroup();
           app.contact().selectContact();
           app.contact().clickRemoveFromGroup();
  }
}
