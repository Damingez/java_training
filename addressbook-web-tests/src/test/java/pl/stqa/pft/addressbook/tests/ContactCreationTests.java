package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testAddingContact() throws Exception {

    app.getContactHelper().gotoAddContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Magda", "Bec", "48668290314", "magdabec@gmail.com", "test1"),true);
    app.getContactHelper().submitContactForm();
  }


}
