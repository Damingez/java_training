package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();

    if (! app.contact().isThereContact())
    {
      app.contact().create(new ContactData().withFirstname("Julio").withSurname("Berdys")
              .withHomeNumber("743534534").withEmail("please@gmail.com").withGroup("test1"),true);
    }
  }

  @Test ()

  public void modifyContact()
   {
     List<ContactData> before = app.contact().list();

     int index = before.size()-1;

     ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstname("Pablo").withSurname("Krasa").withHomeNumber("45607906").withEmail("terere@gmail.com").withGroup("test1");
     app.contact().modify(index, contact);
     app.goTo().homePage();

     List<ContactData> after = app.contact().list();
     Assert.assertEquals(after.size(),before.size());

     before.remove(index);
     before.add(contact);
     Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

   }


}
