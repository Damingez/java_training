package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

public class ContactTests extends TestBase {


  @Test
  public void testAddingContact() throws Exception {

    app.getNavigationHelper().gotoAddContactPage();
    app.getContactHelper().fillAddContactForm(new ContactData("Magda", "Bec", "48668290314", "magdabec@gmail.com"));
    app.getContactHelper().submitContactForm();
  }


}
