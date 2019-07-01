package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test ()
  public void testAddingContact() throws Exception {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();

    ContactData contact = new ContactData().withFirstname("Magda").
            withSurname("Bec").withHomeNumber("48668290314").withEmail("magdabec@gmail.com").withGroup("test1");
    app.contact().create(contact,true);

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(),before.size()+1);


    contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));

  }




}
