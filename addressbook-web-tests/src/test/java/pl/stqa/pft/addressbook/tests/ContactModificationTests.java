package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();

    if ( app.contact().all().size() == 0)
    {
      app.contact().create(new ContactData().withFirstname("Julio").withSurname("Berdys")
              .withHomeNumber("743534534").withEmail("please@gmail.com").withGroup("test1"),true);
    }
  }

  @Test ()

  public void modifyContact()
   {
     Set<ContactData> before = app.contact().all();
      ContactData modifiedContact = before.iterator().next();
     int index = before.size()-1;

     ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Pablo").withSurname("Krasa").withHomeNumber("45607906").withEmail("terere@gmail.com").withGroup("test1");
     app.contact().modify(contact.getId(), contact);
     app.goTo().homePage();

     Set<ContactData> after = app.contact().all();
     Assert.assertEquals(after.size(),before.size());

     before.remove(modifiedContact);
     before.add(contact);
     Assert.assertEquals(after, before);

   }


}
