package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test (enabled = false)
  public void testAddingContact() throws Exception {

    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoAddContactPage();
    ContactData contact = new ContactData("Magda", "Bec", "48668290314", "magdabec@gmail.com", "test1");
    app.getContactHelper().fillContactForm(contact,true);
    app.getContactHelper().submitContactForm();

    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(),before.size()+1);


    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));

  }


}
