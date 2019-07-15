package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class addContactToGroup extends TestBase{




  @Test
public void addingContactToGroupTest()
  {
    app.goTo().homePage();
   // Contacts before = app.db().contacts();  checking
    app.contact().clickGroupChoiceList();
    app.contact().clickNoneGroup();
    app.contact().selectContact();
    app.contact().clickAddToGroup();
  }

}
