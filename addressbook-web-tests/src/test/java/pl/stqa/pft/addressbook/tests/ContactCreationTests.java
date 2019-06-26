package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test
  public void testAddingContact() throws Exception {

    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoAddContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Magda", "Bec", "48668290314", "magdabec@gmail.com", "test1"),true);
    app.getContactHelper().submitContactForm();

    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(),before.size());
  }


}
